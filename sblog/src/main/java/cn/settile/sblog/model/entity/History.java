package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author : lzjyz
 * @date : 2020-02-15 15:27
 */
@Entity
@Table
@EqualsAndHashCode
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @OneToOne
    private Article article;

}
