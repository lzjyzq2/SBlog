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
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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



    @RequiresAuthentication
    @ApiOperation(value = "返回指定文章ID的文章内容", httpMethod = "GET")
    @GetMapping("/info/{articleId:\\d+}")
    public Result info(@PathVariable long articleId, HttpServletRequest request) throws Exception {
        if (articleService.existsArticleByIdAndUsername(articleId, userService.getUserNameByRequest(request))) {
            Article articleById = articleService.getArticleById(articleId);
            ArticleDto article = ArticleDto.builder()
                    .id(articleId)
                    .title(articleById.getTitle())
                    .autoSummary(articleById.isAutoSummary())
                    .summary(articleById.getSummary())
                    .content(articleById.getContent())
                    .tags(articleById.getTags())
                    .approves(articleById.getApprove())
                    .comments(articleById.getComments().size())
                    .isTaskPublish(articleById.isTaskPublish())
                    .publishTime(articleById.getPublishTime())
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

    @RequiresAuthentication
    @ApiOperation(value = "创建新的文章", httpMethod = "POST")
    @PostMapping("/new")
    public Result writeArticle(@RequestBody ArticleParam articleParam, HttpServletRequest request) {
        try {
            log.info(articleParam.toString());
            User user = userService.getUserByRequest(request);
            if (ArticleParam.checkIsRight(articleParam) &&
                    subsectionService.existsSubsectionByIdAndUsername(articleParam.getSubsectionId(), user.getUsername())) {
                Article article = Article.builder()
                        .title(articleParam.getTitle())
                        .summary(articleParam.getSummary())
                        .content(articleParam.getContent())
                        .subsection(subsectionService.getSubsectionById(articleParam.getSubsectionId()))
                        .user(user)
                        .canComment(articleParam.isCanComment())
                        .canCopy(articleParam.isCanCopy())
                        .canView(articleParam.isCanView())
                        .tags(articleParam.getTags())
                        .state(articleParam.getState())
                        .build();
                if(articleParam.isTaskPublish()){
                    articleParam.setPublishTime(articleParam.getPublishTime());
                }else {
                    articleParam.setPublishTime(new Date());
                }
                article = articleService.save(article);
                ArticleDto articleDto = ArticleDto.builder()
                        .title(article.getTitle())
                        .summary(article.getSummary())
                        .content(article.getContent())
                        .canComment(article.isCanComment())
                        .canCopy(article.isCanCopy())
                        .canView(article.isCanView())
                        .id(article.getId())
                        .tags(article.getTags())
                        .build();
                return Result.Builder(Result.SUCCESS,articleDto);
            } else {
                return Result.FAIL;
            }
        } catch (Exception e) {
            return Result.FAIL;
        }
    }

    @RequiresAuthentication
    @ApiOperation(value = "更新文章", httpMethod = "POST")
    @PostMapping("/update")
    public Result updateArticle(@RequestBody ArticleParam articleParam, HttpServletRequest request) {
        try {
            if (ArticleParam.checkIsRight(articleParam) &&
                    articleService.existsArticleByIdAndUsername(articleParam.getId(),
                            userService.getUserByRequest(request).getUsername())) {
                Article article = articleService.getArticleById(articleParam.getId());
                article.setTitle(articleParam.getTitle());
                article.setAutoSummary(articleParam.isAutoSummary());
                article.setSummary(articleParam.getSummary());
                article.setContent(articleParam.getContent());
                article.setCanComment(articleParam.isCanComment());
                article.setCanCopy(articleParam.isCanCopy());
                article.setCanView(articleParam.isCanView());
                article.setTags(articleParam.getTags());
                article.setTaskPublish(articleParam.isTaskPublish());
                if(articleParam.isTaskPublish()){
                    article.setPublishTime(articleParam.getPublishTime());
                }
                article.setState(articleParam.getState());
                articleService.save(article);
                return Result.SUCCESS;
            } else {
                return Result.FAIL;
            }
        } catch (Exception e) {
            return Result.FAIL;
        }
    }

    @RequiresAuthentication
    @ApiOperation(value = "删除文章", httpMethod = "DELETE")
    @DeleteMapping("/delete/{articleId}")
    public Result deleteArticle(@PathVariable long articleId, HttpServletRequest request) throws Exception {
        if (articleService.existsArticleByIdAndUsername(articleId, userService.getUserNameByRequest(request))) {
            articleService.delete(articleId);
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    /**
     * 获取某用户最近更新的指定数量的文章
     * @param username 用户名称
     * @param size 数量
     * @return
     */
    @RequiresGuest
    @ApiOperation(value = "最近更新", httpMethod = "GET")
    @GetMapping("/recent/{username}/size/{size:\\d+}")
    public Result recent(@PathVariable String username, @PathVariable int size) {
        List<ArticleDto> articles = new ArrayList<>();
        articleService.getArticlesByUser(userService.getUserByUname(username), 0, size).forEach(article -> {
            if (article.isCanView()) {
                int summary = 200;
                if(article.getContent().length()<summary){
                    summary = article.getContent().length();
                }
                articles.add(ArticleDto.builder()
                        .id(article.getId())
                        .title(article.getTitle())
                        .summary(article.getContent().substring(0, summary))
                        .publishTime(article.getPublishTime())
                        .approves(article.getApprove())
                        .comments(article.getComments().size())
                        .views(article.getViews())
                        .tags(article.getTags())
                        .build());
            }
        });

        return Result.Builder(Result.SUCCESS, articles);
    }

    /**
     * 登陆用户获取自己指定分卷的文章列表
     * @param subsectionId
     * @param request
     * @return
     * @throws Exception
     */
    @RequiresAuthentication
    @ApiOperation(value = "登陆用户获取自己指定分卷的文章列表", httpMethod = "GET")
    @GetMapping("/list/{subsectionId:\\d+}")
    public Result list(@PathVariable long subsectionId, HttpServletRequest request) throws Exception {
        if (subsectionService.existsSubsectionByIdAndUsername(subsectionId, userService.getUserNameByRequest(request))) {
            List<ArticleDto> articleDtos = new ArrayList<>();
            subsectionService.getSubsectionById(subsectionId).getArticles().forEach(article -> {
                articleDtos.add(ArticleDto.builder()
                        .id(article.getId())
                        .title(article.getTitle())
                        .build()
                );
            });
            return Result.Builder(Result.SUCCESS, articleDtos);
        }
        return Result.FAIL;
    }
}
