package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.Book;
import cn.settile.sblog.model.entity.User;

import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-04-30 23:06
 */
public interface BookService {

    /** 获取一个文集
     * @param id BookId
     * @return Book信息
     */
    public Book getBookById(long id);

    /** 根据用户名查找文集
     * @param username 用户名
     * @return @{code Set<Book>}
     */
    public Set<Book> findBooksByUsername(String username);

    /** 根据用户查找文集
     * @param user 用户
     * @return @{code Set<Book>}
     */
    public Set<Book> findBooksByUser(User user);

    /** 保存文集
     * @param book 文集
     */
    public Book save(Book book);

    /** 保存文集集合
     * @param books 文集集合
     */
    public void saveAll(Set<Book> books);

    /** 删除指定文集
     * @param book
     */
    public void delete(Book book);

    /** 删除指定Id的文集
     * @param bookId 文集Id
     */
    public void delete(long bookId);

    /** 用户是否存在指定Id的文集
     * @param id bookId
     * @param username 用户名
     * @return {@code false}:不存在,{@code true}:存在
     */
    boolean existsBookByUsername(long id,String username);
}
