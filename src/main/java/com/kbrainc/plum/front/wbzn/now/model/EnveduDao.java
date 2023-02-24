package com.kbrainc.plum.front.wbzn.now.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("front.enveduDao")
public interface EnveduDao {
    
    public List<EnveduVo> selectEnveduList(EnveduVo enveduVo) throws Exception;
    
    public List<BannerVo> selectBannerList(BannerVo enveduVo) throws Exception;
    
    public List<EnveduVo> selectEnveduYrList(EnveduVo enveduVo) throws Exception;
    
    public List<EnveduVo> selectEnveduMmList(EnveduVo enveduVo) throws Exception;
    
    public List<PrgrmgdVo> selectPrgrmgdYrList(PrgrmgdVo prgrmgdVo) throws Exception;
    
    public List<PrgrmgdVo> selectPrgrmgdMmList(PrgrmgdVo prgrmgdVo) throws Exception;

    public EnveduVo selectEnveduInfo(EnveduVo enveduVo) throws Exception;
    
    public List<PrgrmgdVo> selectPrgrmgdList(PrgrmgdVo prgrmgdVo) throws Exception;
    
    public PrgrmgdVo selectPrgrmgdInfo(PrgrmgdVo prgrmgdVo) throws Exception;
}
