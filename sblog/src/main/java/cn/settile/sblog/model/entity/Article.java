package cn.settile.sblog.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : lzjyz
 * @date : 2019-08-19 22:43
 */
@Data
@Entity
@Table(name = "Article")
public class Article {
    @Id
    long id;
    @JoinColumn(nullable = false,name = "uid") @ManyToOne(cascade = {CascadeType.REFRESH})
    User user;
    /**
     *
     */
    @JoinColumn(nullable = false,name = "cid") @ManyToOne(cascade = {CascadeType.REFRESH})
    Catalog catalog;
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
     * 最后更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    Date time;
}
