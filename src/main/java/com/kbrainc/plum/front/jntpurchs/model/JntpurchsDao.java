package com.kbrainc.plum.front.jntpurchs.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsAmtVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsOrderVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsTchaidVo;

/**
 * 
 * 환경교육 교구 공동구매 DAO
 *
 * <pre>
 * com.kbrainc.plum.front.jntpurchs.model
 * - JntpurchsDao.java
 * </pre> 
 *
 * @ClassName : JntpurchsDao
 * @Description : 환경교육 교구 공동구매 DAO 
 * @author : KBRAINC
 * @date : 2023. 02. 17.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.JntpurchsDao")
public interface JntpurchsDao {
    /**
    * 환경교육 교구 공동구매 목록 조회
    *
    * @Title : selectJntpurchsList
    * @Description : 환경교육 교구 공동구매 목록 조회
    * @param jntpurchsVo JntpurchsVo 객체
    * @return List<JntpurchsVo> 환경교육 교구 공동구매 목록
    * @throws Exception 예외
    */
    public List<JntpurchsVo> selectJntpurchsList(JntpurchsVo jntpurchsVo) throws Exception;
    
    /**
    * 환경교육 교구 공동구매 상세 정보 조회
    *
    * @Title : selectJntpurchsInfo
    * @Description : 환경교육 교구 공동구매 상세 정보 조회
    * @param jntpurchsVo JntpurchsVo 객체
    * @return JntpurchsVo 환경교육 교구 공동구매 상세 정보
    * @throws Exception 예외
    */
    public JntpurchsVo selectJntpurchsInfo(JntpurchsVo jntpurchsVo) throws Exception;
    
    /**
    * 환경교육 교구 공동구매 수량별 가격 목록 조회
    *
    * @Title : selectAmtList
    * @Description : 환경교육 교구 공동구매 수량별 가격 목록 조회
    * @param jntpurchsAmtVo JntpurchsAmtVo 객체
    * @return List<JntpurchsAmtVo> 환경교육 교구 공동구매 수량별 가격 목록
    * @throws Exception 예외
    */
    public List<JntpurchsAmtVo> selectAmtList(JntpurchsAmtVo jntpurchsAmtVo) throws Exception;
    
    /**
    * 환경교육 교구 공동구매 상품 목록 조회
    *
    * @Title : selectGoodsList
    * @Description : 환경교육 교구 공동구매 상품 목록 조회
    * @param jntpurchsTchaidVo JntpurchsTchaidVo 객체
    * @return List<JntpurchsTchaidVo> 환경교육 교구 공동구매 상품 목록
    * @throws Exception 예외
    */
    public List<JntpurchsTchaidVo> selectGoodsList(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception;
    
    /**
    * 환경교육 교구 공동구매 후기 목록 조회
    *
    * @Title : selectRvwList
    * @Description : 환경교육 교구 공동구매 후기 목록 조회
    * @param jntpurchsRvwVo JntpurchsRvwVo 객체
    * @return List<JntpurchsRvwVo> 환경교육 교구 공동구매 후기 목록
    * @throws Exception 예외
    */
    public List<JntpurchsRvwVo> selectRvwList(JntpurchsRvwVo jntpurchsRvwVo) throws Exception;
    
    /**
    * 공동구매 신청 회원 정보 조회
    *
    * @Title : selectUserInfo
    * @Description : 공동구매 신청 회원 정보 조회
    * @param memberVo MemberVo 객체
    * @return memberVo 공동구매 신청 회원 정보
    * @throws Exception 예외
    */
    public MemberVo selectUserInfo(MemberVo memberVo) throws Exception;
    
    /**
    * 공동구매 수량 정보 조회
    *
    * @Title : selectQntyInfo
    * @Description : 공동구매 수량 정보 조회
    * @param jntpurchsOrderVo JntpurchsOrderVo 객체
    * @return JntpurchsVo 공동구매 수량 정보
    * @throws Exception 예외
    */
    public JntpurchsVo selectQntyInfo(JntpurchsOrderVo jntpurchsOrderVo) throws Exception;
    
    /**
    * 공동구매 신청 등록
    *
    * @Title : insertJntpurchsOrder 
    * @Description : 공동구매  신청 등록
    * @param jntpurchsOrderVo JntpurchsOrderVo객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    public int insertJntpurchsOrder(JntpurchsOrderVo jntpurchsOrderVo) throws Exception;
    
    /**
    * 공동구매 상품 신청 등록
    *
    * @Title : insertJntpurchsOrderGoods 
    * @Description : 공동구매 상품 신청 등록
    * @param jntpurchsTchaidVo JntpurchsTchaidVo객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    public int insertJntpurchsOrderGoods(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception;
    
}