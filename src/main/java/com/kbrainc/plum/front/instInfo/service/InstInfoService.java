package com.kbrainc.plum.front.instInfo.service;

import java.util.List;

import com.kbrainc.plum.front.envEdu.model.PrgrmVo;
import com.kbrainc.plum.front.instInfo.model.InstInfoVo;
import com.kbrainc.plum.front.instInfo.model.InstPicVo;

public interface InstInfoService {

    public InstInfoVo selectInstInfo(InstInfoVo instInfoVo) throws Exception;

    public List<InstPicVo> selectInstPictList(InstPicVo instPicVo) throws Exception;

    public InstPicVo selectInstPicInfo(InstPicVo instPicVo) throws Exception;

    public List<InstPicVo> selectPicSearchList(InstPicVo instPicVo) throws Exception;

    public int updateRegInstPic(InstPicVo instPicVo) throws Exception;

    public int updateCancelInstPic(InstPicVo instPicVo) throws Exception;

}
