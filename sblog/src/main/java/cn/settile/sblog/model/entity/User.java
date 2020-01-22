package cn.settile.sblog.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : lzjyz
 * @date : 2019-08-19 20:35
 */
@Data
@Entity
@Table(name = "user_info")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;       // 用户id
    @Column(name = "uname",nullable = false,unique = true)
    private String uname;   // 登录名，不可改
    @Column(name = "nick",nullable = false,unique = true)
    private String nick;    // 用户昵称，可改
    @Column(nullable = false)
    String password;  //应加密存储
    @Column(name = "salt",nullable = false)
    private String salt;    // 加密盐值
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "created",nullable = false)
    private Date created;   // 创建时间
    @Column(name = "updated",nullable = false)
    private Date updated;   // 修改时间
    @JoinColumn(nullable = false) @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private Role roles;    //用户所有角色值，用于shiro做角色权限的判断
}
