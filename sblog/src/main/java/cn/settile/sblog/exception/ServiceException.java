package cn.settile.sblog.exception;

import cn.settile.sblog.exception.result.Result;

/**
 * @author : lzjyz
 * @date : 2020-02-01 16:10
 */
public class ServiceException extends RuntimeException {

    Result result;

    public ServiceException(Result result){
        super(result.getMessage());
        this.result = result;
    }

//    ServiceException(ResultCode resultCode,String message,boolean key){
//        super(message);
//        result = new Result();
//        result.setCode(resultCode);
//        result.setMessage(message,key);
//    }
//
//    ServiceException(ResultCode resultCode,String message,Object data,boolean key){
//        super(message);
//        result = new Result();
//        result.setCode(resultCode);
//        result.setMessage(message,key);
//        result.setData(data);
//    }

    public Result getResult() {
        return result;
    }
}
