package com.kbrainc.plum.mng.resveReqst.service;

import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;

import java.util.List;

/**
* 시설 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.resveReqst.service
* - ResveReqstService.java
* </pre>
*
* @ClassName : ResveReqstService
* @Description : 시설 서비스 인터페이스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface ResveReqstService {
    
    /**
    * 예약내역 목록 조회
    *
    * @Title : selectResveReqstList
    * @Description : 예약내역 목록 조회
    * @param resveReqstVo 시설 객체
    * @throws Exception 예외
    * @return List<ResveReqstVo>
    */
    public List<ResveReqstVo> selectResveReqstList(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 상태변경이력 목록 조회
    *
    * @Title : selectResveReqstList
    * @Description : 상태변경이력 목록 조회
    * @param resveReqstVo 시설 객체
    * @throws Exception 예외
    * @return List<ResveReqstVo>
    */
    public List<ResveReqstVo> selectResveReqstHstryList(ResveReqstVo resveReqstVo) throws Exception;

    /**
     * 시설명 중복 조회.
     *
     * @Title : checkDuplicateFcltNm
     * @Description : 시설명 중복 조회
     * @param resveReqstVo
     * @throws Exception
     * @return int
     */
    public int checkDuplicateFcltNm(ResveReqstVo resveReqstVo) throws Exception;
    
    /**
     * 마지막 시설번호 조회.
     *
     * @Title : selectFcltNo
     * @Description : 마지막 시설번호 (제일 큰 값) 조회
     * @param resveReqstVo
     * @throws Exception
     * @return ResveReqstVo
     */
    public ResveReqstVo selectFcltNo(ResveReqstVo resveReqstVo) throws Exception;
    
    /**
     * 회원 기관데이터 조회.
     *
     * @Title : selectUserInst
     * @Description : 회원 기관데이터 조회
     * @param instid
     * @throws Exception
     * @return FcltRegMngVo
     */
    public ResveReqstVo selectUserInst(Integer instid) throws Exception;
    
    /**
    * 시설 등록
    *
    * @Title : insertResveReqst
    * @Description : 시설 게시글 등록
    * @param resveReqstVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertResveReqst(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 예약내역 상세정보
    *
    * @Title : selectResveReqstInfo
    * @Description : 예약내역 상세정보
    * @param resveReqstVo 예약내역 객체
    * @throws Exception 예외
    * @return ResveReqstVo
    */
    public ResveReqstVo selectResveReqstInfo(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 상태변경이력 사유 상세정보
    *
    * @Title : selectResveReqstInfo
    * @Description : 상태변경이력 사유 상세정보
    * @param resveReqstVo 상태변경이력 사유 객체
    * @throws Exception 예외
    * @return ResveReqstVo
    */
    public ResveReqstVo selectHstryInfo(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 예약내역 수정
    *
    * @Title : updateResveReqst
    * @Description : 예약신청 수정
    * @param resveReqstVo 예약신청 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateResveReqst(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 상태변경이력 추가
    *
    * @Title : insertHstry
    * @Description : 예약신청 수정
    * @param resveReqstVo 예약신청 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertHstry(ResveReqstVo resveReqstVo) throws Exception;
}
