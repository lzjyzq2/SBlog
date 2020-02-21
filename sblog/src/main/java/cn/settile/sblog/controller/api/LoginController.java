package cn.settile.sblog.controller.api;

import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.filter.aspect.AccessLimit;
import cn.settile.sblog.model.dto.LoginInfo;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.service.UserService;
import cn.settile.sblog.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author : lzjyz
 * @date : 2020-01-27 11:36
 */
@Slf4j
@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;


    @ApiOperation(value = "登录接口", notes = "用户输入用户名和密码进行登录，返回用户部分信息与Token", httpMethod = "POST", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String")
    })
    @PostMapping
    @AccessLimit(time = 5,count = 2,timeUnit = TimeUnit.SECONDS)
    public Result login(@RequestParam String username,@RequestParam String password) {
        Result result = Result.LOGIN_FAIL;
        if (CommonUtil.isNotEmpty(username) && CommonUtil.isNotEmpty(password)
                && RegisterController.checkPassword(password) && RegisterController.checkUserName(username)) {
            User loginUser = userService.getUserByUname(username);
            if(loginUser!=null&&checkPasswordIsCurrent(loginUser,password)){
                result = Result.LOGIN_SUCCESS;
                result.setData(getUserLoginInfo(loginUser));
            }
        }
        return result;
    }

    public boolean checkPasswordIsCurrent(User user,String loginPWD){
        String loginpwd = PasswordUtil.encrypt(user.getUname(),loginPWD,user.getSalt());
        if (loginpwd.equals(user.getPassword())){
            log.info("login success");
            return true;
        }else {
            return  false;
        }
    }

    public LoginInfo getUserLoginInfo(User user){
        LoginInfo info = new LoginInfo();
        info.setUid(user.getUid());
        info.setUname(user.getUname());
        info.setNick(user.getNick());
        info.setCreated(user.getCreated());
        info.setUpdated(user.getUpdated());
        String token = JwtUtil.sign(user.getUname(), user.getPassword());
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
        info.setToken(token);
        return info;
    }

}
