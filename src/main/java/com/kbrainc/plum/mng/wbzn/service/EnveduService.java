package com.kbrainc.plum.mng.wbzn.service;

import java.util.List;

import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;
import com.kbrainc.plum.mng.wbzn.model.EnveduVo;

public interface EnveduService {
    
    public List<EnveduVo> selectEnveduList(EnveduVo enveduVo) throws Exception;
    
    public int insertEnvedu(EnveduVo enveduVo) throws Exception;
    
    public EnveduVo selectEnveduInfo(EnveduVo enveduVo) throws Exception;
    
    public int updateEnvedu(EnveduVo enveduVo) throws Exception;
    
    public int deleteEnvedu(EnveduVo enveduVo) throws Exception;
}
