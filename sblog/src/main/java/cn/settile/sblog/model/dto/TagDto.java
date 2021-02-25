package cn.settile.sblog.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lzjyz
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class TagDto {
    private int id;
    private String name;
    /**
     * 描述
     */
    private String info;

    @Builder.Default
    private int articles = 0;
}
