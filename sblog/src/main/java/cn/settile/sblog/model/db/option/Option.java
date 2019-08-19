package cn.settile.sblog.model.db.option;

import cn.settile.sblog.model.db.key.OptionKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lzjyzq2
 * @date 2019/8/18
 */
@Data
@Entity
@Table(name = "Option")
public class Option implements Serializable {
    @EmbeddedId
    private OptionKey optionKey;
    @Column(name = "value")
    private String value;

    public Option(String id, String key, String value) {
        this.optionKey = new OptionKey();
        optionKey.setId(id);
        optionKey.setKey(key);
        this.value = value;
    }

    public Option() {
    }
}
