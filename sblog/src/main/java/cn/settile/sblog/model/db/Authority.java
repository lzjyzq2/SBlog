package cn.settile.sblog.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : lzjyz
 * @date : 2019-08-19 20:51
 */
@Entity
@Table(name = "Authority")
public class Authority {
    @Id @Column(nullable = false)
    String name;
    boolean canread;
    boolean canwrite;
    boolean cancomment;
}
