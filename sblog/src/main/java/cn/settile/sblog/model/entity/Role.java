package cn.settile.sblog.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-01-22 14:53
 */
@Entity
@Table(name = "role")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"permissions","users"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "rolename",nullable = false,unique = true)
    private String roleName;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Permission> permissions;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
