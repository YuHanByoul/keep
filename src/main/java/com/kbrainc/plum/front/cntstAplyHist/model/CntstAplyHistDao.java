package com.kbrainc.plum.front.cntstAplyHist.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.cntst.model.CntstVo;

import java.util.List;

@Mapper("front.cntstAplyHistDao")
public interface CntstAplyHistDao {

    public List<CntstAplyHistVo> selectCntstAplyHistList(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    public CntstAplyHistVo selectCntstAplyHistInfo(CntstAplyHistVo cntsRectVo) throws Exception;

    public List<CntstAplyHistVo> selectCntstFldMapngInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    public int updateCntstAplyHist(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    public List<CntstAplyHistVo> selectCntstAplySchlHistInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    public int updateCntstAplySchlHist(List<CntstAplyHistVo> cntstAplyHistVoList) throws Exception;
}
