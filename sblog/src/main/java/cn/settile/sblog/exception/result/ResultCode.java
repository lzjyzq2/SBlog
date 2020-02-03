package cn.settile.sblog.exception.result;

import cn.settile.sblog.utils.MessagesUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * @author lzjyz
 */
@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum ResultCode implements Serializable {

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
    ResultCode(int code,String message){
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
}
