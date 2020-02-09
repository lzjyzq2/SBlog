package cn.settile.sblog.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2019-08-19 22:43
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"subsection","tags"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Article")
public class Article {
    @Id
    long id;
    @JoinColumn(nullable = false,name = "uid") @ManyToOne(cascade = {CascadeType.REFRESH})
    User user;
    /**
     *
     */
    @JoinColumn(nullable = false,name = "sid") @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH})
    Subsection subsection;
    /**
     * 文章标题
     */
    String title;
    /**
     * 文章内容
     */
    @Lob @Column(columnDefinition = "text")
    String content;
    /**
     * 创建和最后更新时间
     */
    @Column(nullable = false) @CreatedDate @LastModifiedDate
    Date time;

    /**
     * 能否评论：false-不能，true-能
     */
    @ColumnDefault(value = "true")
    boolean canComment;

    /**
     * 浏览量
     */
    long views;

    /**
     * 能否复制：false-不能，true-能
     */
    @ColumnDefault(value = "false")
    boolean canCopy;

    /**
     * 文章所具有的标签
     */
    @ManyToMany(mappedBy = "articles")
    Set<Tag> tags;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", canComment=" + canComment +
                ", views=" + views +
                ", canCopy=" + canCopy +
                '}';
    }
}
