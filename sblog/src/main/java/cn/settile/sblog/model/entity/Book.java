package cn.settile.sblog.model.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-02-01 21:13
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"subsections"})
@Entity
@Table(name = "book")
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "book_name",nullable = false)
    private String name;

    /**
     * 描述信息
     */
    @Column(nullable = false)
    private String info;

    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    private Set<Subsection> subsections;

    @ManyToOne
    User user;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
