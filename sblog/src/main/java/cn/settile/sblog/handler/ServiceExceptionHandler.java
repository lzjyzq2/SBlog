package cn.settile.sblog.handler;

import cn.settile.sblog.exception.NoFoundException;
import cn.settile.sblog.exception.ServiceException;
import cn.settile.sblog.exception.UnAuthenticationException;
import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.exception.result.ResultCode;
import cn.settile.sblog.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : lzjyz
 * @date : 2020-01-21 14:01
 */
@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {
    @Autowired
    ThemeService themeService;

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseBody
    public Result handleUnAuthorize(UnAuthenticationException e) {
        return e.getResult();
    }

    @ExceptionHandler(NoFoundException.class)
    public String handleNoFound(NoFoundException e) {
        return themeService.render("404");
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handleServiceException(ServiceException e) {
        return e.getResult();
    }
}
