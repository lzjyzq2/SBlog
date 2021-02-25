package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.repository.CommentApproveDao;
import cn.settile.sblog.service.CommentApproveService;
import org.springframework.stereotype.Service;

/**
 * @author lzjyz
 */
@Service
public class CommentApproveServiceImpl implements CommentApproveService {

    private CommentApproveDao commentApproveDao;

    public CommentApproveServiceImpl(CommentApproveDao commentApproveDao) {
        this.commentApproveDao = commentApproveDao;
    }

    @Override
    public boolean isApprove(long commentId, User user) {
        return commentApproveDao.existsCommentApproveByComment_IdAndUser(commentId,user);
    }

    @Override
    public long getCommentApproveCount(long commentId) {
        return commentApproveDao.countCommentApproveByComment_Id(commentId);
    }
}
