package com.kbrainc.plum.mng.delvry.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 * 교부관리 DAO
 *
 * <pre>
 * com.kbrainc.plum.mng.delvry.model
 * - DelvryDao.java
 * </pre> 
 *
 * @ClassName : DelvryDao
 * @Description : 교부관리 DAO 
 * @author : KBRAINC
 * @date : 2023. 02. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface DelvryDao {
    
    /**
    * 교부 목록 조회
    *
    * @Title : selectDelvryList
    * @Description : 교부 목록 조회
    * @param delvryVo DelvryVo 객체
    * @return List<DelvryVo> 교부 목록
    * @throws Exception 예외
    */
    public List<DelvryVo> selectDelvryList(DelvryVo delvryVo) throws Exception;
//    
//    /**
//     * 교구 목록 조회
//     *
//     * @Title : selectTchaidList
//     * @Description : 교구 목록 조회
//     * @param jntpurchsTchaidVo JntpurchsTchaidVo 객체
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
//     * @param jntpurchsVo JntpurchsVo 객체
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
//     * @param jntpurchsTchaidVo JntpurchsTchaidVo 객체
//     * @return List<JntpurchsTchaidVo> 공동구매모집 상품 목록
//     * @throws Exception 예외
//     */
//    public List<JntpurchsTchaidVo> selectGoodsList(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception;
//    
//    /**
//     * 공동구매모집 수량별 가격 목록 조회
//     *
//     * @Title : selectAmtList
//     * @Description : 공동구매모집 수량별 가격 목록 조회
//     * @param jntpurchsAmtVo JntpurchsAmtVo 객체
//     * @return List<JntpurchsTchaJntpurchsAmtVoidVo> 공동구매모집 수량별 가격 목록
//     * @throws Exception 예외
//     */
//    public List<JntpurchsAmtVo> selectAmtList(JntpurchsAmtVo jntpurchsAmtVo) throws Exception;
//    
//    /**
//     * 공동구매모집 신청여부 조회
//     *
//     * @Title : isExistOrder
//     * @Description : 공동구매모집 신청여부 조회
//     * @param jntpurchsVo JntpurchsVo 객체
//     * @return JntpurchsVo 공동구매모집 상세 정보
//     * @throws Exception 예외
//     */
//    public JntpurchsVo isExistOrder(JntpurchsVo jntpurchsVo) throws Exception;
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
//     * 공동구매모집 상품 삭제
//     *
//     * @Title : deleteGoods
//     * @Description : 공동구매모집 상품 삭제
//     * @param jntpurchsVo JntpurchsVo 객체
//     * @return int delete 로우수
//     * @throws Exception 예외
//     */
//    public int deleteGoods(JntpurchsVo jntpurchsVo) throws Exception;
//    
//    /**
//     * 공동구매모집 수량별 가격 삭제
//     *
//     * @Title : deleteAmt
//     * @Description : 공동구매모집 수량별 가격 삭제
//     * @param jntpurchsVo JntpurchsVo 객체
//     * @return int delete 로우수
//     * @throws Exception 예외
//     */
//    public int deleteAmt(JntpurchsVo jntpurchsVo) throws Exception;
//    
//    /**
//     * 공동구매신청 목록 조회
//     *
//     * @Title : selectJntpurchsOrderList
//     * @Description : 공동구매신청 목록 조회
//     * @param jntpurchsOrderVo JntpurchsOrderVo 객체
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
//     * @param jntpurchsOrderVo JntpurchsOrderVo 객체
//     * @return JntpurchsOrderVo 공동구매신청 상세 정보
//     * @throws Exception 예외
//     */
//    public JntpurchsOrderVo selectJntpurchsOrderInfo(JntpurchsOrderVo jntpurchsOrderVo) throws Exception;
//    
//    /**
//     * 공동구매신청 정보 업데이트
//     *
//     * @Title : updateJntpurchsOrder
//     * @Description : 공동구매신청 정보 업데이트
//     * @param jntpurchsOrderVo JntpurchsOrderVo 객체
//     * @return int update 로우수
//     * @throws Exception 예외
//     */
//    public int updateJntpurchsOrder(JntpurchsOrderVo jntpurchsOrderVo) throws Exception;
    
}