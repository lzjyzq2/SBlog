package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.Role;

/**
 * @author : lzjyz
 * @date : 2020-02-02 16:32
 */
public interface RoleService {

    String DefaultRoleName = "user";

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
