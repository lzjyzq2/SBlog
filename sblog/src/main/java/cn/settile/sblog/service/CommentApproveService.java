package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.User;

/**
 * @author lzjyz
 */
public interface CommentApproveService {
    /**
     * @return
     */
    boolean isApprove(long commentId, User user);

    /**
     * @param commentId
     * @return
     */
    long getCommentApproveCount(long commentId);
}
