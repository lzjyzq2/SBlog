package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-02-15 16:51
 */
@Entity
@Table
@EqualsAndHashCode(exclude = {"notices"})
public class NoticeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true)
    private String type;

    @OneToMany(mappedBy = "noticeType")
    private Set<Notice> notices;
}
