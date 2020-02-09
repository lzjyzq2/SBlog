package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : lzjyz
 * @date : 2020-02-08 17:18
 */
@Repository
public interface ArticleDao extends JpaRepository<Article,Long> {
}
