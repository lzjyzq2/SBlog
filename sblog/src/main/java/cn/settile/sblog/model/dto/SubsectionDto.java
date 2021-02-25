package cn.settile.sblog.model.dto;

import cn.settile.sblog.model.entity.Subsection;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-05-01 18:17
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class SubsectionDto {

    private long id;
    private String name;
    private List<ArticleDto> articles;

    @Override
    public String toString() {
        return "SubsectionDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", articles=" + articles +
                '}';
    }

    /** 将{@code Subsection}转换为{@code SubsectionDto}
     * @param subsection Subsection
     * @return SubsectionDto
     */
    public static SubsectionDto of(@NotNull Subsection subsection){
        return SubsectionDto.builder()
                .id(subsection.getId())
                .name(subsection.getName())
                .articles(ArticleDto.of(subsection.getArticles()))
                .build();
    }

    /** 将{@code Set<Subsection>}转换为{@code Set<SubsectionDto>}
     * @param subsections Set<Subsection>
     * @return Set<SubsectionDto>
     */
    public static List<SubsectionDto> of(@NotNull List<Subsection> subsections){
        List<SubsectionDto> subsectionDtos = new ArrayList<>();
        subsections.forEach(subsection -> {
            subsectionDtos.add(SubsectionDto.of(subsection));
        });
        return subsectionDtos;
    }
}
