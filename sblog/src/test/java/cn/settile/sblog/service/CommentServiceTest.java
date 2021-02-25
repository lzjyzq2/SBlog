package cn.settile.sblog.service;

import cn.settile.sblog.SblogApplication;
import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.Comment;
import cn.settile.sblog.repository.ArticleDao;
import cn.settile.sblog.repository.CommentDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SblogApplication.class)
@TestPropertySource(locations ="file:./sblog-dev/application-test.properties")
public class CommentServiceTest {

    @Autowired
    CommentDao commentDao;
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleDao articleDao;
    @Test
    @Transactional
    public void Test(){
        Article article = articleDao.getOne(7L);
        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setApprove(0);
        comment.setTime(new Date());
        comment.setContent("This is Content!");
        comment.setUser(article.getUser());
        for (int i=0;i<10;i++){
            commentDao.save(comment);
        }
    }
}
