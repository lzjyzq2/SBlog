package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.Subsection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface SubsectionDao extends JpaRepository<Subsection,Long> {

}
