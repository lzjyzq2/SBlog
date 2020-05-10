package cn.settile.sblog.model.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2019-08-19 20:35
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode(exclude = {"roles", "books", "histories",
        "articleCollections","bookCollections","follows","links","articleApproves","commentApproves"})
@Entity
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;       // 用户id
    @Column(name = "uname", nullable = false, unique = true)
    private String username;   // 登录名，不可改

    @Column(name = "nick", nullable = false, unique = true)
    private String nick;    // 用户昵称，可改

    @Column(nullable = false)
    String password;  //应加密存储

    @Column(name = "salt", nullable = false)
    private String salt;    // 加密盐值

    @Column(name = "email", nullable = false,unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "created", updatable = false, nullable = false)
    @CreatedDate
    private Date created;   // 创建时间

    @Column(name = "updated", nullable = false)
    @LastModifiedDate
    private Date updated;   // 修改时间

    @JoinColumn(nullable = false)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Role> roles;    //用户所有角色值，用于shiro做角色权限的判断

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Book> books;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<History> histories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<ArticleCollection> articleCollections;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<BookCollection> bookCollections;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "followers")
    private Set<Follow> follows;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Link> links;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<ArticleApprove> articleApproves;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<CommentApprove> commentApproves;
    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + username + '\'' +
                ", nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
