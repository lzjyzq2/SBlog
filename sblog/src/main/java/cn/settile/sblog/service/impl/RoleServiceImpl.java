package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.entity.Role;
import cn.settile.sblog.repository.RoleDao;
import cn.settile.sblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : lzjyz
 * @date : 2020-02-02 16:33
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public Role getRoleByRoleName(String rolename) {
        return roleDao.getRoleByRoleName(rolename);
    }

    @Override
    public boolean existsRoleByRoleName(String rolename) {
        return roleDao.existsRoleByRoleName(rolename);
    }

}
