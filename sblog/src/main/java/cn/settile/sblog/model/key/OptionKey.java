package cn.settile.sblog.model.key;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author : lzjyz
 * @date : 2019-08-18 21:46
 */
@Embeddable
@Data
public class OptionKey implements Serializable {
    @Column(name = "id")
    private String id;
    @Column(name = "key")
    private String key;
}
