package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author : lzjyz
 * @date : 2020-02-15 17:32
 */
@Entity
@Table
@EqualsAndHashCode
public class Follow {
    @Id
    private long id;

    @ManyToOne
    private User followers;

    @OneToOne
    private User user;
}
