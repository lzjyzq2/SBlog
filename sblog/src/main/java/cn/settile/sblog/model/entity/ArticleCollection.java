package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author : lzjyz
 * @date : 2020-02-15 15:50
 */
@Entity
@Table
@EqualsAndHashCode
public class ArticleCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @OneToOne
    private Article article;
}
