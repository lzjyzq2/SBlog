package cn.settile.sblog.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : lzjyz
 * @date : 2020-02-01 21:19
 */
@Data
@Table
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
}
