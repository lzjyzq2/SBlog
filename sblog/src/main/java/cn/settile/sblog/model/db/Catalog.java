package cn.settile.sblog.model.db;

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
    @Id
    long id;
    String title;
}
