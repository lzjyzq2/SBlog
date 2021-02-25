package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author lzjyz
 */

public interface TagService {
    /** 获取指定文章的所有标签
     * @param articles 文章集合
     * @return 标签集合
     */
    List<Tag> findTagByUserArticle(Set<Article> articles);
}
