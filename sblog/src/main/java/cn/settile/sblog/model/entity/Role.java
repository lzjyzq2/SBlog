package cn.settile.sblog.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-01-22 14:53
 */
@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "rolename",nullable = false,unique = true)
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private Set<Permission> permissions;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
