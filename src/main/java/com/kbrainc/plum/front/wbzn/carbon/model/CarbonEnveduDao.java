package com.kbrainc.plum.front.wbzn.carbon.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("front.carbonEnveduDao")
public interface CarbonEnveduDao {
    
    public List<CarbonEnveduVo> selectEnveduList(CarbonEnveduVo carbonEnveduVo) throws Exception;
    
    public List<CarbonBannerVo> selectBannerList(CarbonBannerVo carbonBannerVo) throws Exception;
    
    public List<CarbonEnveduVo> selectEnveduYrList(CarbonEnveduVo carbonEnveduVo) throws Exception;
    
    public List<CarbonEnveduVo> selectEnveduMmList(CarbonEnveduVo carbonEnveduVo) throws Exception;
    
    public List<CarbonPrgrmgdVo> selectPrgrmgdYrList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;
    
    public List<CarbonPrgrmgdVo> selectPrgrmgdMmList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;

    public CarbonEnveduVo selectEnveduInfo(CarbonEnveduVo carbonEnveduVo) throws Exception;
    
    public List<CarbonPrgrmgdVo> selectPrgrmgdList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;
    
    public CarbonPrgrmgdVo selectPrgrmgdInfo(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;
}
