package cn.settile.sblog.model.dto;

import cn.settile.sblog.model.entity.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : lzjyz
 * @date : 2020-01-27 10:49
 */
@Data
public class UserDto {
    private long uid;       // 用户id
    private String uname;   // 登录名，不可改
    private String nick;    // 用户昵称，可改
    private String email;
    private String phone;
}
