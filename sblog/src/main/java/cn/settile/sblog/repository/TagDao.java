package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author lzjyz
 */
@Repository
public interface TagDao extends JpaRepository<Tag,Long> {

    List<Tag> findDistinctByArticlesIn(Set<Article> articles);
}
