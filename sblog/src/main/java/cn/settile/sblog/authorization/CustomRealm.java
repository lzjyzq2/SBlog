package cn.settile.sblog.authorization;

import cn.settile.sblog.model.entity.Permission;
import cn.settile.sblog.model.entity.Role;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.service.UserService;
import cn.settile.sblog.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-01-22 15:02
 */
@Service
@Slf4j
public class CustomRealm extends AuthorizingRealm {


    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserService userService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("————权限认证 [ roles、permissions]————");
        User user = null;
        if (principals != null) {
            user = (User) principals.getPrimaryPrincipal();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 设置用户拥有的角色集合，比如“admin,test”
        // 设置用户拥有的权限集合，比如“sys:role:add,sys:user:add”
        Set<Role> roles = userService.getRoleSet(user);
        roles.forEach(role -> {
            info.addRole(role.getRoleName());
            Set<Permission> permissions = role.getPermissions();
            permissions.forEach(permission -> {
                info.addStringPermission(permission.getPermissionsName());
            });
        });
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String credentials = (String) token.getCredentials();
        if (credentials == null) {
            log.info("————————身份认证失败——————————IP地址:  " + CommonUtil.getIpAddrByRequest(SpringContextUtil.getHttpServletRequest()));
            throw new AuthenticationException("token为空!");
        }
        // 校验token有效性
        User loginUser = this.checkUserTokenIsEffect(credentials);
        return new SimpleAuthenticationInfo(loginUser, credentials, getName());
    }


    /**
     * 校验token的有效性
     *
     * @param token
     */
    public User checkUserTokenIsEffect(String token) throws AuthenticationException{
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token非法无效!");
        }

        // 查询用户信息
        User loginUser = User.builder().build();
        User user = userService.getUserByUname(username);
        if (user == null) {
            throw new AuthenticationException("用户不存在!");
        }

        // 校验token是否超时失效 & 或者账号密码是否错误
        if (!jwtTokenRefresh(token, username, user.getPassword())) {
            throw new AuthenticationException("Token失效请重新登录!");
        }

//        // 判断用户状态
//        if (!"0".equals(user.getDelFlag())) {
//            throw new AuthenticationException("账号已被删除,请联系管理员!");
//        }

        BeanUtils.copyProperties(user, loginUser);
        return loginUser;
    }

    /**
     * JWTToken刷新生命周期 （解决用户一直在线操作，提供Token失效问题）
     * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)
     * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
     * 3、当该用户这次请求JWTToken值还在生命周期内，则会通过重新PUT的方式k、v都为Token值，缓存中的token值生命周期时间重新计算(这时候k、v值一样)
     * 4、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
     * 5、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     * 6、每次当返回为true情况下，都会给Response的Header中设置Authorization，该Authorization映射的v为cache对应的v值。
     * 7、注：当前端接收到Response的Header中的Authorization值会存储起来，作为以后请求token使用
     * 参考方案：https://blog.csdn.net/qq394829044/article/details/82763936
     *
     * @param userName
     * @param passWord
     * @return
     */
    public boolean jwtTokenRefresh(String token, String userName, String passWord) {
        String cacheToken = String.valueOf(redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token));
        if (CommonUtil.isNotEmpty(cacheToken)) {
            // 校验token有效性
            if (!JwtUtil.verify(cacheToken, userName, passWord)) {
                String newAuthorization = JwtUtil.sign(userName, passWord);
                redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, newAuthorization);
                // 设置超时时间
                redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
            } else {
                redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, cacheToken);
                // 设置超时时间
                redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
            }
            return true;
        }
        return false;
    }
}
