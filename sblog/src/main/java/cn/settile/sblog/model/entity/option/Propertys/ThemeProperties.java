package cn.settile.sblog.model.entity.option.Propertys;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lzjyzq2
 * @date 2019/8/16
 */
@Data @EqualsAndHashCode(callSuper = true)
public class ThemeProperties  extends Properties{
    String id;
    String activatedTheme;
}
