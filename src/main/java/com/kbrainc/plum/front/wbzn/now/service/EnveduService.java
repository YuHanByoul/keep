package com.kbrainc.plum.front.wbzn.now.service;

import java.util.List;

import com.kbrainc.plum.front.wbzn.now.model.BannerVo;
import com.kbrainc.plum.front.wbzn.now.model.EnveduVo;
import com.kbrainc.plum.front.wbzn.now.model.PrgrmgdVo;

public interface EnveduService {
    
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
