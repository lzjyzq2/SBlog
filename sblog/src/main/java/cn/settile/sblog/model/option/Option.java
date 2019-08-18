package cn.settile.sblog.model.option;

import cn.settile.sblog.model.key.OptionKey;
import cn.settile.sblog.model.option.Propertys.Properties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
