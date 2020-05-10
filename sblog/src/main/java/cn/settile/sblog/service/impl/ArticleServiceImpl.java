package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.Subsection;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.repository.ArticleDao;
import cn.settile.sblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * @author : lzjyz
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public Article getArticleById(long id) {
        return articleDao.getOne(id);
    }

    @Override
    public Set<Article> getArticlesByUser(User user, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize, Sort.by(Sort.Direction.DESC,"createTime"));
        return articleDao.findArticlesByUser(user, pageable).toSet();
    }

    @Override
    public Set<Article> getAllArticlesByUser(User user) {
        return articleDao.findArticlesByUser(user);
    }

    @Override
    public Set<Article> getAllArticlesBySubsection(Subsection subsection) {
        return articleDao.findArticlesBySubsection(subsection);
    }

    @Override
    public Set<Article> getArticlesBySubsection(Subsection subsection, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize,Sort.Direction.DESC,"createTime");
        return articleDao.findArticlesBySubsection(subsection,pageable).toSet();
    }

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public void saveAll(Set<Article> articles) {
        articleDao.saveAll(articles);
    }

    @Override
    public boolean existsArticleByIdAndUsername(long id, String username) {
        return articleDao.existsArticleByIdAndUser_Username(id,username);
    }

    @Override
    public void delete(Article article) {
        articleDao.delete(article);
    }

    @Override
    public void delete(long id) {
        articleDao.deleteById(id);
    }


}
