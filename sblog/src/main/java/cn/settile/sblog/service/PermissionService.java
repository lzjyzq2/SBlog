package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.Permission;

/**
 * @author : lzjyz
 * @date : 2020-02-10 17:57
 */
public interface PermissionService {

    /** 创建一个权限在Permission数据表中
     * @param permission 待创建的权限名称
     */
    public void createPermission(String permission);

    /** 根据ID值删除一个权限
     * @param id Permission的主键ID值
     */
    public void deletePermissionById(Long id);

    /** 根据名称值删除一个权限
     * @param permission Permission的名称
     */
    public void deletePermissionByName(String permission);

    /** 根据ID值获取一个权限对象
     * @param id Permission的主键ID值
     * @return 对应的一个权限
     */
    public Permission getPermissionById(Long id);

    /** 根据名称值获取一个权限
     * @param permission Permission的名称
     * @return 对应的一个权限
     */
    public Permission getPermissionByName(String permission);

    /** 根据权限名判断是否存在该权限
     * @param permission 权限名词
     * @return false:不存在,true:存在
     */
    boolean existsPermissionByPermissionsName(String permission);
}
