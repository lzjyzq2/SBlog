package cn.settile.sblog.model.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author : lzjyz
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"subsection","tags","comments","uploadImages","uploadFiles","articleApproves"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Article")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
     * 文章摘要
     */
    private String summary;

    /**
     * 文章内容
     */
    @Lob
    @Column(columnDefinition = "text")
    private String content;
    /**
     * 创建时间
     */
    @Column(nullable = false) @CreatedDate
    private Date createTime;

    /**
     * 最后更新时间
     */
    @Column(nullable = false)
    @LastModifiedDate
    private Date updateTime;

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
    @ColumnDefault(value = "0")
    private long views;

    /**
     * 日前阅读量
     */
    @ColumnDefault(value = "0")
    private long dayView;

    /**
     * 赞同数
     */
    @ColumnDefault(value = "0")
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

    @OneToMany(mappedBy = "article")
    private Set<ArticleApprove> articleApproves;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime="+ updateTime +
                ", canComment=" + canComment +
                ", canView=" + canView +
                ", canCopy=" + canCopy +
                ", views=" + views +
                ", dayView=" + dayView +
                ", approve=" + approve +
                '}';
    }
}
