package cn.settile.sblog.controller.api;

import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.model.dto.ArticleDto;
import cn.settile.sblog.model.dto.BookDto;
import cn.settile.sblog.model.dto.PublicDto;
import cn.settile.sblog.model.dto.TagDto;
import cn.settile.sblog.model.entity.Tag;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.repository.TagDao;
import cn.settile.sblog.service.ArticleService;
import cn.settile.sblog.service.TagService;
import cn.settile.sblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.awt.print.Book;
import java.util.*;

/**
 * @author lzjyz
 */
@Slf4j
@Api(value = "用户公共信息接口")
@RestController
@RequestMapping("/api/user/public")
public class PublicController {


    private UserService userService;
    private ArticleService articleService;
    private TagService tagService;

    public PublicController(UserService userService, ArticleService articleService, TagService tagService) {
        this.userService = userService;
        this.articleService = articleService;
        this.tagService = tagService;
    }


    @GetMapping("/{username}")
    @ApiOperation(value = "返回指定用户的主页内容", httpMethod = "GET")
    public Result index(@PathVariable String username) {
        if (userService.existsUserByUname(username)) {

            User user = userService.getUserByUname(username);
            List<BookDto> bookDtos = getRecentlyBooks(username);
            List<ArticleDto> articleDtos = getRecentlyArticles(username);
            PublicDto publicDto = PublicDto.builder()
                    .userid(user.getUid())
                    .username(user.getUsername())
                    .neckname(user.getNick())
                    .follow(user.getFollows().size())
                    //TODO Article
                    .articles(0)
                    .anthology(user.getBooks().size())
                    .books(bookDtos)
                    .recentlyArticles(articleDtos)
                    .build();
            return Result.Builder(Result.SUCCESS, publicDto);
        }
        return Result.FAIL;
    }

    @ApiOperation(value = "返回指定用户的最近更新文章", httpMethod = "GET")
    @GetMapping("/{username}/recently/articles")
    public Result recentlyArticle(@PathVariable String username) {
        if (userService.existsUserByUname(username)) {
            List<ArticleDto> articleDtos = getRecentlyArticles(username);
            return Result.Builder(Result.SUCCESS, articleDtos);
        }
        return Result.FAIL;
    }

    @ApiOperation(value = "返回用户的文集", httpMethod = "GET")
    @GetMapping("/{username}/recently/books")
    public Result recentlyBooks(@PathVariable String username) {
        if (userService.existsUserByUname(username)) {
            List<BookDto> bookDtos = getRecentlyBooks(username);
            return Result.Builder(Result.SUCCESS, bookDtos);
        }
        return Result.FAIL;
    }

    @ApiOperation(value = "返回用户的Tags", httpMethod = "GET")
    @GetMapping("/{username}/tags")
    public Result tags(@PathVariable String username) {
        if (userService.existsUserByUname(username)) {
            List<Tag> tags = tagService.findTagByUserArticle(articleService.findArticleByUsername(username));
            List<TagDto> tagDtos = new ArrayList<>();
            tags.forEach(tag -> {
                tagDtos.add(
                        TagDto.builder()
                                .id(tag.getId())
                                .name(tag.getName())
                                .info(tag.getInfo())
                                .articles(articleService.countArticlesByTag(tag))
                                .build());
            });
            return Result.Builder(Result.SUCCESS, tagDtos);
        }
        return Result.FAIL;
    }

    @ApiOperation(value = "返回用户的公开信息", httpMethod = "GET")
    @GetMapping("/{username}/public-info")
    public Result publicInfo(@PathVariable String username) {
        if(userService.existsUserByUname(username)){
            Map<String,String> publicInfo = new HashMap<>();
            User user = userService.getUserByUname(username);
            publicInfo.put("userid",String.valueOf(user.getUid()));
            publicInfo.put("username",user.getUsername());
            publicInfo.put("neckname",user.getNick());
            publicInfo.put("follow",String.valueOf(user.getFollows().size()));
            publicInfo.put("articles",String.valueOf(user.getArticles().size()));
            publicInfo.put("anthology",String.valueOf(user.getBooks().size()));
            return Result.Builder(Result.SUCCESS,publicInfo);
        }
        return Result.FAIL;
    }

    private List<BookDto> getRecentlyBooks(String username) {
        List<BookDto> bookDtos = new ArrayList<>();
        userService.getUserByUname(username).getBooks().forEach(book -> {
            if (book.isCanView()) {
                bookDtos.add(
                        BookDto.builder()
                                .id(book.getId())
                                .name(book.getName())
                                .info(book.getInfo())
                                .build()
                );
            }
        });
        return bookDtos;
    }

    public List<ArticleDto> getRecentlyArticles(String username) {
        List<ArticleDto> articleDtos = new ArrayList<>();
        articleService.findRectlyArticles(username, 30).forEach(
                article -> {
                    if (article.isCanView()) {
                        articleDtos.add(
                                ArticleDto.builder()
                                        .id(article.getId())
                                        .title(article.getTitle())
                                        .summary(article.getSummary())
                                        .publishTime(article.getPublishTime())
                                        .build()
                        );
                    }
                }
        );
        return articleDtos;
    }

//    @ApiOperation(value = "用户获取自己的私有信息", httpMethod = "GET")
//    @GetMapping("/{username}/private-info")
//    public Result privateInfo(@PathVariable String username) {
//        return Result.FAIL;
//    }

}
