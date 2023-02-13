package com.kbrainc.plum.mng.delvry.service;

import java.util.List;

import com.kbrainc.plum.mng.delvry.model.PcntstVo;

/**
 * 
 * 교부관리 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.delvry.service
 * - DelvryService.java
 * </pre> 
 *
 * @ClassName : DelvryService
 * @Description : 교부관리 서비스 인터페이스 
 * @author : KBRAINC
 * @date : 2023. 02. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface DelvryService {
    
    /**
    * 공모 목록 조회
    *
    * @Title : selectPcntstList 
    * @Description : 교부 목록 조회
    * @param pcntstVo PcntstVo객체
    * @return List<PcntstVo> 교부 목록
    * @throws Exception 예외
    */
    public List<PcntstVo> selectPcntstList(PcntstVo pcntstVo) throws Exception;
    
    /**
    * 공모 정보 조회
    *
    * @Title : selectPcntstInfo 
    * @Description : 공모 정보 조회
    * @param pcntstVo PcntstVo객체
    * @return PcntstVo 공모 정보
    * @throws Exception 예외
    */
    public PcntstVo selectPcntstInfo(PcntstVo pcntstVo) throws Exception;
    
    
//    
//    /**
//     * 교구 목록 조회
//     *
//     * @Title : selectTchaidList 
//     * @Description : 교구 목록 조회
//     * @param jntpurchsTchaidVo JntpurchsTchaidVo객체
//     * @return List<JntpurchsTchaidVo> 교구 목록
//     * @throws Exception 예외
//     */
//    public List<JntpurchsTchaidVo> selectTchaidList(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception;
//    
//    /**
//     * 공동구매모집 상세 정보 조회
//     *
//     * @Title : selectJntpurchsInfo 
//     * @Description : 공동구매모집 상세 정보 조회
//     * @param jntpurchsVo JntpurchsVo객체
//     * @return JntpurchsVo 공동구매모집 상세 정보
//     * @throws Exception 예외
//     */
//    public JntpurchsVo selectJntpurchsInfo(JntpurchsVo jntpurchsVo) throws Exception;
//    
//    /**
//     * 공동구매모집 상품 목록 조회
//     *
//     * @Title : selectGoodsList 
//     * @Description : 공동구매모집 상품 목록 조회
//     * @param jntpurchsTchaidVo JntpurchsTchaidVo객체
//     * @return List<JntpurchsTchaidVo> 공동구매모집 상품 목록
//     * @throws Exception 예외
//     */
//    public List<JntpurchsTchaidVo> selectGoodsList(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception;
//    
//    /**
//     * 공동구매모집 수량별 가격 목록 조회
//     *
//     * @Title : selectAmtsList 
//     * @Description : 공동구매모집 수량별 가격 목록 조회
//     * @param jntpurchsAmtVo JntpurchsAmtVo객체
//     * @return List<JntpurchsAmtVo> 공동구매모집 수량별 가격 목록
//     * @throws Exception 예외
//     */
//    public List<JntpurchsAmtVo> selectAmtList(JntpurchsAmtVo jntpurchsAmtVo) throws Exception;
//    
//    /**
//     * 공동구매모집 정보 업데이트
//     *
//     * @Title : updateJntpurchs
//     * @Description : 공동구매모집 정보 업데이트
//     * @param jntpurchsVo JntpurchsVo 객체
//     * @return int update 로우수
//     * @throws Exception 예외
//     */
//    public int updateJntpurchs(JntpurchsVo jntpurchsVo) throws Exception;
//    
//    /**
//     * 공동구매모집 정보 삭제
//     *
//     * @Title : deleteJntpurchs
//     * @Description : 공동구매모집 정보 삭제
//     * @param jntpurchsVo JntpurchsVo 객체
//     * @return int delete 로우수
//     * @throws Exception 예외
//     */
//    public int deleteJntpurchs(JntpurchsVo jntpurchsVo) throws Exception;
//    
//    /**
//     * 공동구매신청 목록 조회
//     *
//     * @Title : selectJntpurchsOrderList 
//     * @Description : 공동구매신청 목록 조회
//     * @param jntpurchsOrderVo JntpurchsOrderVo객체
//     * @return List<JntpurchsOrderVo> 공동구매신청 목록
//     * @throws Exception 예외
//     */
//    public List<JntpurchsOrderVo> selectJntpurchsOrderList(JntpurchsOrderVo jntpurchsOrderVo) throws Exception;
//    
//    /**
//     * 공동구매신청 상세 정보 조회
//     *
//     * @Title : selectJntpurchsOrderInfo 
//     * @Description : 공동구매신청 상세 정보 조회
//     * @param jntpurchsOrderVo JntpurchsOrderVo객체
//     * @return JntpurchsOrderVo 공동구매신청 상세 정보
//     * @throws Exception 예외
//     */
//    public JntpurchsOrderVo selectJntpurchsOrderInfo(JntpurchsOrderVo jntpurchsOrderVo) throws Exception;
//    
//    /**
//     * 공동구매신청 정보 업데이트
//     *
//     * @Title : updateJntpurchsOrder
//     * @Description : 공동구매모집 정보 업데이트
//     * @param jntpurchsOrderVo JntpurchsOrderVo 객체
//     * @return int update 로우수
//     * @throws Exception 예외
//     */
//    public int updateJntpurchsOrder(JntpurchsOrderVo jntpurchsOrderVo) throws Exception;
    
}