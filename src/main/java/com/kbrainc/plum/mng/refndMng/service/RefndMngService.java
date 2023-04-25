package com.kbrainc.plum.mng.refndMng.service;

import java.util.List;

import com.kbrainc.plum.mng.refndMng.model.RefndMngVo;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;

/**
* 환불관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.refndMng.service
* - RefndMngService.java
* </pre>
*
* @ClassName : RefndMngService
* @Description : 환불관리 서비스 인터페이스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface RefndMngService {
    
    /**
    * 환불 신청 목록 조회 
    *
    * @Title : selectRefndMngList
    * @Description : 환불 신청 목록 조회
    * @param ResveReqstVo  객체
    * @throws Exception 예외
    * @return List<RefndMngVo>
    */
    public List<ResveReqstVo> selectRefndMngList(ResveReqstVo resveReqstVo) throws Exception;
    
    /**
     * 환불 완료 목록 조회
     *
     * @Title : selectRefndMngCompleteList
     * @Description : 환불 완료 목록 조회
     * @param ResveReqstVo 객체
     * @throws Exception 예외
     * @return List<RefndMngVo>
     */
     public List<ResveReqstVo> selectRefndMngCompleteList(ResveReqstVo resveReqstVo) throws Exception;

    /**
     * 환불 완료 처리 
     *
     * @Title : updateRefndComplete
     * @Description : 환불 완료 처리
     * @param ResveReqstVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateRefndComplete(ResveReqstVo resveReqstVo) throws Exception;

    /**
     * 환불 요청 취소 처리
     *
     * @Title : updateRefndCancel
     * @Description : 환불 요청 취소 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateRefndCancel(ResveReqstVo resveReqstVo) throws Exception;

    /**
     * 환불완료취소 처리
     *
     * @Title : updateRefndRollback
     * @Description : 환불완료취소 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateRefndRollback(ResveReqstVo resveReqstVo) throws Exception;
}
