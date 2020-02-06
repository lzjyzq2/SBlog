package cn.settile.sblog.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-01-21 14:47
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = "roles")
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "permission",nullable = false)
    private String permissionsName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn
    private Set<Role> roles;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionsName='" + permissionsName + '\'' +
                '}';
    }
}
