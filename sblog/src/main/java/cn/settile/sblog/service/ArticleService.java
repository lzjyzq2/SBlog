package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.Subsection;
import cn.settile.sblog.model.entity.Tag;
import cn.settile.sblog.model.entity.User;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-04-29 19:50
 */

public interface ArticleService {

    /** 获取指定ID的文章
     * @param id 文章ID
     * @return 对应文章ID的文章
     */
    public Article getArticleById(long id);


    /** 获取用户指定页的指定数量文章集合
     * @param user 用户
     * @param page  页
     * @param pageSize 页大小
     * @return 文章集合
     */
    public Set<Article> getArticlesByUser(User user,int page,int pageSize);

    /** 获取用户的所有文章集合
     * @param user 用户
     * @return 文章集合
     */
    public Set<Article> getAllArticlesByUser(User user);

    /** 获取指定卷的所有文章
     * @param subsection 卷
     * @return 卷所包含的文章
     */
    public Set<Article> getAllArticlesBySubsection(Subsection subsection);

    /** 获取文集指定页的指定数量文章集合
     * @param subsection 卷
     * @param page 页
     * @param pageSize 页大小
     * @return 文章集合
     */
    public Set<Article> getArticlesBySubsection(Subsection subsection,int page,int pageSize);

    /** 存储
     * @param article 带存储文章
     */
    public Article save(Article article);

    /** 存储文章集合
     * @param articles 带存储文章集合
     */
    public void saveAll(Set<Article> articles);

    public List<Article> findRectlyArticles(String username, int pageSize);

    /** 获取是否存在某用户的指定ID的文章
     * @param id 文章ID
     * @param username 用户名
     * @return {@code false}:不存在 {@code true}:存在
     */
    public boolean existsArticleByIdAndUsername(long id,String username);

    /** 获取该用户名的用户的文章
     * @param username 用户名
     * @return 文章集合
     */
    public Set<Article> findArticleByUsername(String username);

    public int countArticlesByTag(Tag tag);

    /** 删除文章
     * @param article 文章实体
     */
    public void delete(Article article);

    /** 删除指定ID的文章
     * @param id 文章ID
     */
    public void delete(long id);

    /** 获取是否存在某用户的指定ID的文章
     * @param id 文章ID
     * @return {@code false}:不存在 {@code true}:存在
     */
    boolean existsArticlesById(long id);
}
