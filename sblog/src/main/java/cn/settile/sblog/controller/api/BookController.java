package cn.settile.sblog.controller.api;

import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.model.dto.ArticleDto;
import cn.settile.sblog.model.dto.BookDto;
import cn.settile.sblog.model.dto.SubsectionDto;
import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.Book;
import cn.settile.sblog.model.entity.Subsection;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.model.param.BookParam;
import cn.settile.sblog.service.BookService;
import cn.settile.sblog.service.SubsectionService;
import cn.settile.sblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-04-30 22:56
 */
@RestController
@RequestMapping("/api/user/book")
@Api
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    SubsectionService subsectionService;
    @Autowired
    UserService userService;

    @ApiOperation(value = "返回登录用户的文集", httpMethod = "GET")
    @GetMapping("/list")
    public Result list(HttpServletRequest request) throws Exception {
        Set<BookDto> bookDtos = new HashSet<>();
        bookService.findBooksByUsername(userService.getUserNameByRequest(request)).forEach(book -> {
            bookDtos.add(BookDto.builder().id(book.getId()).userId(book.getUser().getUid()).name(book.getName()).info(book.getInfo()).build());
        });
        return Result.Builder(Result.SUCCESS,bookDtos);
    }

    @ApiOperation(value = "返回指定ID文集的信息", httpMethod = "GET")
    @GetMapping("/info/{bookId:(\\d+)}")
    public Result info(long bookId){
        Book bookData = bookService.getBookById(bookId);
        Set<SubsectionDto> subsections = new HashSet<>();
        bookData.getSubsections().forEach(subsection -> {
            Set<ArticleDto> articles = new HashSet<>();
            subsection.getArticles().forEach(article -> {
                articles.add(ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .build());
            });
            subsections.add(SubsectionDto.builder()
            .id(subsection.getId())
            .name(subsection.getName())
            .articles(articles)
            .build());
        });
        BookDto book = BookDto.builder()
                .id(bookData.getId())
                .name(bookData.getName())
                .info(bookData.getInfo())
                .userId(bookData.getUser().getUid())
                .subsections(subsections)
                .build();
        return Result.Builder(Result.SUCCESS,book);
    }

    @ApiOperation(value = "删除指定文集", httpMethod = "DELETE")
    @DeleteMapping("/delete/{bookId}")
    public Result deleteBook(@PathVariable long bookId,HttpServletRequest request) throws Exception {
        if(bookService.existsBookByUsername(bookId,userService.getUserNameByRequest(request))){
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @ApiOperation(value = "创建文集", httpMethod = "POST")
    @PostMapping("/new")
    public Result createBook(@RequestBody BookParam bookParam){
        if(bookParam.checkIsRight()){
            Book book = Book.builder()
                    .name(bookParam.getName())
                    .info(bookParam.getInfo())
                    .build();
            bookService.save(book);
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @ApiOperation(value = "更新文集信息", httpMethod = "POST")
    @PostMapping("/update")
    public Result updateBook(@RequestBody BookParam bookParam,HttpServletRequest request) throws Exception {
        if(bookParam.checkIsRight()&&bookService.existsBookByUsername(bookParam.getId(),userService.getUserNameByRequest(request))){
            Book book = bookService.getBookById(bookParam.getId());
            book.setName(book.getName());
            book.setInfo(bookParam.getInfo());
            bookService.save(book);
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }
}
