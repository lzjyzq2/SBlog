package cn.settile.sblog.exception.result;

import cn.settile.sblog.utils.MessagesUtil;
import lombok.Data;

/**
 * @author : lzjyz
 * @date : 2020-02-01 16:14
 */
@Data
public class Result{

    ResultCode code;
    Object data;

    public Result(ResultCode resultCode,Object data){
        this.code = resultCode;
        this.data = data;
    }
    public Result(ResultCode resultCode){
        this.code = resultCode;
    }
    public Result(ResultCode resultCode,String message,boolean key){
        this.code = resultCode;
        setMessage(message,key);
    }
    public Result(ResultCode resultCode,String message,Object data,boolean key){
        this.code = resultCode;
        setMessage(message,key);
        this.data = data;
    }

    public void setMessage(String message,boolean key){
        if(code!=null){
            if(key){
                this.code.setMessage(MessagesUtil.get(message));
            }else {
                this.code.setMessage(message);
            }
        }
    }
}
