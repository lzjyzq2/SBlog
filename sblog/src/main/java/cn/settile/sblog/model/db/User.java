package cn.settile.sblog.model.db;

import javax.persistence.*;

/**
 * @author : lzjyz
 * @date : 2019-08-19 20:35
 */
@Entity
@Table(name = "UserInfo")
public class User {
    @Id
    long uid;
    @Column(length = 32,nullable = false)
    String uname;
    @Column(nullable = false)
    String password;
    @Column
    String token;
    @JoinColumn(nullable = false,name = "authority") @ManyToOne(cascade = {CascadeType.REFRESH})
    Authority authority;
    @Column
    String lasttime;
    @Column
    String email;
}
