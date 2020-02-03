package cn.settile.sblog.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : lzjyz
 * @date : 2019-08-19 22:47
 */
@Data
@Entity
@Table(name = "Catalog")
public class Catalog {
    //TODO:将此类移除，由 卷 存储目录
    @Id
    long id;
    String title;
}
