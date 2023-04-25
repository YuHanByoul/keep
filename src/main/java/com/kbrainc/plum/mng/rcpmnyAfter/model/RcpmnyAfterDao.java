package com.kbrainc.plum.mng.rcpmnyAfter.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;

/**
* 입금 후 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.rcpmnyAfter.model
* - RcpmnyAfterDao.java
* </pre>
*
* @ClassName : RcpmnyAfterDao
* @Description : 입금 후 Dao 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface RcpmnyAfterDao {
    
    /**
    * 입금 후 목록 조회
    *
    * @Title : selectRcpmnyAfterList
    * @Description : 입금 후 목록 조회
    * @param rcpmnyAfterVo 입금 후 객체
    * @throws Exception 예외
    * @return List<RcpmnyAfterVo>
    */
    public List<ResveReqstVo> selectRcpmnyAfterList(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 입금 후 상세정보 조회
    *
    * @Title : selectRcpmnyAfterInfo
    * @Description : 입금 후 상세정보
    * @param rcpmnyAfterVo 입금 후 객체
    * @throws Exception 예외
    * @return RcpmnyAfterVo
    */
    public ResveReqstVo selectRcpmnyAfterInfo(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 입금 확인 전 상세정보 조회
    *
    * @Title : selectDsptCheckInfo
    * @Description : 입금 확인 전 상세정보
    * @param rcpmnyAfterVo 입금 후 객체
    * @throws Exception 예외
    * @return RcpmnyAfterVo
    */
    public ResveReqstVo selectDsptCheckInfo(ResveReqstVo resveReqstVo) throws Exception;

    /**
     * 입금 확인 취소 처리
     *
     * @Title : updateDsptCheckCancel
     * @Description : 입금 확인 취소 처리
     * @param rcpmnyAfterVo 입금 후 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateDsptCheckCancel(ResveReqstVo resveReqstVo) throws Exception;

    /**
     * 환불요청 처리
     *
     * @Title : updateRefnd
     * @Description : 환불요청 처리
     * @param rcpmnyAfterVo 입금 후 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateRefnd(ResveReqstVo resveReqstVo) throws Exception;
}
