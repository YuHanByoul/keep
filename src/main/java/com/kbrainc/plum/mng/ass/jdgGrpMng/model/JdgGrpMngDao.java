package com.kbrainc.plum.mng.ass.jdgGrpMng.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface JdgGrpMngDao {
    public List<JdgGrpVo> selectJdgGrpList(JdgGrpVo jdgGrpVo) throws Exception;

    public JdgGrpVo selectJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception;

    public List<JdgGrpExpertVo> selectJdgGrpExpertList(JdgGrpVo jdgGrpVo) throws Exception;

    public List<JdgGrpExpertVo> selectJdgGrpMngExpertSearchList(JdgGrpExpertVo jdgGrpExpertVo) throws Exception;

    public int insertJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception;

    public int updateJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception;
}
