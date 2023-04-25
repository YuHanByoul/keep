package com.kbrainc.plum.mng.refndMng.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;

/**
* 시설 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.refndMng.model
* - RefndMngDao.java
* </pre>
*
* @ClassName : RefndMngDao
* @Description : 언론보도관리 Dao 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface RefndMngDao {
    
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
    /**
     * 환불 요청 취소 처리(재승인)전 기예약 없는(재승인 가능한) 신청건 체크 및 호출    
     *
     * @Title : selectSuitableAplyids
     * @Description : 환불 요청 취소 처리(재승인)전 기예약 없는(재승인 가능한) 신청건 체크 및 호출
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public String[] selectSuitableAplyids(ResveReqstVo resveReqstVo) throws Exception;
}
