package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.Tag;
import cn.settile.sblog.repository.TagDao;
import cn.settile.sblog.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author lzjyz
 */
@Service
public class TagServiceImpl implements TagService {


    private TagDao tagDao;

    public TagServiceImpl(TagDao tagDao){
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> findTagByUserArticle(Set<Article> articles) {
        List<Tag> tags = tagDao.findDistinctByArticlesIn(articles);
        return tags;
    }
}
