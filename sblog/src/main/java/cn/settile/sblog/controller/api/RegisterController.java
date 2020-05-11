package cn.settile.sblog.controller.api;

import cn.settile.sblog.configuration.SblogConfiguration;
import cn.settile.sblog.exception.ServiceException;
import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.filter.aspect.AccessLimit;
import cn.settile.sblog.model.entity.Role;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.model.param.RegisterParam;
import cn.settile.sblog.service.RoleService;
import cn.settile.sblog.service.ThemeService;
import cn.settile.sblog.service.UserService;
import cn.settile.sblog.utils.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    MailUtil mailUtil;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ThemeService themeService;
    @Autowired
    SblogConfiguration sblogConfiguration;
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
     * @param registerParam 待注册User
     * @return 注册结果
     */
    @ApiOperation(value = "注册接口", notes = "用户输入用户信息进行注册，返回注册结果", httpMethod = "PUT", response = String.class)
    @PutMapping
    public Result register(@RequestBody RegisterParam registerParam) throws ServiceException{
        boolean checkout = checkUserInfoIsCurrent(registerParam);
        log.info("checkout:{}",checkout);
        User reg_user = User.builder().username(registerParam.getUsername())
                .nick(registerParam.getNickname())
                .email(registerParam.getEmail())
                .build();
        boolean canReg = userService.canRegisterUser(reg_user);
        if (checkout && canReg) {
            try {
                log.info("信息正确");
                reg_user.setSalt(PasswordUtil.getSaltString());
                String password = PasswordUtil.encrypt(registerParam.getUsername(),registerParam.getPassword(),reg_user.getSalt());
                reg_user.setPassword(password);
                Set<Role> roleSet = reg_user.getRoles();
                if(null==roleSet){
                    roleSet = new HashSet<>();
                }else {
                    roleSet.clear();
                }
                roleSet.add(roleService.getRoleByRoleName(RoleService.DEFAULT_ROLE_NAME));
                reg_user.setRoles(roleSet);
                userService.registerUser(reg_user);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(Result.REGISTER_ERR);
            }
            return Result.REGISTER_SUCCESS;
        } else {
            return Result.REGISTER_FAIL;
        }
    }

    /** 发送验证码
     * 限制每60秒，请求一次发送
     * @param username 待注册用户名
     * @param email 用户邮箱
     * @return 邮件发送结果
     */
    @GetMapping("/captcha")
    @AccessLimit(time = 60,count = 1)
    @ApiOperation(value = "注册验证码接口", notes = "用户输入邮箱后，通过邮箱发送验证码", httpMethod = "GET", response = Result.class)
    public Result sendCaptcha(@RequestParam String username, @RequestParam String email){
        if(checkUserName(username)&&checkEmail(email)){
            String captcha = CommonUtil.getCode(6);
            redisUtil.set(REG_CODE_CACHE+username+":"+email,captcha, TimeUnit.MINUTES.toSeconds(15));
            Map<String,String> data = new HashMap<>();
            data.put("webSiteUrl",sblogConfiguration.getWebUrl());
            data.put("webSiteName",sblogConfiguration.getWebName());
            data.put("captcha",captcha);
            try {
                mailUtil.sendFreeMarkerHtmlMail(sblogConfiguration.getMailName(),
                        new String[]{email},new String[]{}, MessagesUtil.get("register.mail.title"),themeService.render("message"),data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Result.REGISTER_MAIL_SUCCESS;
    }
    /**
     * 检查待注册User信息是否正确
     *
     * @param registerParam 待检测User
     * @return false：错误，true：正确
     */
    public boolean checkUserInfoIsCurrent(RegisterParam registerParam) {
        boolean flag = true;
        if (CommonUtil.isEmpty(registerParam.getUsername()) || !checkUserName(registerParam.getUsername())) {
            flag = false;
        }
        if (CommonUtil.isEmpty(registerParam.getNickname()) || !checkNickName(registerParam.getNickname())) {
            flag = false;
        }

        if (CommonUtil.isEmpty(registerParam.getPassword())||!checkPassword(registerParam.getPassword())) {
            flag = false;
        }
        if(CommonUtil.isEmpty(registerParam.getEmail())||!checkEmail(registerParam.getEmail())){
            flag = false;
        }
        if(CommonUtil.isEmpty(registerParam.getCaptcha())){
            flag = false;
        }
        if(CommonUtil.isNotEmpty(registerParam.getCaptcha())&&!registerParam.getCaptcha().equals(redisUtil.get(REG_CODE_CACHE+registerParam.getUsername()+":"+registerParam.getEmail()))){
            flag = false;
        }
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
