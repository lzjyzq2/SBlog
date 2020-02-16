package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-02-15 17:31
 */
@Entity
@Table
@EqualsAndHashCode(exclude = "articles")
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String hash;
    @ManyToMany
    private Set<Article> articles;
}
