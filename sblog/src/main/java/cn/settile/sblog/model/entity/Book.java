package cn.settile.sblog.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : lzjyz
 * @date : 2020-02-01 21:13
 */
@Data @Entity @Table(name = "book")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "book_name",nullable = false)
    private String name;

    //TODO:添加卷列、一对多关系
}
