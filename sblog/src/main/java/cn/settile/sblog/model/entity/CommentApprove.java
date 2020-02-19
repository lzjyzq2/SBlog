package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author : lzjyz
 * @date : 2020-02-16 16:16
 */
@Entity
@Table
@EqualsAndHashCode
public class CommentApprove {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment comment;
}
