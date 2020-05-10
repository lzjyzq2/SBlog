package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.Permission;
import cn.settile.sblog.model.entity.Role;
import cn.settile.sblog.model.entity.User;

import javax.servlet.http.HttpServletRequest;
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

    /** 获取用户具有的所有角色
     * @param user 用户
     * @return 角色集合
     */
    Set<Role> getRoleSet(User user);

    /** 获取用户具有的所有角色名
     * @param user 用户
     * @return 角色名称集
     */
    Set<String> getRoleNameSet(User user);

    /** 获取用户具有的所有用户权限
     * @param user 用户
     * @return 用户权限
     */
    Set<Permission> getPermissionSet(User user);

    /** 获取用户具有的所有用户权限名称
     * @param user 用户
     * @return 用户权限名称
     */
    Set<String> getPermissionNameSet(User user);

    /** 是否存在该名称的用户
     * @param uname 用户名
     * @return {@code false}:不存在,{@code true}:存在
     */
    boolean existsUserByUname(String uname);

    /** 获取发起请求的用户
     * @param request 请求
     * @return 请求资源的用户
     * @throws Exception 不存在用户
     */
    User getUserByRequest(HttpServletRequest request) throws Exception;

    /** 获取发起请求的用户名
     * @param request 请求
     * @return 请求资源的用户的用户名
     * @throws Exception 不存在用户
     */
    String getUserNameByRequest(HttpServletRequest request) throws Exception;
}
