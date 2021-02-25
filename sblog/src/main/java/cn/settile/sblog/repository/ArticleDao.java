package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.Subsection;
import cn.settile.sblog.model.entity.Tag;
import cn.settile.sblog.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-02-08 17:18
 */
@Repository
public interface ArticleDao extends JpaRepository<Article,Long> {

    /** 查找指定用户指定大小页文章数据
     * @param user 用户
     * @param pageable 分页设置
     * @return 指定页文章数据
     */
    Page<Article> findArticlesByUser(User user, Pageable pageable);

    /** 查找指定用户名用户的指定大小页文章数据
     * @param username 用户名
     * @param pageable 分页设置
     * @return 指定页文章数据
     */
    Page<Article> findArticlesByUser_Username(String username,Pageable pageable);

    /** 查找指定用户文章数据
     * @param user 用户
     * @return 文章集合
     */
    Set<Article> findArticlesByUser(User user);

    /** 查找指定用户文章数据
     * @param username 用户
     * @return 文章集合
     */
    Set<Article> findArticlesByUser_Username(String username);

    /** 查找指定卷文章数据
     * @param subsection 卷
     * @return
     */
    Set<Article> findArticlesBySubsection(Subsection subsection);


    /** 查找指定卷文章数据
     * @param subsection 卷
     * @param pageable 分页设置
     * @return 文章集合
     */
    Page<Article> findArticlesBySubsection(Subsection subsection,Pageable pageable);


    /** 获取是否存在某用户的指定ID的文章
     * @param id 文章ID
     * @param username 用户名
     * @return {@code false}:不存在 {@code true}:存在
     */
    boolean existsArticleByIdAndUser_Username(long id,String username);

    /**
     *
     */
    Page<Article> findArticlesByUser_UsernameOrderByPublishTime(String username, Pageable pageable);

    /** 统计含有某标签的文章数
     * @param tag 标签
     * @return 文章数
     */
    int countArticlesByTagsContains(Tag tag);
}
