package cn.settile.sblog.handler;

import cn.settile.sblog.exception.NoFoundException;
import cn.settile.sblog.exception.ServiceException;
import cn.settile.sblog.exception.UnAuthenticationException;
import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author : lzjyz
 * @date : 2020-01-21 14:01
 */
@Slf4j
@RestControllerAdvice
@Controller
public class ServiceExceptionHandler implements ErrorController {
    @Autowired
    ThemeService themeService;

    private static final String PATH_DEFAULT = "/error";

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handleUnAuthorize(UnAuthenticationException e) {
        log.error("验证失败");
        return e.getResult();
    }

    @RequestMapping(PATH_DEFAULT)
    public void handleError(HttpServletRequest request) throws Throwable {
        if (request.getAttribute("javax.servlet.error.exception") != null) {
            throw (Throwable) request.getAttribute("javax.servlet.error.exception");
        }
    }

    @ExceptionHandler(NoFoundException.class)
    public String handleNoFound(NoFoundException e) {
        return themeService.render("404");
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleServiceException(ServiceException e) {
        log.error("系统异常");
        return e.getResult();
    }



    @Override
    public String getErrorPath() {
        return PATH_DEFAULT;
    }
}
