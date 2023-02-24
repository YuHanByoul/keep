package com.kbrainc.plum.front.jntpurchs.service;

import java.util.List;

import com.kbrainc.plum.front.jntpurchs.model.JntpurchsRvwVo;
import com.kbrainc.plum.front.jntpurchs.model.JntpurchsVo;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsAmtVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsOrderVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsTchaidVo;

/**
 * 
 * 환경교육 교구 공동구매 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.jntpurchs.service
 * - JntpurchsService.java
 * </pre> 
 *
 * @ClassName : JntpurchsService
 * @Description : 환경교육 교구 공동구매 서비스 인터페이스 
 * @author : KBRAINC
 * @date : 2023. 02. 17.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface JntpurchsService {
    
    /**
    * 환경교육 교구 공동구매 목록 조회
    *
    * @Title : selectJntpurchsList 
    * @Description : 환경교육 교구 공동구매 목록 조회
    * @param jntpurchsVo JntpurchsVo객체
    * @return List<JntpurchsVo> 환경교육 교구 공동구매 목록
    * @throws Exception 예외
    */
    public List<JntpurchsVo> selectJntpurchsList(JntpurchsVo jntpurchsVo) throws Exception;
    
    /**
    * 환경교육 교구 공동구매 상세 정보 조회
    *
    * @Title : selectJntpurchsInfo 
    * @Description : 환경교육 교구 공동구매 상세 정보 조회
    * @param jntpurchsVo JntpurchsVo객체
    * @return JntpurchsVo 환경교육 교구 공동구매 상세 정보
    * @throws Exception 예외
    */
    public JntpurchsVo selectJntpurchsInfo(JntpurchsVo jntpurchsVo) throws Exception;
    
    /**
    * 환경교육 교구 공동구매 수량별 가격 목록 조회
    *
    * @Title : selectAmtsList 
    * @Description : 환경교육 교구 공동구매 수량별 가격 목록 조회
    * @param jntpurchsAmtVo JntpurchsAmtVo객체
    * @return List<JntpurchsAmtVo> 환경교육 교구 공동구매 수량별 가격 목록
    * @throws Exception 예외
    */
    public List<JntpurchsAmtVo> selectAmtList(JntpurchsAmtVo jntpurchsAmtVo) throws Exception;
    
    /**
    * 환경교육 교구 공동구매 상품 조회
    *
    * @Title : selectGoodsList 
    * @Description : jntpurchsTchaidVo 상품 목록 조회
    * @param jntpurchsTchaidVo JntpurchsTchaidVo객체
    * @return List<JntpurchsTchaidVo> jntpurchsTchaidVo 상품 목록
    * @throws Exception 예외
    */
    public List<JntpurchsTchaidVo> selectGoodsList(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception;
    
    /**
    * 환경교육 교구 공동구매 후기 목록 조회
    *
    * @Title : selectRvwList 
    * @Description : 환경교육 교구 공동구매 후기 목록 조회
    * @param jntpurchsRvwVo JntpurchsRvwVo객체
    * @return List<JntpurchsRvwVo> jntpurchsRvwVo 후기 목록
    * @throws Exception 예외
    */
    public List<JntpurchsRvwVo> selectRvwList(JntpurchsRvwVo jntpurchsRvwVo) throws Exception;
    
    /**
    * 공동구매 신청 회원 정보 조회
    *
    * @Title : selectUserInfo 
    * @Description : 공동구매 회원 정보 조회
    * @param memberVo MemberVo객체
    * @return MemberVo 공동구매 회원 정보
    * @throws Exception 예외
    */
    public MemberVo selectUserInfo(MemberVo memberVo) throws Exception;
    
    /**
    * 공동구매 신청 등록
    *
    * @Title : insertJntpurchsOrder 
    * @Description : 공동구매 신청 등록
    * @param jntpurchsOrderVo JntpurchsOrderVo객체
    * @return int qestrnid
    * @throws Exception 예외
    */
    public int insertJntpurchsOrder(JntpurchsOrderVo jntpurchsOrderVo) throws Exception;
    
    
    
    
//    
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