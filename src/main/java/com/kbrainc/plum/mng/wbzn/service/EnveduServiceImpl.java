package com.kbrainc.plum.mng.wbzn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.mmnws.model.MmnwsDao;
import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;
import com.kbrainc.plum.mng.wbzn.model.EnveduDao;
import com.kbrainc.plum.mng.wbzn.model.EnveduVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

@Service
public class EnveduServiceImpl extends PlumAbstractServiceImpl implements EnveduService{
    
    @Autowired
    private EnveduDao enveduDao;
    
    public List<EnveduVo> selectEnveduList(EnveduVo enveduVo) throws Exception {
        return enveduDao.selectEnveduList(enveduVo);
    }
    
    public int insertEnvedu(EnveduVo enveduVo) throws Exception{
        return enveduDao.insertEnvedu(enveduVo);
    }
    
    public EnveduVo selectEnveduInfo(EnveduVo enveduVo) throws Exception{
        return enveduDao.selectEnveduInfo(enveduVo);
    }
    
    public int updateEnvedu(EnveduVo enveduVo) throws Exception{
        return enveduDao.updateEnvedu(enveduVo);
    }
    
    public int deleteEnvedu(EnveduVo enveduVo) throws Exception{
        return enveduDao.deleteEnvedu(enveduVo);
    }
}
