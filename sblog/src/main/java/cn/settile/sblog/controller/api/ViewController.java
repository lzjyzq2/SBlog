package cn.settile.sblog.controller.api;

import cn.settile.sblog.exception.NoFoundException;
import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.model.dto.ArticleDto;
import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.service.ArticleService;
import cn.settile.sblog.service.TagService;
import cn.settile.sblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzjyz
 */
@Slf4j
@Api(value = "用户公共信息接口")
@RestController
@RequestMapping("/api/article/view")
public class ViewController {

    private UserService userService;
    private ArticleService articleService;
    private TagService tagService;

    public ViewController(UserService userService, ArticleService articleService, TagService tagService) {
        this.userService = userService;
        this.articleService = articleService;
        this.tagService = tagService;
    }

    @ApiOperation(value = "浏览指定文章ID的文章内容", httpMethod = "GET")
    @GetMapping("/{articleId:\\d+}")
    @RequiresGuest
    public Result view(@PathVariable long articleId) {
        Article article = null;
        if(articleService.existsArticlesById(articleId)){
            article = articleService.getArticleById(articleId);
        }
        if (null!=article&&article.isCanView()) {
            ArticleDto articleDto = ArticleDto.builder()
                    .id(articleId)
                    .title(article.getTitle())
                    .content(article.getContent())
                    .tags(article.getTags())
                    .approves(article.getApprove())
                    .comments(article.getComments().size())
                    .publishTime(article.getPublishTime())
                    .updateTime(article.getUpdateTime())
                    .canComment(article.isCanComment())
                    .canCopy(article.isCanCopy())
                    .views(article.getViews())
                    //TODO Tags
                    .build();
            return Result.Builder(Result.SUCCESS, articleDto);
        } else {
            throw new NoFoundException(Result.Builder(Result.NOT_FOUND, "文章不存在或已被隐藏", null));
        }
    }
    @ApiOperation(value = "查询指定文章是否存在", httpMethod = "GET")
    @GetMapping("/exits/{articleId}")
    @RequiresGuest
    public Result hasArticle(@PathVariable long articleId){
        return Result.FAIL;
    }
}
