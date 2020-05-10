package cn.settile.sblog.controller.api;

import cn.settile.sblog.exception.NoFoundException;
import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.model.dto.ArticleDto;
import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.model.param.ArticleParam;
import cn.settile.sblog.service.ArticleService;
import cn.settile.sblog.service.SubsectionService;
import cn.settile.sblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-04-20 18:59
 */
@Slf4j
@Api(value = "用户文章管理接口")
@RestController
@RequestMapping("/api/user/article")
public class ArticleController {
    //TODO:文章服务

    @Autowired
    ArticleService articleService;

    @Autowired
    SubsectionService subsectionService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "浏览指定文章ID的文章内容", httpMethod = "GET")
    @GetMapping("/view/{articleId}")
    @RequiresGuest
    public Result view(@PathVariable long articleId) {
        Article articleById = articleService.getArticleById(articleId);
        if (articleById.isCanView()) {
            ArticleDto article = ArticleDto.builder()
                    .id(articleId)
                    .title(articleById.getTitle())
                    .content(articleById.getContent())
                    .tags(articleById.getTags())
                    .approves(articleById.getApprove())
                    .comments(articleById.getComments().size())
                    .createTime(articleById.getCreateTime())
                    .updateTime(articleById.getUpdateTime())
                    .canComment(articleById.isCanComment())
                    .canCopy(articleById.isCanCopy())
                    .views(articleById.getViews())
                    .build();
            return Result.Builder(Result.SUCCESS, article);
        } else {
            throw new NoFoundException(Result.Builder(Result.SUCCESS, "文章不存在或已被隐藏", null));
        }
    }

    @ApiOperation(value = "返回指定文章ID的文章内容", httpMethod = "GET")
    @GetMapping("/info/{articleId}")
    public Result info(@PathVariable long articleId, HttpServletRequest request) throws Exception {
        if (articleService.existsArticleByIdAndUsername(articleId, userService.getUserNameByRequest(request))) {
            Article articleById = articleService.getArticleById(articleId);
            ArticleDto article = ArticleDto.builder()
                    .id(articleId)
                    .title(articleById.getTitle())
                    .content(articleById.getContent())
                    .tags(articleById.getTags())
                    .approves(articleById.getApprove())
                    .comments(articleById.getComments().size())
                    .createTime(articleById.getCreateTime())
                    .updateTime(articleById.getUpdateTime())
                    .canComment(articleById.isCanComment())
                    .canCopy(articleById.isCanCopy())
                    .canView(articleById.isCanView())
                    .views(articleById.getViews())
                    .build();
            return Result.Builder(Result.SUCCESS, article);
        } else {
            throw new NoFoundException(Result.Builder(Result.SUCCESS, "文章不存在", null));
        }
    }

    @ApiOperation(value = "创建新的文章", httpMethod = "POST")
    @PostMapping("/new")
    public Result writeArticle(@RequestBody ArticleParam articleParam, HttpServletRequest request) {
        try {
            User user = userService.getUserByRequest(request);
            if (ArticleParam.checkIsRight(articleParam) &&
                    subsectionService.existsSubsectionByIdAndUsername(articleParam.getSubsectionId(), user.getUsername())) {
                Article article = Article.builder()
                        .title(articleParam.getTitle())
                        .content(articleParam.getContent())
                        .subsection(subsectionService.getSubsectionById(articleParam.getSubsectionId()))
                        .user(user)
                        .canComment(articleParam.isCanComment())
                        .canCopy(articleParam.isCanCopy())
                        .canView(articleParam.isCanView())
                        .tags(articleParam.getTags())
                        .build();
                articleService.save(article);
                return Result.SUCCESS;
            } else {
                return Result.FAIL;
            }
        } catch (Exception e) {
            return Result.FAIL;
        }
    }

    @ApiOperation(value = "更新文章", httpMethod = "POST")
    @PostMapping("/update")
    public Result updateArticle(@RequestBody ArticleParam articleParam, HttpServletRequest request) {
        try {
            if (ArticleParam.checkIsRight(articleParam) &&
                    articleService.existsArticleByIdAndUsername(articleParam.getId(),
                            userService.getUserByRequest(request).getUsername())) {
                Article article = articleService.getArticleById(articleParam.getId());
                article.setTitle(articleParam.getTitle());
                article.setContent(articleParam.getContent());
                article.setCanComment(articleParam.isCanComment());
                article.setCanCopy(articleParam.isCanCopy());
                article.setCanView(articleParam.isCanCopy());
                article.setTags(articleParam.getTags());
                articleService.save(article);
                return Result.SUCCESS;
            } else {
                return Result.FAIL;
            }
        } catch (Exception e) {
            return Result.FAIL;
        }
    }

    @ApiOperation(value = "删除文章", httpMethod = "DELETE")
    @DeleteMapping("/delete/{articleId}")
    public Result deleteArticle(@PathVariable long articleId, HttpServletRequest request) throws Exception {
        if (articleService.existsArticleByIdAndUsername(articleId, userService.getUserNameByRequest(request))) {
            articleService.delete(articleId);
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @ApiOperation(value = "最近更新", httpMethod = "GET")
    @GetMapping("/recent/{username}/size/{size:(\\d+)}")
    public Result recent(@PathVariable String username, @PathVariable int size) {
        Set<ArticleDto> articles = new HashSet<>();
        articleService.getArticlesByUser(userService.getUserByUname(username), 0, size).forEach(article -> {
            if (article.isCanView()) {
                articles.add(ArticleDto.builder()
                        .id(article.getId())
                        .title(article.getTitle())
                        .content(article.getContent().substring(0, 200))
                        .createTime(article.getCreateTime())
                        .approves(article.getApprove())
                        .comments(article.getComments().size())
                        .views(article.getViews())
                        .tags(article.getTags())
                        .build());
            }
        });

        return Result.Builder(Result.SUCCESS,articles);
    }
}
