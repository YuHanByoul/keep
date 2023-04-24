package com.kbrainc.plum.mng.rcpmnyBfe.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;

/**
* 입금 전 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.rcpmnyBfe.model
* - RcpmnyBfeDao.java
* </pre>
*
* @ClassName : RcpmnyBfeDao
* @Description : 입금 전 Dao 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface RcpmnyBfeDao {
    
    /**
    * 입금 전 목록 조회
    *
    * @Title : selectRcpmnyBfeList
    * @Description : 입금 전 목록 조회
    * @param rcpmnyBfeVo 입금 전 객체
    * @throws Exception 예외
    * @return List<RcpmnyBfeVo>
    */
    public List<ResveReqstVo> selectRcpmnyBfeList(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 입금 전 상세정보 조회
    *
    * @Title : selectRcpmnyBfeInfo
    * @Description : 입금 전 상세정보
    * @param rcpmnyBfeVo 입금 전 객체
    * @throws Exception 예외
    * @return RcpmnyBfeVo
    */
    public ResveReqstVo selectRcpmnyBfeInfo(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 입금 확인 전 상세정보 조회
    *
    * @Title : selectDsptCheckInfo
    * @Description : 입금 확인 전 상세정보
    * @param rcpmnyBfeVo 입금 전 객체
    * @throws Exception 예외
    * @return RcpmnyBfeVo
    */
    public ResveReqstVo selectDsptCheckInfo(ResveReqstVo resveReqstVo) throws Exception;

    /**
     * 입금 확인 처리
     *
     * @Title : updateDsptCheck
     * @Description : 입금 확인 처리
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateDsptCheck(ResveReqstVo resveReqstVo) throws Exception;

    /**
     * 예약 신청 취소 처리
     *
     * @Title : updateResveCancel
     * @Description : 예약 신청 취소 처리
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateResveCancel(ResveReqstVo resveReqstVo) throws Exception;
    /**
     * 현재 해당 신청건의 예약 일정중 진행중인 예약이 있는지 확인
     *
     * @Title : isThereResveNow
     * @Description : 현재 해당 신청건의 예약 일정중 진행중인 예약이 있는지 확인
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public String isThereResveNow(ResveReqstVo resveReqstVo) throws Exception;
    
}
