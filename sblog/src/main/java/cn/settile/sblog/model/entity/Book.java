package cn.settile.sblog.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-02-01 21:13
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"subsections","user"})
@Entity
@Table(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "book_name",nullable = false)
    private String name;

    @Builder.Default
    @Column(nullable = false)
    private boolean canView = true;
    /**
     * 描述信息
     */
    @Column(nullable = false)
    private String info;

    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    private List<Subsection> subsections;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", canView='" + canView + '\'' +
                '}';
    }
}
