package cn.settile.sblog.controller.api;

import cn.settile.sblog.exception.ServiceException;
import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.model.entity.Role;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.service.RoleService;
import cn.settile.sblog.service.UserService;
import cn.settile.sblog.utils.CommonUtil;
import cn.settile.sblog.utils.PasswordUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-01-26 17:21
 */
@Slf4j
@RestController
@RequestMapping("/api/register")
@Api(tags = "用户注册接口")
public class RegisterController {

    public static final String REG_CODE_CACHENAME = "REG_CODE_CACHE";
    public static final String REG_CODE_CACHE = "REG_CODE_CACHE::";

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    //TODO:由配置文件控制
    /**
     * 用户名最大长度
     */
    private static final int MAX_NAME_LENGTH = 20;
    /**
     * 用户名最小长度
     */
    private static final int MIN_NAME_LENGTH = 1;
    /**
     * 用户昵称最大长度
     */
    private static final int MAX_NICK_LENGTH = 20;
    /**
     * 用户昵称最小长度
     */
    private static final int MIN_NICK_LENGTH = 1;

    /**
     * 最大密码长度
     */
    private static final int MAX_PWD_LENGTH = 120;

    /**
     * 最小密码长度
     */
    private static final int MIN_PWD_LENGTH = 8;

    /**
     * 密码匹配字串
     * 至少8个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符（非数字字母）
     */
    private static final String MATCHES_PWD_STRING = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    /**
     * 用户名匹配字串
     */
    private static final String MATCHES_NAME_STRING = "^[A-Za-z0-9]+$";
    /**
     * 用户昵称匹配字串
     */
    private static final String MATCHES_NICK_STRING = "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$";
    /**
     * 用户手机号匹配字串
     * 国内手机号匹配
     */
    private static final String MATCHES_PHONE_STRING = "^0?(13|14|15|18|17)[0-9]{9}$";
    /**
     * 用户邮箱匹配字串
     */
    private static final String MATCHES_EMAIL_STRING = "^\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}$";


    /**
     * 注册用户API接口，方法：PUT
     *
     * @param user 待注册User
     * @return 注册结果
     */
    @ApiOperation(value = "注册接口", notes = "用户输入用户信息进行注册，返回注册结果", httpMethod = "PUT", response = String.class)
    @PutMapping
    public Result register(@RequestBody User user) throws ServiceException{
        boolean checkout = checkUserInfoIsCurrent(user);
        boolean canReg = userService.canRegisterUser(user);
        if (checkout && canReg) {
            //TODO:密码加密
            try {
                user.setSalt(PasswordUtil.getSaltString());
                String password = PasswordUtil.encrypt(user.getUname(),user.getPassword(),user.getSalt());
                user.setPassword(password);
                Set<Role> roleSet = user.getRoles();
                if(null==roleSet){
                    roleSet = new HashSet<>();
                }else {
                    roleSet.clear();
                }
                roleSet.add(roleService.getRoleByRoleName(RoleService.DEFAULT_ROLE_NAME));
                user.setRoles(roleSet);
                log.error(user.toString());
                userService.registerUser(user);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(Result.REGISTER_ERR);
            }
            return Result.REGISTER_SUCCESS;
        } else {
            return Result.REGISTER_FAIL;
        }
    }

    /**
     * 检查待注册User信息是否正确
     *
     * @param user 待检测User
     * @return false：错误，true：正确
     */
    public boolean checkUserInfoIsCurrent(User user) {
        boolean flag = true;
        if (CommonUtil.isEmpty(user.getUname()) || !checkUserName(user.getUname())) {
            flag = false;
        }
        if (CommonUtil.isEmpty(user.getNick()) || !checkNickName(user.getNick())) {
            flag = false;
        }

        if (CommonUtil.isEmpty(user.getPassword())||!checkPassword(user.getPassword())) {
            flag = false;
        }
        if(CommonUtil.isEmpty(user.getEmail())||!checkEmail(user.getEmail())){
            flag = false;
        }
        //TODO:完善注册流程，手机号、邮箱的检测、验证、绑定
        return flag;
    }

    /** 检查用户名是否符合要求
     * @param name 用户名
     * @return false:符合，true:不符合
     */
    public static boolean checkUserName(String name){
        boolean flag = false;
        if(name.matches(MATCHES_NAME_STRING)){
            flag = true;
        }
        return flag;
    }

    /** 检查昵称是否符合要求
     * @param nick 昵称
     * @return false:符合，true:不符合
     */
    public static boolean checkNickName(String nick){
        boolean flag = false;
        if(nick.matches(MATCHES_NICK_STRING)){
            flag = true;
        }
        return flag;
    }

    /** 检查密码格式是否符合要求
     * @param password 密码
     * @return false:符合，true:不符合
     */
    public static boolean checkPassword(String password){
        boolean flag = false;
        if(password.matches(MATCHES_PWD_STRING)){
            flag = true;
        }
        return flag;
    }

    /** 检查邮箱地址是否正确
     * @param email 邮箱地址
     * @return false:邮箱地址格式不正确，true:邮箱地址格式正确
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        if(email.matches(MATCHES_EMAIL_STRING)){
            flag = true;
        }
        return flag;
    }

    /** 检查是否为正确的手机号
     * @param phone 待检查手机号
     * @return false:手机号格式不正确，true:手机号格式正确
     */
    public static boolean checkPhone(String phone){
        boolean flag = false;
        if(phone.matches(MATCHES_PHONE_STRING)){
            flag = true;
        }
        return flag;
    }

}
