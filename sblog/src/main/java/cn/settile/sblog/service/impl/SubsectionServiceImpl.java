package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.entity.Subsection;
import cn.settile.sblog.repository.SubsectionDao;
import cn.settile.sblog.service.SubsectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : lzjyz
 * @date : 2020-04-29 21:46
 */
@Service
public class SubsectionServiceImpl implements SubsectionService {

    @Autowired
    SubsectionDao subsectionDao;

    @Override
    public Subsection getSubsectionById(long subsectionId) {
        return subsectionDao.getOne(subsectionId);
    }

    @Override
    public boolean existsSubsectionByIdAndUsername(long id, String username) {
        return subsectionDao.existsSubsectionByIdAndBook_User_Username(id,username);
    }

    @Override
    public Subsection save(Subsection subsection) {
        return subsectionDao.save(subsection);
    }

    @Override
    public void delete(Subsection subsection) {
        subsectionDao.delete(subsection);
    }

    @Override
    public void delete(long id) {
        subsectionDao.deleteById(id);
    }
}
