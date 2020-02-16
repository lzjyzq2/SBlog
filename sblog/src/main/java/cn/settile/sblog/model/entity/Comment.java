package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-02-15 14:54
 */
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(exclude = {"comments","article"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne
    User user;

    private String content;

    @OneToMany
    private Set<Comment> comments;

    @Column(nullable = false)
    @CreatedDate
    private Date time;

    @ColumnDefault(value = "0")
    private long approve;

    @ManyToOne
    private Article article;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", approve=" + approve +
                '}';
    }
}
