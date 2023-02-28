package com.kbrainc.plum.mng.prtpn.eduSarea.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.prtpn.eduSarea.model.EduSareaVo;



/**
* 유아환경교육 -> 운영권역관리 서비스 인터페이스
**
<pre>
* com.kbrainc.plum.mng.prtpn.eduSarea.service
* - EduSareaService.java
* </pre>
**
@ClassName : EduSareaService
* @Description : 유아환경교육 -> 운영권역관리 서비스 인터페이스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface EduSareaService {
    
    /**
    * 운영권역관리 게시글 목록 조회
    *
    * @Title : selectEduSareaList
    * @Description : 운영권역관리 게시글 목록 조회
    * @param eduSarea 운영권역관리 객체
    * @throws Exception 예외
    * @return List<EduSareaVo>
    */
    public List<EduSareaVo> selectEduSareaList(EduSareaVo eduSarea) throws Exception;
    
    /**
    * 운영권역관리 게시글 등록
    *
    * @Title : insertEduSarea
    * @Description : 운영권역관리 게시글 등록
    * @param eduSarea 운영권역관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertEduSarea(EduSareaVo eduSarea) throws Exception;
    
    /**
    * 운영권역관리 게시글 상세조회
    *
    * @Title : selectEduSareaInfo
    * @Description : 운영권역관리 게시글 상세조회
    * @param eduSarea 운영권역관리 객체
    * @throws Exception 예외
    * @return EduSareaVo
    */
    public EduSareaVo selectEduSareaInfo(EduSareaVo eduSarea) throws Exception;
    
    /**
    * 운영권역관리 게시글 수정
    *
    * @Title : updateEduSarea
    * @Description : 운영권역관리 게시글 수정
    * @param eduSarea 운영권역관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateEduSarea(EduSareaVo eduSarea) throws Exception;

    /**
     * 운영권역관리 세부지역설정 수정
     *
     * @Title : updateEduSareaSignguSetting
     * @Description : 운영권역관리 세부지역설정 수정
     * @param eduSarea 운영권역관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateEduSareaSignguSetting(EduSareaVo eduSarea) throws Exception;
    
    /**
    * 운영권역_교육유형 조회.
    **
    @Title : selectClssrmEduTypeCd
    * @Description : 운영권역_교육유형 조회.
    * @param clssrmId
    * @return
    * @throws Exception
    * @return String
    */
    public EduSareaVo selectClssrmEduTypeCd(String clssrmId) throws Exception;
    
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
     * 지역설정 목록을 조회.
     **
    @Title : selectCtprvnCdList
     * @Description : 지역설정 목록을 조회
     * @param sareaid
     * @return
     * @throws Exception
     * @return List<EduSareaVo>
     */
    public List<EduSareaVo> selectCtprvnCdList(int sareaid) throws Exception;
}
