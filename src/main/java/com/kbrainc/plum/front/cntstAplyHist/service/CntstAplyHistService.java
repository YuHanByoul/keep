package com.kbrainc.plum.front.cntstAplyHist.service;

import java.util.List;

import com.kbrainc.plum.front.cntst.model.CntstAplySchlVo;
import com.kbrainc.plum.front.cntstAplyHist.model.CntstAplyHistVo;

public interface CntstAplyHistService {

    public List<CntstAplyHistVo> selectCntstAplyHistList(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    public CntstAplyHistVo selectCntstAplyHistInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    public List<CntstAplyHistVo> selectCntstFldMapngInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    public int updateCntstAplyHist(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    public List<CntstAplyHistVo> selectCntstAplySchlHistInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    public int updateCntstAplySchlHist(List<CntstAplyHistVo> cntstAplyHistVoList) throws Exception;

}
