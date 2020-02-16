package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-02-01 21:19
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"articles"})
@Table
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * 描述
     */
    @Column(nullable = false)
    private String info;

    @ManyToMany(fetch = FetchType.LAZY)
    Set<Article> articles;

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
