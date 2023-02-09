package com.kbrainc.plum.mng.prtpn.eduSarea.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVo;

/**
* 유아환경교육 -> 운영권역관리 Dao 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.eduSarea.model
* - EduSareaDao.java
* </pre>
**
@ClassName : EduSareaDao
* @Description : 유아환경교육 -> 운영권역관리 Dao 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface EduSareaDao {
    
    /**
    * 운영권역관리 게시글 목록 조회
    *
    * @Title : selectEduSareaList
    * @Description : 운영권역관리 게시글 목록 조회
    * @param eduSareaVo 운영권역관리 객체
    * @throws Exception 예외
    * @return List<EduSareaVo>
    */
    public List<EduSareaVo> selectEduSareaList(EduSareaVo eduSareaVo) throws Exception;
    
    /**
    * 운영권역관리 게시글 등록
    *
    * @Title : insertEduSarea
    * @Description : 운영권역관리 게시글 등록
    * @param eduSareaVo 운영권역관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertEduSarea(EduSareaVo eduSareaVo) throws Exception;

    /**
    * 운영권역관리 지역코드 등록
    **
    @Title : insertCtprvnCd
    * @Description : 운영권역관리 지역코드 등록
    * @param eduSareaVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertCtprvnCd(EduSareaVo eduSareaVo) throws Exception;

    /**
     * 운영권역관리 세부지역설정 수정
     **
    @Title : updateEduSareaSignguSetting
     * @Description : 운영권역관리 세부지역설정 수정
     * @param eduSareaVo
     * @return
     * @throws Exception
     * @return int
     */
    public int updateEduSareaSignguSetting(EduSareaVo eduSareaVo) throws Exception;
    
    /**
    * 운영권역관리 게시글 상세조회
    *
    * @Title : selectEduSareaInfo
    * @Description : 운영권역관리 게시글 상세조회
    * @param eduSareaVo 운영권역관리 객체
    * @throws Exception 예외
    * @return EduSareaVo
    */
    public EduSareaVo selectEduSareaInfo(EduSareaVo eduSareaVo) throws Exception;
    
    /**
    * 운영권역관리 게시글 수정
    *
    * @Title : updateEduSarea
    * @Description : 운영권역관리 게시글 수정
    * @param eduSareaVo 운영권역관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateEduSarea(EduSareaVo eduSareaVo) throws Exception;

    /**
    * 운영권역_교육유형 조회
    **
    * @Title : selectClssrmEduTypeCd
    * @Description : 운영권역_교육유형 조회
    * @param clssrmId
    * @return
    * @throws Exception
    * @return String
    */
    public EduSareaVo selectClssrmEduTypeCd(@Param("clssrmId") String clssrmId) throws Exception;
    
    /**
    * 지역코드 삭제
    **
    @Title : deleteCtprvnCd
    * @Description : 지역코드 삭제
    * @param eduSareaVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteCtprvnCd(EduSareaVo eduSareaVo) throws Exception;
    /**
    * 시군구코드 목록을 조회.
    **
    @Title : selectSignguCodeList
    * @Description : 시군구코드 목록을 조회
    * @param codeVo
    * @return
    * @throws Exception
    * @return List<EduSareaVo>
    */    
    public List<EduSareaVo> selectSignguCodeList(EduSareaVo eduSareaVo) throws Exception;
    /**
    * 시군구코드 삭제
    **
    @Title : deleteEduSareaSignguSetting
    * @Description : 시군구코드 삭제
    * @param eduSareaVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteEduSareaSignguSetting(EduSareaVo eduSareaVo) throws Exception;
}
