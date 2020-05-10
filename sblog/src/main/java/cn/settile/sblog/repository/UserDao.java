package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzjyz
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {
    /**
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /** 查询是否已经有该用户的邮箱和用户名被注册
     * @param username 待查询的User
     * @param email 待查询的email
     * @return false:不存在,true:存在
     */
    boolean existsUserByEmailOrUsername(String email, String username);

    /** 根据用户名检查是否存在该用户
     * @param username 用户名
     * @return false:不存在,true:存在
     */
    boolean existsUserByUsername(String username);
}
