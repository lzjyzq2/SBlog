package cn.settile.sblog.service;

import cn.settile.sblog.model.entity.Subsection;

/**
 * @author : lzjyz
 * @date : 2020-04-29 21:41
 */
public interface SubsectionService {

    /** 获取卷
     * @param subsectionId 卷Id
     * @return 卷
     */
    public Subsection getSubsectionById(long subsectionId);

    /**
     * @param id SubsectionId
     * @param username 用户名
     * @return {@code false}:不存在,{@code true}:存在
     */
    public boolean existsSubsectionByIdAndUsername(long id,String username);

    /** 存储 Subsection
     * @param subsection Subsection
     * @return 存储后的完整Subsection实例
     */
    public Subsection save(Subsection subsection);

    /** 删除文集
     * @param subsection 文集
     */
    public void delete(Subsection subsection);

    /** 删除指定ID的文集
     * @param id 文集ID
     */
    public void delete(long id);

}
