package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.CommentApprove;
import cn.settile.sblog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentApproveDao extends JpaRepository<CommentApprove,Long> {


    /**
     * @param commentId
     * @param user
     * @return
     */
    boolean existsCommentApproveByComment_IdAndUser(long commentId, User user);

    /**
     * @param commentId
     * @return
     */
    long countCommentApproveByComment_Id(long commentId);
}
