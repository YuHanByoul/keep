package com.kbrainc.plum.front.wbzn.carbon.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.wbzn.carbon.model.CarbonBannerVo;
import com.kbrainc.plum.front.wbzn.carbon.model.CarbonEnveduDao;
import com.kbrainc.plum.front.wbzn.carbon.model.CarbonEnveduVo;
import com.kbrainc.plum.front.wbzn.carbon.model.CarbonPrgrmgdVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

@Service("front.carbonEnveduServiceImpl")
@Alias("front.carbonEnveduServiceImpl")
public class CarbonEnveduServiceImpl extends PlumAbstractServiceImpl implements CarbonEnveduService{
    
    @Resource(name = "front.carbonEnveduDao")
    private CarbonEnveduDao carbonEnveduDao;
    
    @Override
    public List<CarbonEnveduVo> selectEnveduList(CarbonEnveduVo carbonEnveduVo) throws Exception {
        return carbonEnveduDao.selectEnveduList(carbonEnveduVo);
    }
    
    @Override
    public List<CarbonBannerVo> selectBannerList(CarbonBannerVo carbonBannerVo) throws Exception {
        return carbonEnveduDao.selectBannerList(carbonBannerVo);
    }
    
    @Override
    public List<CarbonEnveduVo> selectEnveduYrList(CarbonEnveduVo carbonEnveduVo) throws Exception {
        return carbonEnveduDao.selectEnveduYrList(carbonEnveduVo);
    }
    
    @Override
    public List<CarbonEnveduVo> selectEnveduMmList(CarbonEnveduVo carbonEnveduVo) throws Exception {
        return carbonEnveduDao.selectEnveduMmList(carbonEnveduVo);
    }
    
    @Override
    public List<CarbonPrgrmgdVo> selectPrgrmgdYrList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        return carbonEnveduDao.selectPrgrmgdYrList(carbonPrgrmgdVo);
    }
    
    @Override
    public List<CarbonPrgrmgdVo> selectPrgrmgdMmList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        return carbonEnveduDao.selectPrgrmgdMmList(carbonPrgrmgdVo);
    }
    
    @Override
    public CarbonEnveduVo selectEnveduInfo(CarbonEnveduVo carbonEnveduVo) throws Exception{
        return carbonEnveduDao.selectEnveduInfo(carbonEnveduVo);
    }
    
    @Override
    public List<CarbonPrgrmgdVo> selectPrgrmgdList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        return carbonEnveduDao.selectPrgrmgdList(carbonPrgrmgdVo);
    }
    
    @Override
    public CarbonPrgrmgdVo selectPrgrmgdInfo(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception{
        return carbonEnveduDao.selectPrgrmgdInfo(carbonPrgrmgdVo);
    }



}
