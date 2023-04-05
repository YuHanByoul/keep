package com.kbrainc.plum.front.cntstAplyHist.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.cntst.model.CntstVo;

import java.util.List;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.front.cntstAplyHist.model
* - CntstAplyHistDao.java
* </pre>
*
* @ClassName : CntstAplyHistDao
* @Description : TODO
* @author : JD
* @date : 2023. 2. 24.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.cntstAplyHistDao")
public interface CntstAplyHistDao {

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
    * 공모전 참여 이력(환경일기장신청) 상세정보 조회
    *
    * @Title : selectCntstAplySchlHistInfo
    * @Description : 공모전 참여 이력(환경일기장신청) 상세정보 조회
    * @param cntstAplyHistVo
    * @throws Exception
    * @return List<CntstAplyHistVo>
    */
    public CntstAplyHistVo selectCntstAplySchlHistInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception;

    /**
    * 공모전 참여 이력(환경일기장신청) 수정
    *
    * @Title : updateCntstAplySchlHist
    * @Description : 공모전 참여 이력(환경일기장신청) 수정
    * @param cntstAplyHistVoList
    * @throws Exception
    * @return int
    */
    public int updateCntstAplySchlHist(CntstAplyHistVo cntstAplyHistVo) throws Exception;
    
}
