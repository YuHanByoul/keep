package com.kbrainc.plum.mng.rcpmnyBfe.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

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
    public List<RcpmnyBfeVo> selectRcpmnyBfeList(RcpmnyBfeVo rcpmnyBfeVo) throws Exception;

    /**
    * 입금 전 상세정보 조회
    *
    * @Title : selectRcpmnyBfeInfo
    * @Description : 입금 전 상세정보
    * @param rcpmnyBfeVo 입금 전 객체
    * @throws Exception 예외
    * @return RcpmnyBfeVo
    */
    public RcpmnyBfeVo selectRcpmnyBfeInfo(RcpmnyBfeVo rcpmnyBfeVo) throws Exception;

    /**
    * 입금 확인 전 상세정보 조회
    *
    * @Title : selectDsptCheckInfo
    * @Description : 입금 확인 전 상세정보
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
}
