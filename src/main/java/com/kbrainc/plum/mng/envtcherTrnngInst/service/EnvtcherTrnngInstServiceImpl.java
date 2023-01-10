package com.kbrainc.plum.mng.envtcherTrnngInst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstDao;
import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

@Service
public class EnvtcherTrnngInstServiceImpl extends PlumAbstractServiceImpl implements EnvtcherTrnngInstService{

    @Autowired
    private EnvtcherTrnngInstDao envtcherTrnngInstDao;
    
    public int insertEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        return envtcherTrnngInstDao.insertEnvtcherTrnngInst(envtcherTrnngInstVo);
    }

    public int updateEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        return envtcherTrnngInstDao.updateEnvtcherTrnngInst(envtcherTrnngInstVo);
    }
    
    public List<EnvtcherTrnngInstVo> selectEnvtcherTrnngInstList(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        return envtcherTrnngInstDao.selectEnvtcherTrnngInstList(envtcherTrnngInstVo);
    }
    
    public EnvtcherTrnngInstVo selectEnvtcherTrnngInstInfo(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        return envtcherTrnngInstDao.selectEnvtcherTrnngInstInfo(envtcherTrnngInstVo);
    }
}
