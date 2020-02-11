package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzjyz
 */
@Repository
public interface PermissionDao extends JpaRepository<Permission,Long> {

    /** 根据权限名称删除一个权限
     * @param permission 权限名称
     */
    void deletePermissionByPermissionsName(String permission);

    /** 根据权限名称取得一个权限
     * @param permission 权限名称
     * @return 对应的一个权限
     */
    Permission getPermissionByPermissionsName(String permission);

    /** 根据权限名判断是否存在该权限
     * @param permission 权限名词
     * @return false:不存在,true:存在
     */
    boolean existsPermissionByPermissionsName(String permission);
}
