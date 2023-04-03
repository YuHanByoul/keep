package com.kbrainc.plum.mng.fcltMng.service;

import java.util.List;

import com.kbrainc.plum.mng.fcltMng.model.FcltMngVo;

/**
* 시설 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.fcltMng.service
* - FcltMngService.java
* </pre>
*
* @ClassName : FcltMngService
* @Description : 시설 서비스 인터페이스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface FcltMngService {
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectFcltMngList
    * @Description : 시설 목록 조회
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return List<FcltMngVo>
    */
    public List<FcltMngVo> selectFcltMngList(FcltMngVo fcltMngVo) throws Exception;
    
    /**
     * 시설명 중복 조회.
     *
     * @Title : checkDuplicateFcltNm
     * @Description : 시설명 중복 조회
     * @param fcltMngVo
     * @throws Exception
     * @return int
     */
    public int checkDuplicateFcltNm(FcltMngVo fcltMngVo) throws Exception;
    
    /**
     * 마지막 시설번호 조회.
     *
     * @Title : selectFcltNo
     * @Description : 마지막 시설번호 (제일 큰 값) 조회
     * @param fcltMngVo
     * @throws Exception
     * @return FcltMngVo
     */
    public FcltMngVo selectFcltNo(FcltMngVo fcltMngVo) throws Exception;
    
    /**
     * 회원 기관데이터 조회.
     *
     * @Title : selectUserInst
     * @Description : 회원 기관데이터 조회
     * @param instid
     * @throws Exception
     * @return FcltRegMngVo
     */
    public FcltMngVo selectUserInst(Integer instid) throws Exception;
    
    /**
    * 시설 등록
    *
    * @Title : insertFcltMng
    * @Description : 시설 게시글 등록
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertFcltMng(FcltMngVo fcltMngVo) throws Exception;

    /**
    * 시설 상세정보
    *
    * @Title : selectFcltMngInfo
    * @Description : 시설 상세정보
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return FcltMngVo
    */
    public FcltMngVo selectFcltMngInfo(FcltMngVo fcltMngVo) throws Exception;

    /**
    * 시설 수정
    *
    * @Title : updateFcltMng
    * @Description : 시설 수정
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateFcltMng(FcltMngVo fcltMngVo) throws Exception;

    /**
    * 시설 삭제
    *
    * @Title : deleteFcltMng
    * @Description : 게시글 삭제
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int deleteFcltMng(FcltMngVo fcltMngVo) throws Exception;   
    
    /**
     * 공간 목록 조회
     *
     * @Title : selectFcltMngSpceList
     * @Description : 공간 목록 조회
     * @param fcltMngVo 시설 객체
     * @throws Exception 예외
     * @return List<FcltMngVo>
     */
     public List<FcltMngVo> selectFcltMngSpceList(FcltMngVo fcltMngVo) throws Exception;
}
