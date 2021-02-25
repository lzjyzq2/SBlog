package cn.settile.sblog.controller.api;

import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.model.dto.CommentDto;
import cn.settile.sblog.model.dto.UserDto;
import cn.settile.sblog.model.entity.Comment;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.model.param.CommentParam;
import cn.settile.sblog.service.ArticleService;
import cn.settile.sblog.service.CommentApproveService;
import cn.settile.sblog.service.CommentService;
import cn.settile.sblog.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzjyz
 */
@Slf4j
@Api(value = "用户评论接口")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;
    private ArticleService articleService;
    private UserService userService;
    private CommentApproveService commentApproveService;
    public CommentController(CommentService commentService, ArticleService articleService, UserService userService, CommentApproveService commentApproveService){
        this.commentService = commentService;
        this.articleService = articleService;
        this.userService = userService;
        this.commentApproveService = commentApproveService;
    }

    @PostMapping
    public Result comment(@RequestBody CommentParam commentParam, HttpServletRequest request) throws Exception {
        if(articleService.existsArticlesById(commentParam.getArticleId())||(commentParam.getContent()!=null&&!"".equals(commentParam.getContent().trim()))){
            User user = userService.getUserByRequest(request);
            Comment comment = new Comment();
            comment.setUser(user);
            comment.setContent(commentParam.getContent());
            comment.setArticle(articleService.getArticleById(commentParam.getArticleId()));
            if(commentService.existsCommentById(commentParam.getReplyCommentId())){
                Comment parentComment = commentService.getCommentById(commentParam.getReplyCommentId());
                if(parentComment.getParentCommentId()!=-1){
                    comment.setParentCommentId(parentComment.getParentCommentId());
                }else {
                    comment.setParentCommentId(parentComment.getId());
                }
                parentComment.getComments().add(comment);
                commentService.save(parentComment);
            }else {
                commentService.save(comment);
            }
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @GetMapping("/artcile/{aid:\\d+}/page/{page}")
    public Result articleComments(@PathVariable long aid,@PathVariable int page,HttpServletRequest request) throws Exception {
        Page<Comment> commentPage = commentService.getCommentsByArticleId(aid,page);
        List<CommentDto> commentDtos = new ArrayList<>();
        User loginUser = null;
        try {
            loginUser = userService.getUserByRequest(request);
        }catch (Exception e){

        }
        for (Comment comment : commentPage) {
            UserDto user = UserDto.of(comment.getUser());
            UserDto replyUser = null;
            if (comment.getParentCommentId() != -1) {
                replyUser = UserDto.of(commentService.getCommentById(comment.getParentCommentId()).getUser());
            }
            Page<Comment> subCommentPages = commentService.getCommentsByParentCommentId(comment.getId(), 1);
            List<CommentDto> subComments = new ArrayList<>();
            for (Comment subComment : subCommentPages) {
                CommentDto commentDto = CommentDto.builder().commentId(subComment.getId())
                        .content(subComment.getContent())
                        .parentCommentId(subComment.getParentCommentId())
                        .user(user)
                        .replyUser(replyUser)
                        .articleId(subComment.getArticle().getId())
                        .approve(commentApproveService.getCommentApproveCount(subComment.getId()))
                        .time(subComment.getTime())
                        .build();
                if (loginUser != null) {
                    commentDto.setCanApprove(commentApproveService.isApprove(subComment.getId(), loginUser));
                } else {
                    commentDto.setCanApprove(false);
                }
                subComments.add(commentDto);
            }
            commentDtos.add(
                    CommentDto.builder()
                            .commentId(comment.getId())
                            .content(comment.getContent())
                            .parentCommentId(comment.getParentCommentId())
                            .user(user)
                            .replyUser(replyUser)
                            .articleId(comment.getArticle().getId())
                            .approve(commentApproveService.getCommentApproveCount(comment.getId()))
                            .commentCount(subCommentPages.getTotalElements())
                            .time(comment.getTime())
                            .comments(subComments)
                            .canApprove(commentApproveService.isApprove(comment.getId(), loginUser))
                            .build()
            );
        }

        CommentDto commentDto = CommentDto.builder()
                .commentCount(commentPage.getTotalElements())
                .comments(commentDtos)
                .build();
        return Result.Builder(Result.SUCCESS,commentDto);
    }
    @GetMapping("/artcile/{aid:\\d+}/tpage/{page:\\d+}")
    public Result articleTreeComments(@PathVariable long aid,@PathVariable int page){
        return Result.FAIL;
    }

    @GetMapping("/{parentCommentId:\\d+}/replys/{page:\\d+}")
    public Result findReplys(@PathVariable int parentCommentId, @PathVariable int page,HttpServletRequest request) throws Exception {
        Page<Comment> commentPage = commentService.getCommentsByParentCommentId(parentCommentId,page);
        User loginUser = userService.getUserByRequest(request);
        List<CommentDto> commentDtos = new ArrayList<>();
        commentPage.forEach(comment -> {
            UserDto user = UserDto.of(comment.getUser());
            UserDto replyUser = UserDto.of(commentService.getCommentById(comment.getParentCommentId()).getUser());
            commentDtos.add(
                    CommentDto.builder().commentId(comment.getId())
                            .content(comment.getContent())
                            .parentCommentId(comment.getParentCommentId())
                            .user(user)
                            .replyUser(replyUser)
                            .articleId(comment.getArticle().getId())
                            .approve(comment.getId())
                            .time(comment.getTime())
                            .canApprove(commentApproveService.isApprove(comment.getId(),loginUser))
                            .build()
            );
        });
        return Result.Builder(Result.SUCCESS,commentDtos);
    }
}
