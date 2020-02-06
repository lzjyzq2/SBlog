package cn.settile.sblog.exception;

import cn.settile.sblog.exception.result.Result;

/**
 * @author : lzjyz
 * @date : 2020-02-01 13:18
 */
public class UnAuthenticationException extends ServiceException {

    public UnAuthenticationException(Result result) {
        super(result);
    }
}
