package com.kbrainc.plum.mng.rcpmnyAfter.service;

import com.kbrainc.plum.mng.rcpmnyAfter.model.RcpmnyAfterVo;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;

import java.util.List;

/**
* 입금 후 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.rcpmnyAfter.service
* - RcpmnyAfterService.java
* </pre>
*
* @ClassName : RcpmnyAfterService
* @Description : 입금 후 서비스 인터페이스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface RcpmnyAfterService {
    
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
    * 입금 후 상세 화면
    *
    * @Title : selectRcpmnyAfterInfo
    * @Description : 입금 후 상세 화면
    * @param rcpmnyAfterVo 입금 후 객체
    * @throws Exception 예외
    * @return RcpmnyAfterVo
    */
    public ResveReqstVo selectRcpmnyAfterInfo(ResveReqstVo resveReqstVo) throws Exception;

    /**
    * 입금 확인 전 상세 화면
    *
    * @Title : selectDsptCheckInfo
    * @Description : 입금 확인 전 상세 화면
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
