package cn.settile.sblog.exception;

import cn.settile.sblog.exception.result.Result;

/**
 * @author : lzjyz
 * @date : 2020-02-01 13:17
 */
public class NoFoundException extends ServiceException {
    public NoFoundException(Result result) {
        super(result);
    }
}
