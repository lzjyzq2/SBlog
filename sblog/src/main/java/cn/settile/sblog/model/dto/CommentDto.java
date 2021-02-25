package cn.settile.sblog.model.dto;

import cn.settile.sblog.model.entity.Comment;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author lzjyz
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {
    private long commentId;
    private long articleId;
    @Builder.Default
    private long parentCommentId = -1;
    private UserDto replyUser;
    private UserDto user;
    private String content;
    private Date time;
    private long commentCount;
    private long approve;
    private boolean canApprove;
    private List<CommentDto> comments;
}
