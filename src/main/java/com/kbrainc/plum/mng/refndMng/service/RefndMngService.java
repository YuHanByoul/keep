package com.kbrainc.plum.mng.refndMng.service;

import com.kbrainc.plum.mng.refndMng.model.RefndMngVo;

import java.util.List;

/**
* 시설 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.refndMng.service
* - RefndMngService.java
* </pre>
*
* @ClassName : RefndMngService
* @Description : 시설 서비스 인터페이스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface RefndMngService {
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectRefndMngList
    * @Description : 시설 목록 조회
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return List<RefndMngVo>
    */
    public List<RefndMngVo> selectRefndMngList(RefndMngVo refndMngVo) throws Exception;
    
    /**
     * 시설명 중복 조회.
     *
     * @Title : checkDuplicateFcltNm
     * @Description : 시설명 중복 조회
     * @param refndMngVo
     * @throws Exception
     * @return int
     */
    public int checkDuplicateFcltNm(RefndMngVo refndMngVo) throws Exception;
    
    /**
     * 마지막 시설번호 조회.
     *
     * @Title : selectFcltNo
     * @Description : 마지막 시설번호 (제일 큰 값) 조회
     * @param refndMngVo
     * @throws Exception
     * @return RefndMngVo
     */
    public RefndMngVo selectFcltNo(RefndMngVo refndMngVo) throws Exception;
    
    /**
     * 회원 기관데이터 조회.
     *
     * @Title : selectUserInst
     * @Description : 회원 기관데이터 조회
     * @param instid
     * @throws Exception
     * @return FcltRegMngVo
     */
    public RefndMngVo selectUserInst(Integer instid) throws Exception;
    
    /**
    * 시설 등록
    *
    * @Title : insertRefndMng
    * @Description : 시설 게시글 등록
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertRefndMng(RefndMngVo refndMngVo) throws Exception;

    /**
    * 시설 상세정보
    *
    * @Title : selectRefndMngInfo
    * @Description : 시설 상세정보
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return RefndMngVo
    */
    public RefndMngVo selectRefndMngInfo(RefndMngVo refndMngVo) throws Exception;

    /**
    * 시설 수정
    *
    * @Title : updateRefndMng
    * @Description : 시설 수정
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateRefndMng(RefndMngVo refndMngVo) throws Exception;

    /**
    * 시설 삭제
    *
    * @Title : deleteRefndMng
    * @Description : 게시글 삭제
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int deleteRefndMng(RefndMngVo refndMngVo) throws Exception;   
    
    /**
     * 공간 목록 조회
     *
     * @Title : selectRefndMngSpceList
     * @Description : 공간 목록 조회
     * @param refndMngVo 시설 객체
     * @throws Exception 예외
     * @return List<RefndMngVo>
     */
     public List<RefndMngVo> selectRefndMngSpceList(RefndMngVo refndMngVo) throws Exception;
}
