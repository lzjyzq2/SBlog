package cn.settile.sblog.model.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private Set<Article> articles;
}
