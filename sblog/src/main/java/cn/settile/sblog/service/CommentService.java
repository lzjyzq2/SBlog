package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.Comment;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author lzjyz
 */
public interface CommentService {

    /** 是否存在该ID的评论
     * @param id 评论ID
     * @return true/false
     */
    boolean existsCommentById(long id);

    /** 保存评论
     * @param comment 评论
     * @return 存储后对象实例
     */
    Comment save(Comment comment);

    /** 获取指定ID的评论
     * @param id 评论ID
     * @return 该ID的评论
     */
    Comment getCommentById(long id);


    /**
     * @param articleId
     * @return
     */
    Page<Comment> getCommentsByArticleId(long articleId, int page);

    /**
     * @param commentId
     * @return
     */
    Page<Comment> getCommentsByParentCommentId(long commentId, int page);
}
