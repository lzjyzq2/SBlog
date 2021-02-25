package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.entity.Comment;
import cn.settile.sblog.repository.CommentDao;
import cn.settile.sblog.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author lzjyz
 */
@Service
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    @Override
    public boolean existsCommentById(long id) {
        return commentDao.existsById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public Comment getCommentById(long id) {
        return commentDao.getOne(id);
    }

    @Override
    public Page<Comment> getCommentsByArticleId(long articleId, int page) {
        Pageable pageable = PageRequest.of(page,10);
        return commentDao.getCommentsByArticle_IdAndParentCommentIdOrderByTimeDesc(articleId,-1,pageable);
    }

    @Override
    public Page<Comment> getCommentsByParentCommentId(long commentId,int page) {
        Pageable pageable = PageRequest.of(page,10);
        return commentDao.getCommentsByParentCommentIdOrderByTimeDesc(commentId,pageable);
    }

}
