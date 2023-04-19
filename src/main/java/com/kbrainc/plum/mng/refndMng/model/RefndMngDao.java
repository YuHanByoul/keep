package com.kbrainc.plum.mng.refndMng.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

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
    * 시설 상세정보 조회
    *
    * @Title : selectRefndMngInfo
    * @Description : 시설 상세정보
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return RefndMngVo
    */
    public RefndMngVo selectRefndMngInfo(RefndMngVo refndMngVo) throws Exception;
    
    
    /**
     * 공간 목록 조회
     *
     * @Title : selectRefndMngCompleteList
     * @Description : 공간 목록 조회
     * @param refndMngVo 시설 객체
     * @throws Exception 예외
     * @return List<RefndMngVo>
     */
     public List<RefndMngVo> selectRefndMngCompleteList(RefndMngVo refndMngVo) throws Exception;

    /**
     * 입금 확인 처리
     *
     * @Title : updateRefndComplete
     * @Description : 입금 확인 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateRefndComplete(RefndMngVo refndMngVo) throws Exception;

    /**
     * 예약 신청 취소 처리
     *
     * @Title : updateRefndCancel
     * @Description : 예약 신청 취소 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateRefndCancel(RefndMngVo refndMngVo) throws Exception;

    /**
     * 환불완료취소 처리
     *
     * @Title : updateRefndRollback
     * @Description : 예약 신청 취소 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateRefndRollback(RefndMngVo refndMngVo) throws Exception;
}
