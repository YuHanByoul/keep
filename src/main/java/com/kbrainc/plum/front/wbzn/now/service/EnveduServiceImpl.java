package com.kbrainc.plum.front.wbzn.now.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.wbzn.now.model.BannerVo;
import com.kbrainc.plum.front.wbzn.now.model.EnveduDao;
import com.kbrainc.plum.front.wbzn.now.model.EnveduVo;
import com.kbrainc.plum.front.wbzn.now.model.PrgrmgdVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

@Service("front.enveduServiceImpl")
@Alias("front.enveduServiceImpl")
public class EnveduServiceImpl extends PlumAbstractServiceImpl implements EnveduService{
    
    @Resource(name = "front.enveduDao")
    private EnveduDao enveduDao;
    
    @Override
    public List<EnveduVo> selectEnveduList(EnveduVo enveduVo) throws Exception {
        return enveduDao.selectEnveduList(enveduVo);
    }
    
    @Override
    public List<BannerVo> selectBannerList(BannerVo enveduVo) throws Exception {
        return enveduDao.selectBannerList(enveduVo);
    }
    
    @Override
    public List<EnveduVo> selectEnveduYrList(EnveduVo enveduVo) throws Exception {
        return enveduDao.selectEnveduYrList(enveduVo);
    }
    
    @Override
    public List<EnveduVo> selectEnveduMmList(EnveduVo enveduVo) throws Exception {
        return enveduDao.selectEnveduMmList(enveduVo);
    }
    
    @Override
    public List<PrgrmgdVo> selectPrgrmgdYrList(PrgrmgdVo prgrmgdVo) throws Exception {
        return enveduDao.selectPrgrmgdYrList(prgrmgdVo);
    }
    
    @Override
    public List<PrgrmgdVo> selectPrgrmgdMmList(PrgrmgdVo prgrmgdVo) throws Exception {
        return enveduDao.selectPrgrmgdMmList(prgrmgdVo);
    }
    
    @Override
    public EnveduVo selectEnveduInfo(EnveduVo enveduVo) throws Exception{
        return enveduDao.selectEnveduInfo(enveduVo);
    }
    
    @Override
    public List<PrgrmgdVo> selectPrgrmgdList(PrgrmgdVo prgrmgdVo) throws Exception {
        return enveduDao.selectPrgrmgdList(prgrmgdVo);
    }
    
    @Override
    public PrgrmgdVo selectPrgrmgdInfo(PrgrmgdVo prgrmgdVo) throws Exception{
        return enveduDao.selectPrgrmgdInfo(prgrmgdVo);
    }



}
