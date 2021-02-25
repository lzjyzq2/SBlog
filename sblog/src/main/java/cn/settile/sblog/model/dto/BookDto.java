package cn.settile.sblog.model.dto;

import cn.settile.sblog.model.entity.Book;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-05-01 18:16
 */
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class BookDto {

    private long id;
    private long userId;
    private String name;
    private String info;
    private List<SubsectionDto> subsections;

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", subsections=" + subsections +
                '}';
    }

    public static BookDto of(Book book){
        return BookDto.builder()
                .id(book.getId())
                .userId(book.getUser().getUid())
                .name(book.getName())
                .info(book.getInfo())
                .subsections(SubsectionDto.of(book.getSubsections()))
                .build();
    }
}
