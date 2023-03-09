package com.kbrainc.plum.front.instInfo.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.envEdu.model.PrgrmVo;

@Mapper("front.instInfoDao")
public interface InstInfoDao {

    public InstInfoVo selectInstInfo(InstInfoVo instInfoVo) throws Exception;
    
    public int updateInstInfo(InstInfoVo instInfoVo) throws Exception;

    public List<InstPicVo> selectInstPictList(InstPicVo instPicVo) throws Exception;

    public InstPicVo selectInstPicInfo(InstPicVo instPicVo) throws Exception;

    public List<InstPicVo> selectPicSearchList(InstPicVo instPicVo) throws Exception;

    public int updateRegInstPic(InstPicVo instPicVo) throws Exception;

    public int updateCancelInstPic(InstPicVo instPicVo) throws Exception;

}
