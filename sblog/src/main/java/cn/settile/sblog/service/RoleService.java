package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.Permission;
import cn.settile.sblog.model.entity.Role;

import java.util.List;

/**
 * @author : lzjyz
 * @date : 2020-02-02 16:32
 */
public interface RoleService {

    /**
     * 创建时用户时默认角色名词
     */
    String DEFAULT_ROLE_NAME = "user";


    /** 根据ID值获取一个角色
     * @param id 角色ID值
     * @return 对应的角色对象
     */
    Role getRoleByRoleId(Long id);

    /** 根据角色名获取一个角色
     * @param role 角色名称
     * @return 对应的角色对象
     */
    Role getRoleByRoleName(String role);


    /** 根据角色名删除一个角色
     * @param role 角色名称
     */
    void deleteRoleByRoleName(String role);

    /** 根据角色ID值删除一个角色
     * @param id 角色ID值
     */
    void deleteRoleByRoleId(Long id);

    /** 检查是否存在该名称的角色
     * @param role 角色名称
     * @return false：不存在，true：存在
     */
    boolean existsRoleByRoleName(String role);


    /** 使用指定的角色名和权限组创建一个角色
     * @param role 角色名称
     * @param permissions 权限组
     */
    void createRole(String role, Permission... permissions);

    /** 使用指定的角色名和权限组创建一个角色
     * @param role 角色名称
     * @param permissions 权限组
     */
    void createRole(String role, List<Permission> permissions);

    /** 使用指定的角色实例创建一个角色
     * @param role 角色实例
     */
    void createRole(Role role);
}
