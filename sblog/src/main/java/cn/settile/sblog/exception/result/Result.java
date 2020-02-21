package cn.settile.sblog.exception.result;

import cn.settile.sblog.utils.MessagesUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author : lzjyz
 * @date : 2020-02-01 16:14
 */
@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum Result{

    /**
     *
     */
    SUCCESS(0, "normal.success"),
    /**
     *
     */
    FAIL(1,"normal.fail"),
    /**
     *
     */
    ERR(2,"normal.err"),

    /**
     * 短时间内请求过多，访问拒绝
     */
    REFUSE(3,"normal.refuse"),

    /**
     *
     */
    REGISTER_SUCCESS(10, "register.success"),
    /**
     *
     */
    REGISTER_FAIL(11,"register.fail"),

    /**
     *
     */
    REGISTER_ERR(12,"register.err"),

    /**
     *
     */
    LOGIN_SUCCESS(20, "login.success"),
    /**
     *
     */
    LOGIN_FAIL(21,"login.fail"),

    LOGIN_ERR(22,"login.err"),

    /**
     *
     */
    AUTHENTICATION_SUCCESS(30,"authentication.success"),

    /**
     *
     */
    AUTHENTICATION_FAIL(31,"authentication.fail"),

    /**
     *
     */
    AUTHENTICATION_ERR(32,"authentication.err");


    private int code;
    private String message;
    private Object data = null;


    private Result(int code,String message){
        this.code = code;
        this.message = MessagesUtil.get(message);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result Builder(Result result, Object data){
        result.setData(data);
        return result;
    }

    public static Result Builder(Result result,String message,Object data){
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
