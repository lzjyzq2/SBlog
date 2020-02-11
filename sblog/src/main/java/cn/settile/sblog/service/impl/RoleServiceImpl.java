package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.entity.Permission;
import cn.settile.sblog.model.entity.Role;
import cn.settile.sblog.repository.RoleDao;
import cn.settile.sblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-02-02 16:33
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public Role getRoleByRoleId(Long id) {
        return roleDao.getOne(id);
    }

    @Override
    public Role getRoleByRoleName(String role) {
        return roleDao.getRoleByRoleName(role);
    }

    @Override
    public void deleteRoleByRoleName(String role) {
        roleDao.deleteRoleByRoleName(role);
    }

    @Override
    public void deleteRoleByRoleId(Long id) {
        roleDao.deleteById(id);
    }

    @Override
    public boolean existsRoleByRoleName(String role) {
        return roleDao.existsRoleByRoleName(role);
    }

    @Override
    public void createRole(String roleName, Permission... permissions) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setPermissions(new HashSet<>(Arrays.asList(permissions)));
        createRole(role);
    }

    @Override
    public void createRole(String roleName, List<Permission> permissions) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setPermissions(new HashSet<>(permissions));
        createRole(role);
    }

    @Override
    public void createRole(Role role) {
        roleDao.save(role);
    }
}
