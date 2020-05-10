package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.entity.Permission;
import cn.settile.sblog.model.entity.Role;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.repository.UserDao;
import cn.settile.sblog.service.UserService;
import cn.settile.sblog.utils.CommonConstant;
import cn.settile.sblog.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-01-26 17:26
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUserByUname(String uname) {
        return userDao.getUserByUsername(uname);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getOne(id);
    }

    @Override
    public boolean canRegisterUser(User user) {
        boolean flag = false;
        if (!userDao.existsUserByEmailOrUsername(user.getEmail(), user.getUsername())) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void registerUser(User user) {
        userDao.save(user);
    }

    @Override
    public Set<Role> getRoleSet(User user) {
        return user.getRoles();
    }

    @Override
    public Set<String> getRoleNameSet(User user) {
        Set<Role> roles = user.getRoles();
        Set<String> rolename = new HashSet<>();
        roles.stream()
                .forEach(role -> {
                    rolename.add(role.getRoleName());
                });
        return rolename;
    }

    @Override
    public Set<Permission> getPermissionSet(User user) {
        Set<Permission> permissionSet = new HashSet<>();
        user.getRoles().stream()
                .forEach(role -> {
                    permissionSet.addAll(role.getPermissions());
                });
        return permissionSet;
    }

    @Override
    public Set<String> getPermissionNameSet(User user) {
        Set<String> permissionNameSet = new HashSet<>();
        user.getRoles().stream()
                .forEach(role -> {
                    role.getPermissions().stream()
                            .forEach(permission -> {
                                permissionNameSet.add(permission.getPermissionsName());
                            });
                });
        return null;
    }

    @Override
    public boolean existsUserByUname(String uname) {
        return userDao.existsUserByUsername(uname);
    }

    @Override
    public User getUserByRequest(HttpServletRequest request) throws Exception {
        User user = userDao.getUserByUsername(JwtUtil.getUserNameByToken(request));
        return user;
    }

    @Override
    public String getUserNameByRequest(HttpServletRequest request) throws Exception {
        return JwtUtil.getUserNameByToken(request);
    }
}
