package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author : lzjyz
 * @date : 2020-02-16 16:11
 */
@Entity
@Table
@EqualsAndHashCode
public class ArticleApprove {
    @Id
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Article article;

}
