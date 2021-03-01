package cn.settile.sblog.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author : lzjyz
 * @date : 2020-02-02 16:15
 */
@Data
public class LoginInfo {
    private long uid;       // 用户id
    private String uname;   // 登录名，不可改
    private String nick;    // 用户昵称，可改
    private long created;   // 登陆时间
    private long expiration;   // 到期时间
    private String token;
}
