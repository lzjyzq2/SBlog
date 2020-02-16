package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author : lzjyz
 * @date : 2020-02-15 17:35
 */
@Entity
@Table
@EqualsAndHashCode
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * 链接
     */
    private String link;

    /**
     * 链接描述
     */
    private String info;

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
