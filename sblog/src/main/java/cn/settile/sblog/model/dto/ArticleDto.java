package cn.settile.sblog.model.dto;

import cn.settile.sblog.model.entity.Article;
import cn.settile.sblog.model.entity.Tag;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-05-01 18:23
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class ArticleDto {

    private static Article article;
    private long id;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    private boolean canComment;
    private boolean canView;
    private boolean canCopy;
    private long views;
    private long dayView;
    private Set<Tag> tags;
    private long approves;
    private long comments;

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", canComment=" + canComment +
                ", canView=" + canView +
                ", canCopy=" + canCopy +
                ", views=" + views +
                ", dayView=" + dayView +
                ", tags=" + tags +
                ", approves=" + approves +
                ", comments=" + comments +
                '}';
    }

    /** 将{@code Article}转换为{@code ArticleDto}
     * @param article 待转换文章实体
     * @return ArticleDto
     */
    public static ArticleDto of(@NotNull Article article){
        ArticleDto.article = article;
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createTime(article.getCreateTime())
                .updateTime(article.getUpdateTime())
                .canComment(article.isCanComment())
                .canCopy(article.isCanCopy())
                .canView(article.isCanView())
                .views(article.getViews())
                .dayView(article.getDayView())
                .approves(article.getApprove())
                .comments(article.getComments().size())
                .tags(article.getTags())
                .build();
    }

    /** 将{@code Set<Article>}转换为{@code Set<ArticleDto>}
     * @param articles Set<Article>
     * @return Set<ArticleDto>
     */
    public static Set<ArticleDto> of(@NotNull Set<Article> articles){
        Set<ArticleDto> articleDtoSet = new HashSet<>();
        articles.forEach(article -> {
            articleDtoSet.add(ArticleDto.of(article));
        });
        return articleDtoSet;
    }
}
