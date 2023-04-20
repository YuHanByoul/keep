package com.kbrainc.plum.mng.rcpmnyBfe.service;

import com.kbrainc.plum.mng.rcpmnyBfe.model.RcpmnyBfeVo;

import java.util.List;

/**
* 입금 전 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.rcpmnyBfe.service
* - RcpmnyBfeService.java
* </pre>
*
* @ClassName : RcpmnyBfeService
* @Description : 입금 전 서비스 인터페이스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface RcpmnyBfeService {
    
    /**
    * 입금 전 목록 조회
    *
    * @Title : selectRcpmnyBfeList
    * @Description : 입금 전 목록 조회
    * @param rcpmnyBfeVo 입금 전 객체
    * @throws Exception 예외
    * @return List<RcpmnyBfeVo>
    */
    public List<RcpmnyBfeVo> selectRcpmnyBfeList(RcpmnyBfeVo rcpmnyBfeVo) throws Exception;

    /**
    * 입금 전 상세 화면
    *
    * @Title : selectRcpmnyBfeInfo
    * @Description : 입금 전 상세 화면
    * @param rcpmnyBfeVo 입금 전 객체
    * @throws Exception 예외
    * @return RcpmnyBfeVo
    */
    public RcpmnyBfeVo selectRcpmnyBfeInfo(RcpmnyBfeVo rcpmnyBfeVo) throws Exception;

    /**
    * 입금 확인 전 상세 화면
    *
    * @Title : selectDsptCheckInfo
    * @Description : 입금 확인 전 상세 화면
    * @param rcpmnyBfeVo 입금 전 객체
    * @throws Exception 예외
    * @return RcpmnyBfeVo
    */
    public RcpmnyBfeVo selectDsptCheckInfo(RcpmnyBfeVo rcpmnyBfeVo) throws Exception;

    /**
     * 입금 확인 처리
     *
     * @Title : updateDsptCheck
     * @Description : 입금 확인 처리
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateDsptCheck(RcpmnyBfeVo rcpmnyBfeVo) throws Exception;

    /**
     * 예약 신청 취소 처리
     *
     * @Title : updateResveCancel
     * @Description : 예약 신청 취소 처리
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateResveCancel(RcpmnyBfeVo rcpmnyBfeVo) throws Exception;
    /**
     * 현재 해당 신청건의 예약 일정중 진행중인 예약이 있는지 확인
     *
     * @Title : isThereResveNow
     * @Description : 현재 해당 신청건의 예약 일정중 진행중인 예약이 있는지 확인
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public String isThereResveNow(RcpmnyBfeVo rcpmnyBfeVo) throws Exception;
}
