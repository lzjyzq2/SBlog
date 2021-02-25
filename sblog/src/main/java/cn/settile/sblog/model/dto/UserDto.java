package cn.settile.sblog.model.dto;

import cn.settile.sblog.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : lzjyz
 * @date : 2020-01-27 10:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long uid;       // 用户id
    private String uname;   // 登录名，不可改
    private String nick;    // 用户昵称，可改
    private String email;
    private String phone;

    public static UserDto of(User user){
        return UserDto.builder()
                .uid(user.getUid())
                .uname(user.getUsername())
                .nick(user.getNick())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
