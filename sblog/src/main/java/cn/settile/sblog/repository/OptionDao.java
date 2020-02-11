package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.key.OptionKey;
import cn.settile.sblog.model.entity.option.Option;
import cn.settile.sblog.model.entity.option.propertys.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : lzjyz
 * @date : 2019-08-18 20:59
 */
@Repository(value = "OptionDao")
public interface OptionDao extends JpaRepository<Option, OptionKey> {
    <T extends Properties> List<Option> findOptionsByOptionKeyId(String id);
}
