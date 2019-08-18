package cn.settile.sblog.model.theme;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

public class Theme {
    @Getter@Setter
    String name;
    @Getter@Setter
    boolean canOptions;
}
