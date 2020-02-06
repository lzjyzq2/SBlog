package cn.settile.sblog.controller.api;

import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.model.dto.LoginInfo;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.service.UserService;
import cn.settile.sblog.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : lzjyz
 * @date : 2020-01-27 11:36
 */
@Slf4j
@RestController("/api/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;


    @PostMapping
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
