package com.kbrainc.plum.front.cntstAplyHist.service;

import java.util.List;

import com.kbrainc.plum.front.cntst.model.CntstAplySchlVo;
import com.kbrainc.plum.front.cntstAplyHist.model.CntstAplyHistVo;

/**
* 공모전 참여 이력 Service 클래스
*
* <pre>
* com.kbrainc.plum.front.cntstAplyHist.service
* - CntstAplyHistService.java
* </pre>
*
* @ClassName : CntstAplyHistService
* @Description : 공모전 참여 이력 Service 클래스
* @author : JD
* @date : 2023. 2. 24.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface CntstAplyHistService {

    /**
    * 공모전 참여 이력 목록 조회
    *
    * @Title : selectCntstAplyHistList
    * @Description : 공모전 참여 이력 목록 조회
    * @param cntstAplyHistVo
    * @throws Exception
    * @return List<CntstAplyHistVo>
    */
    public List<CntstAplyHistVo> selectCntstAplyHistList(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    /**
    * 공모전 참여 이력 상세정보 조회
    *
    * @Title : selectCntstAplyHistInfo
    * @Description : 공모전 참여 이력 상세정보 조회
    * @param cntsRectVo
    * @return
    * @throws Exception
    * @return CntstAplyHistVo
    */
    public CntstAplyHistVo selectCntstAplyHistInfo(CntstAplyHistVo cntsRectVo) throws Exception;

    /**
    * 공모전 참여분야 정보 조회
    *
    * @Title : selectCntstFldMapngInfo
    * @Description : 공모전 참여분야 정보 조회
    * @param cntstAplyHistVo
    * @throws Exception
    * @return List<CntstAplyHistVo>
    */
    public List<CntstAplyHistVo> selectCntstFldMapngInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    /**
    * 공모전 참여 이력 수정
    *
    * @Title : updateCntstAplyHist
    * @Description : 공모전 참여 이력 수정
    * @param cntstAplyHistVo
    * @throws Exception
    * @return int
    */
    public int updateCntstAplyHist(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    /**
    * 공모전 참여 이력(환경방학 일기장 프로젝트) 상세정보 조회
    *
    * @Title : selectCntstAplySchlHistInfo
    * @Description : 공모전 참여 이력(환경방학 일기장 프로젝트) 상세정보 조회
    * @param cntstAplyHistVo
    * @throws Exception
    * @return List<CntstAplyHistVo>
    */
    public List<CntstAplyHistVo> selectCntstAplySchlHistInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    /**
    * 공모전 참여 이력(환경방학 일기장 프로젝트) 수정
    *
    * @Title : updateCntstAplySchlHist
    * @Description : 공모전 참여 이력(환경방학 일기장 프로젝트) 수정
    * @param cntstAplyHistVoList
    * @throws Exception
    * @return int
    */
    public int updateCntstAplySchlHist(List<CntstAplyHistVo> cntstAplyHistVoList) throws Exception;
    
}
