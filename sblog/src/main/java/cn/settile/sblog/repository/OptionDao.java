package cn.settile.sblog.repository;

import cn.settile.sblog.model.key.OptionKey;
import cn.settile.sblog.model.option.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : lzjyz
 * @date : 2019-08-18 20:59
 */
@Repository(value = "OptionDao")
public interface OptionDao extends JpaRepository<Option, OptionKey> {
}
