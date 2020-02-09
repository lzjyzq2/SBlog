package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzjyz
 */
@Repository
public interface BookDao extends JpaRepository<Book,Long> {
}
