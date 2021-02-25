package cn.settile.sblog.model.dto;

import cn.settile.sblog.model.entity.Tag;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @date 2020年7月13日 15:54:15
 * @author lzjyz
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class PublicDto {
    String username;
    long userid;
    String neckname;
    String autograph;

    long follow;
    long articles;
    long anthology;
    long tags;
    boolean canFollow;

    List<ArticleDto> recentlyArticles;
    List<BookDto> books;
    List<String> allTag;
}
