package cn.settile.sblog.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : lzjyz
 * @date : 2020-01-21 14:47
 */
@Data
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "permission",nullable = false)
    private String permissionsName;

    @ManyToOne
    @JoinColumn
    private Role role;
}
