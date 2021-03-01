package cn.settile.sblog.controller.api;

import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.model.dto.ArticleDto;
import cn.settile.sblog.model.dto.SubsectionDto;
import cn.settile.sblog.model.entity.Book;
import cn.settile.sblog.model.entity.Subsection;
import cn.settile.sblog.model.param.SubsectionParam;
import cn.settile.sblog.service.BookService;
import cn.settile.sblog.service.SubsectionService;
import cn.settile.sblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-04-30 22:57
 */
@Api
@RestController
@RequestMapping("/api/user/subsection")
public class SubsectionController {

    @Autowired
    UserService userService;
    @Autowired
    SubsectionService subsectionService;
    @Autowired
    BookService bookService;

    @RequiresAuthentication
    @ApiOperation(value = "更新分卷信息", httpMethod = "POST")
    @PostMapping("/update")
    public Result update(@RequestBody SubsectionParam subsectionParam, HttpServletRequest request) throws Exception {
        if(subsectionService.existsSubsectionByIdAndUsername(subsectionParam.getId(),userService.getUserNameByRequest(request))){
            Subsection subsection = subsectionService.getSubsectionById(subsectionParam.getId());
            subsection.setName(subsectionParam.getName());
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @RequiresAuthentication
    @ApiOperation(value = "删除分卷", httpMethod = "DELETE")
    @DeleteMapping("/delete/{subsectionId:(\\d+)}")
    public Result delete(@PathVariable long subsectionId, HttpServletRequest request) throws Exception{
        if(subsectionService.existsSubsectionByIdAndUsername(subsectionId,userService.getUserNameByRequest(request))){
            subsectionService.delete(subsectionId);
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @RequiresAuthentication
    @ApiOperation(value = "返回指定ID的分卷", httpMethod = "POST")
    @PostMapping("/info/{subsectionId:(\\d+)}")
    public Result info(@PathVariable long subsectionId, HttpServletRequest request) throws Exception {
        if(subsectionService.existsSubsectionByIdAndUsername(subsectionId,userService.getUserNameByRequest(request))){
            Subsection sub = subsectionService.getSubsectionById(subsectionId);
            List<ArticleDto> articleDtoSet = new ArrayList<>();
                    sub.getArticles().forEach(article -> {
                        articleDtoSet.add(ArticleDto.builder().id(article.getId())
                        .title(article.getTitle())
                        .build());
                    });
            SubsectionDto subsection = SubsectionDto.builder()
                    .id(subsectionId)
                    .name(sub.getName())
                    .articles(articleDtoSet)
                    .build();
            return Result.Builder(Result.SUCCESS,subsection);
        }
        return Result.FAIL;
    }

    @RequiresAuthentication
    @ApiOperation(value = "新建分卷", httpMethod = "POST")
    @PostMapping("/new")
    public Result create(@RequestBody SubsectionParam subsectionParam, HttpServletRequest request) throws Exception {
        if (bookService.existsBookByUsername(subsectionParam.getBookId(), userService.getUserNameByRequest(request))
                && subsectionParam.checkIsRight()) {
            Subsection subsection = Subsection.builder()
                    .book(bookService.getBookById(subsectionParam.getBookId()))
                    .name(subsectionParam.getName())
                    .build();
            subsection = subsectionService.save(subsection);
            SubsectionDto subsectionDto = SubsectionDto.builder()
                    .id(subsection.getId())
                    .name(subsection.getName())
                    .build();
            return Result.Builder(Result.SUCCESS,subsectionDto);
        }
        return Result.FAIL;
    }

    @RequiresAuthentication
    @ApiOperation(value = "获取指定文集的分卷列表", httpMethod = "GET")
    @GetMapping("/list/{bookId:\\d+}")
    public Result list(@PathVariable long bookId,HttpServletRequest request) throws Exception {
        if(bookService.existsBookByUsername(bookId,userService.getUserNameByRequest(request))){
            List<SubsectionDto> subsectionDtos = new ArrayList<>();
            bookService.getBookById(bookId).getSubsections().forEach(subsection -> {
                subsectionDtos.add(SubsectionDto.builder()
                .id(subsection.getId())
                .name(subsection.getName())
                .build());
            });
            return Result.Builder(Result.SUCCESS,subsectionDtos);
        }
        return Result.FAIL;
    }

}
