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

    /**
     * @param rolename
     * @return
     */
    Role getRoleByRoleName(String rolename);

    /**
     * @param rolename
     * @return
     */
    boolean existsRoleByRoleName(String rolename);
}
