package cn.settile.sblog.repository;

import cn.settile.sblog.model.entity.Subsection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author lzjyz
 */
@Repository
public interface SubsectionDao extends JpaRepository<Subsection,Long> {

    /** 查询用户是否存在指定Id的卷
     * @param id id
     * @param username 用户名
     * @return {@code false}:不存在,{@code true}:存在
     */
    boolean existsSubsectionByIdAndBook_User_Username(long id,String username);

    /** 获取BookId的所有卷
     * @param id bookId
     * @return Set<Subsection>
     */
    Set<Subsection> findSubsectionsByBook_Id(long id);
}
