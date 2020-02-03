package cn.settile.sblog.exception;

import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.exception.result.ResultCode;

/**
 * @author : lzjyz
 * @date : 2020-02-01 13:18
 */
public class UnAuthenticationException extends ServiceException {

    public UnAuthenticationException(Result result) {
        super(result);
    }
}
