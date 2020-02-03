package cn.settile.sblog.exception;

import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.exception.result.ResultCode;

/**
 * @author : lzjyz
 * @date : 2020-02-01 13:17
 */
public class NoFoundException extends ServiceException {
    public NoFoundException(Result result) {
        super(result);
    }
}
