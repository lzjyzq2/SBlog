package cn.settile.sblog.model.entity;

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
@EqualsAndHashCode(exclude = {"subsection","tags","comments","uploadImages","uploadFiles"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Article")
public class Article {
    @Id
    private long id;
    @JoinColumn(nullable = false,name = "uid") @ManyToOne(cascade = {CascadeType.REFRESH})
    private User user;
    /**
     *
     */
    @JoinColumn(nullable = false,name = "sid") @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH})
    private Subsection subsection;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    @Lob
    @Column(columnDefinition = "text")
    private String content;
    /**
     * 创建和最后更新时间
     */
    @Column(nullable = false) @CreatedDate
    @LastModifiedDate
    private Date time;

    /**
     * 能否评论：false-不能，true-能
     */
    @ColumnDefault(value = "true")
    private boolean canComment;

    /**
     * 能否查看
     */
    @ColumnDefault(value = "true")
    private boolean canView;

    /**
     * 能否复制：false-不能，true-能
     */
    @ColumnDefault(value = "false")
    private boolean canCopy;

    /**
     * 浏览量
     */
    private long views;

    /**
     * 日前阅读量
     */
    private long dayView;

    /**
     * 赞同数
     */
    private long approve;
    /**
     * 文章所具有的标签
     */
    @ManyToMany(mappedBy = "articles")
    private Set<Tag> tags;

    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "articles")
    private Set<UploadImage> uploadImages;

    @ManyToMany(mappedBy = "articles")
    private Set<UploadFile> uploadFiles;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", canComment=" + canComment +
                ", canView=" + canView +
                ", canCopy=" + canCopy +
                ", views=" + views +
                ", dayView=" + dayView +
                ", approve=" + approve +
                '}';
    }
}
