package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.Book;
import cn.settile.sblog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author lzjyz
 */
@Repository
public interface BookDao extends JpaRepository<Book,Long> {
    /** 根据用户名查找文集
     * @param username 用户名称
     * @return {@code Set<Book>}
     */
    Set<Book> findBooksByUser_UsernameOrderByIdDesc(String username);

    /** 根据用户查找文集
     * @param user 用户
     * @return {@code Set<Book>}
     */
    Set<Book> findBooksByUserOrderByIdDesc(User user);

    /** 用户是否存在指定Id的文集
     * @param id bookId
     * @param user 用户
     * @return
     */
    boolean existsBookByIdAndUser(long id,User user);

    /** 用户是否存在指定Id的文集
     * @param id bookId
     * @param username 用户名
     * @return {@code false}:不存在,{@code true}:存在
     */
    boolean existsBookByIdAndUser_Username(long id,String username);
}
