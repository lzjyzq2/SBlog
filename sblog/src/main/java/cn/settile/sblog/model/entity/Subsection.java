package cn.settile.sblog.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-02-07 17:54
 */
@Getter
@Setter
@Entity
@Table(name = "subsection")
@EqualsAndHashCode(exclude = {"articles"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subsection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "subsection_name",nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "bid",nullable = false)
    private Book book;

    @OneToMany(mappedBy = "subsection")
    private List<Article> articles;
}
