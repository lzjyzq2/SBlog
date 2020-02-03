package cn.settile.sblog.utils;

import cn.settile.sblog.model.dto.UserDto;
import cn.settile.sblog.model.entity.User;

/**
 * @author : lzjyz
 * @date : 2020-01-27 10:47
 */
public class ConvertUtil {

    /** 将User转换为UserDto
     * @param user 待转化的User
     * @return 转换结果
     */
    public static UserDto convertUser(User user){
        UserDto userDto = new UserDto();
        userDto.setUid(user.getUid());
        userDto.setUname(user.getUname());
        userDto.setNick(user.getNick());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setCreated(user.getCreated());
        userDto.setUpdated(user.getUpdated());
        return userDto;
    }
}
