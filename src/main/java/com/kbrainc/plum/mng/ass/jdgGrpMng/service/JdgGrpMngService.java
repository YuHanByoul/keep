package com.kbrainc.plum.mng.ass.jdgGrpMng.service;

import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpExpertVo;
import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpVo;

import java.util.List;

public interface JdgGrpMngService {
    public List<JdgGrpVo> selectJdgGrpList(JdgGrpVo jdgGrpVo) throws Exception;

    public JdgGrpVo selectJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception;

    public List<JdgGrpExpertVo> selectJdgGrpExpertList(JdgGrpVo jdgGrpVo) throws Exception;

    public List<JdgGrpExpertVo> selectJdgGrpMngExpertSearchList(JdgGrpExpertVo jdgGrpExpertVo) throws Exception;

    public int insertJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception;

    public int updateJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception;

}
