package cn.settile.sblog.model.param;

import cn.settile.sblog.model.entity.Comment;
import cn.settile.sblog.service.ArticleService;
import cn.settile.sblog.service.UserService;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author lzjyz
 */
@Getter @Setter
@ApiModel
public class CommentParam {
    long articleId;
    String content;
    long replyCommentId;
    List<Long> userIds;
}
