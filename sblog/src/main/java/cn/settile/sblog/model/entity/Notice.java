package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author : lzjyz
 * @date : 2020-02-15 16:32
 */
@Entity
@Table
@EqualsAndHashCode
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User sender;

    @OneToOne
    private User user;

    @ManyToOne
    private NoticeType noticeType;

    /**
     * commit或Article的主键
     */
    private long location;
}
