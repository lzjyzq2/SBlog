package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzjyz
 */
@Repository(value = "CommentDao")
public interface CommentDao extends JpaRepository<Comment,Long> {

    /**
     * @param article
     * @param pageable
     * @return
     */
    Page<Comment> getCommentsByArticleOrderByTimeDesc(Article article, Pageable pageable);


    /**
     * @param id
     * @param pageable
     * @return
     */
    Page<Comment> getCommentsByArticle_IdAndParentCommentIdOrderByTimeDesc(long id,long parentCommentId, Pageable pageable);

    Page<Comment> getCommentsByParentCommentIdOrderByTimeDesc(long id,Pageable pageable);
}
