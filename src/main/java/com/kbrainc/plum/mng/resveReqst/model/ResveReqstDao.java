package com.kbrainc.plum.mng.resveReqst.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
* 시설 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.resveReqst.model
* - ResveReqstDao.java
* </pre>
*
* @ClassName : ResveReqstDao
* @Description : 언론보도관리 Dao 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface ResveReqstDao {
    
    /**
    * 예약내역 목록 조회
    *
    * @Title : selectResveReqstList
    * @Description : 예약내역 목록 조회
    * @param resveReqstVo 예약내역 객체
    * @throws Exception 예외
    * @return List<ResveReqstVo>
    */
    public List<ResveReqstVo> selectResveReqstList(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 상태변경이력 목록 조회
    *
    * @Title : selectResveReqstList
    * @Description : 상태변경이력 목록 조회
    * @param resveReqstVo 예약내역 객체
    * @throws Exception 예외
    * @return List<ResveReqstVo>
    */
    public List<ResveReqstVo> selectResveReqstHstryList(ResveReqstVo resveReqstVo) throws Exception;

    /**
     * 마지막 시설번호 조회.
     *
     * @Title : selectFcltNo
     * @Description : 마지막 시설번호 조회.
     * @param resveReqstVo
     * @return ResveReqstVo
     * @throws Exception 예외
     */
     public ResveReqstVo selectFcltNo(ResveReqstVo resveReqstVo) throws Exception;
     
     /**
      * 시설명 중복 조회.
      *
      * @Title : checkDuplicateFcltNm
      * @Description : 시설명 중복 조회.
      * @param resveReqstVo
      * @return ResveReqstVo
      * @throws Exception 예외
      */
     public int checkDuplicateFcltNm(ResveReqstVo resveReqstVo) throws Exception;
     
     /**
      * 회원 기관데이터 조회.
      *
      * @Title : selectUserInst
      * @Description : 회원 기관데이터 조회.
      * @param instid
      * @return ResveReqstVo
      * @throws Exception 예외
      */
     public ResveReqstVo selectUserInst(Integer instid) throws Exception;
     
    /**
    * 시설 등록
    *
    * @Title : insertResveReqst
    * @Description : 시설 등록
    * @param resveReqstVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertResveReqst(ResveReqstVo resveReqstVo) throws Exception;
    
    /**
    * 예약내역 상세정보 조회
    *
    * @Title : selectResveReqstInfo
    * @Description : 예약내역 상세정보
    * @param resveReqstVo 예약내역 객체
    * @throws Exception 예외
    * @return ResveReqstVo
    */
    public ResveReqstVo selectResveReqstInfo(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 상태변경이력 사유 상세정보 조회
    *
    * @Title : selectResveReqstInfo
    * @Description : 상태변경이력 사유 상세정보
    * @param resveReqstVo 예약내역 객체
    * @throws Exception 예외
    * @return ResveReqstVo
    */
    public ResveReqstVo selectHstryInfo(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 시설 수정
    *
    * @Title : updateResveReqst
    * @Description : 시설 수정
    * @param resveReqstVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateResveReqst(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 상태변경이력 추가
    *
    * @Title : insertHstry
    * @Description : 상태변경이력 추가
    * @param resveReqstVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertHstry(ResveReqstVo resveReqstVo) throws Exception;
}
