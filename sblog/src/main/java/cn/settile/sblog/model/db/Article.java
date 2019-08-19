package cn.settile.sblog.model.db;

import lombok.Data;

import javax.persistence.*;

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
    @JoinColumn(nullable = false,name = "cid") @ManyToOne(cascade = {CascadeType.REFRESH})
    Catalog catalog;
    String title;
    String content;
}
