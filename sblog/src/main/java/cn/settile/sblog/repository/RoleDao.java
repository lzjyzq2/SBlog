package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.Role;
import cn.settile.sblog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-02-02 16:27
 */
@Repository
public interface RoleDao extends JpaRepository<Role,Long> {

    /** 根据角色名获取一个角色
     * @param role 角色名称
     * @return 对应的角色对象
     */
    Role getRoleByRoleName(String role);

    /** 根据角色名删除一个角色
     * @param role 角色名称
     */
    void deleteRoleByRoleName(String role);

    /** 检查是否存在该名称的角色
     * @param role 角色名称
     * @return false：不存在，true：存在
     */
    boolean existsRoleByRoleName(String role);
}
