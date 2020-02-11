package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.entity.Permission;
import cn.settile.sblog.repository.PermissionDao;
import cn.settile.sblog.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : lzjyz
 * @date : 2020-02-10 17:57
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public void createPermission(String permission) {
        Permission perm = new Permission();
        perm.setPermissionsName(permission);
        permissionDao.saveAndFlush(perm);
    }

    @Override
    public void deletePermissionById(Long id) {
        permissionDao.deleteById(id);
    }

    @Override
    public void deletePermissionByName(String permission) {
        permissionDao.deletePermissionByPermissionsName(permission);
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionDao.getOne(id);
    }

    @Override
    public Permission getPermissionByName(String permission) {
        return permissionDao.getPermissionByPermissionsName(permission);
    }

    @Override
    public boolean existsPermissionByPermissionsName(String permission) {
        return permissionDao.existsPermissionByPermissionsName(permission);
    }
}
