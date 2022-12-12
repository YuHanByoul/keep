package com.kbrainc.plum.mng.wbzn.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;

@Mapper
public interface EnveduDao {
    
    public List<EnveduVo> selectEnveduList(EnveduVo enveduVo) throws Exception;
    
    public int insertEnvedu(EnveduVo enveduVo) throws Exception;
    
    public EnveduVo selectEnveduInfo(EnveduVo enveduVo) throws Exception;
    
    public int updateEnvedu(EnveduVo enveduVo) throws Exception;
    
    public int deleteEnvedu(EnveduVo enveduVo) throws Exception;
}
