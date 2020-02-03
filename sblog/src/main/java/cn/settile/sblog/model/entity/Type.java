package cn.settile.sblog.model.entity;

import lombok.Data;

import javax.persistence.*;

/** 文章类型
 * @author : lzjyz
 * @date : 2020-02-01 21:19
 */
@Data @Table @Entity
public class Type {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true)
    private String name;
}
