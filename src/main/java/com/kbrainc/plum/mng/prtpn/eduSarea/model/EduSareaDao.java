package com.kbrainc.plum.mng.prtpn.eduSarea.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

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
    public int deleteCtprvnCdAll(EduSareaVo eduSareaVo) throws Exception;
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
    
    /**
     * 지역설정 목록을 조회.
     **
     * @Title : selectCtprvnCdList
     * @Description : 지역설정 목록을 조회
     * @param sareaid
     * @return
     * @throws Exception
     * @return List<EduSareaVo>
     */
    public List<EduSareaVo> selectCtprvnCdList(@Param("sareaid") int sareaid) throws Exception;    
    
    /**
    * 지역코드 목록을 공통 테이블에서 조회.
    **
    @Title : selectAddrCtprvnList
    * @Description : 지역코드 목록을 공통 테이블에서 조회.
    * @param 
    * @return
    * @throws Exception
    * @return List<EduSareaVo>
    */
    public List<EduSareaVo> selectAddrCtprvnList() throws Exception;        

    /**
     * 시군구 목록을 공통 테이블에서 조회.
     **
    @Title : selectAddrSignguList
     * @Description : 시군구 목록을 공통 테이블에서 조회.
     * @param 
     * @return
     * @throws Exception
     * @return List<EduSareaVo>
     */
    public List<EduSareaVo> selectAddrSignguList(EduSareaVo eduSareaVo) throws Exception;

    /**
     * 권역 > 시도 테이블에 등록된 개수 조회
     *
     * @param eduSareaVo
     * @return int
     * @throws Exception
     * @Title : countAddrSignguList
     * @Description : TODO [메소드 설명]
     */
    public int countAddrSignguList(EduSareaVo eduSareaVo) throws Exception;

    /**
    * 등록된 지역코드 목록 조회
    *
    * @Title : selectDplctCtprvnCdList
    * @Description : 등록된 지역코드 목록 조회
    * @throws Exception 예외
    * @return List<String>
    */
    public List<String> selectDplctCtprvnCdList(EduSareaVo eduSareaVo) throws Exception;

    /**
     * 세부지역 설정 전부 삭제
     *
     * @param eduSareaVo
     * @return int
     * @throws Exception
     * @Title : deleteEduSareaSignguAll
     * @Description : 세부지역 설정 전부 삭제
     */
    public int deleteEduSareaSignguAll(EduSareaVo eduSareaVo) throws Exception;

    public EduSareaVo selectEduSareaSignguById(EduSareaVo eduSareaVo) throws Exception;

    public int deleteCtprvnCd(EduSareaVo eduSareaVo) throws Exception;
}
