package com.kbrainc.plum.mng.ass.jdgGrpMng.service;

import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpExpertVo;
import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpMngDao;
import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdgGrpMngServiceImpl extends PlumAbstractServiceImpl implements JdgGrpMngService {

    @Autowired
    private JdgGrpMngDao jdgGrpMngDao;

    @Override
    public List<JdgGrpVo> selectJdgGrpList(JdgGrpVo jdgGrpVo) throws Exception {
        return jdgGrpMngDao.selectJdgGrpList(jdgGrpVo);
    }

    @Override
    public JdgGrpVo selectJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception {
        return jdgGrpMngDao.selectJdgGrpInfo(jdgGrpVo);
    }

    @Override
    public List<JdgGrpExpertVo> selectJdgGrpExpertList(JdgGrpVo jdgGrpVo) throws Exception {
        return jdgGrpMngDao.selectJdgGrpExpertList(jdgGrpVo);
    }

    @Override
    public List<JdgGrpExpertVo> selectJdgGrpMngExpertSearchList(JdgGrpExpertVo jdgGrpExpertVo) throws Exception {
        return jdgGrpMngDao.selectJdgGrpMngExpertSearchList(jdgGrpExpertVo);
    }

    @Override
    public int insertJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception {
        int retval = 0;

        retval += jdgGrpMngDao.insertJdgGrpInfo(jdgGrpVo);

        return retval;
    }

    @Override
    public int updateJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception {
        int retval = 0;

        retval += jdgGrpMngDao.updateJdgGrpInfo(jdgGrpVo);

        return retval;
    }
}
