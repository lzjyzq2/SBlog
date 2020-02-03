package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.Permission;
import cn.settile.sblog.model.entity.Role;
import cn.settile.sblog.model.entity.User;

import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-01-26 17:25
 */
public interface UserService {

    /** 根据UserName从数据库中获取用户信息
     * @param uname 用户名称（数据库中唯一）
     * @return 一个具有该用户名称的User
     */
    User getUserByUname(String uname);

    /** 根据ID从数据库中获取用户信息
     * @param id 用户ID（数据库中唯一）
     * @return 一个具有该ID的User
     */
    User getUserById(long id);

    /** 检测是否能够创建该邮箱或用户名的User
     * @param user 用户信息
     * @return 是否能创建该User
     */
    boolean canRegisterUser(User user);

    /** 向数据库中注册用户
     * @param user 将要注册的User
     */
    void registerUser(User user);

    Set<Role> getRoleSet(User user);

    Set<String> getRoleNameSet(User user);

    Set<Permission> getPermissionSet(User user);

    Set<String> getPermissionNameSet(User user);

    boolean existsUserByUname(String uname);
}
