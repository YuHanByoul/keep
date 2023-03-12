package com.kbrainc.plum.front.lend.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 *  교구 대여 DAO 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.lend.model
 * - LendDao.java
 * </pre> 
 *
 * @ClassName : LendDao
 * @Description : 교구 대여 (사용자) DAO
 * @author : KBRAINC
 * @date : 2021.03.03.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper("front.lendDao")
public interface LendDao {
    
    /**
     * @Title : insertLendAply
     * @Description : 대여 신청 등록 
     * @param LendAplyVo
     * @throws Exception
     * @return List<NtcnVo> 객체
     */
    public int insertLendAply(LendAplyVo lendAplyVo) throws Exception;
    /**
     * @Title : insertLendAplyTrgt
     * @Description : 대여 신청 대상 등록 
     * @param LendAplyVo
     * @throws Exception
     * @return List<NtcnVo> 객체
     */
    public int insertLendAplyTrgt(LendAplyVo lendAplyVo) throws Exception;
    /**
     * @Title : selectLendRankList
     * @Description : 대여 신청 대상 등록 
     * @throws Exception
     * @return List<Map<String,Object>> 객체
     */
    public List<Map<String,Object>> selectLendRankList() throws Exception;
    /**
     * @Title : selectLendList
     * @Description : 대여 목록 호출  
     * @param LendVo
     * @throws Exception
     * @return List<LendVo> 객체
     */
    public List<LendVo> selectLendList(LendVo lendVo) throws Exception;
    /**
     * @Title : selectLend
     * @Description : 대여 상세  
     * @param LendVo
     * @throws Exception
     * @return LendVo 객체
     */
    public LendVo selectLend(LendVo lendVo) throws Exception;
    /**
     * @Title : selectLendRndList
     * @Description : 대여 차시 리스트 호출  
     * @param LendRndVo
     * @throws Exception
     * @return List<LendRndVo> 객체
     */
    public List<LendRndVo> selectLendRndList(LendRndVo lendRndVo) throws Exception;
    /**
     * @Title : selectLendRndList
     * @Description : 대여 후기 목록 호출  
     * @param LendAplyVo
     * @throws Exception
     * @return List<LendAplyVo> 객체
     */
    public List<LendAplyVo> selectLendReplyList(LendAplyVo lendAplyVo) throws Exception;
    /**
     * @Title : selectEduSbjctCdList
     * @Description : 교육 주제 코드 리스트 호출   
     * @throws Exception
     * @return List<Map<String,Object>> 객체
     */
    public List<Map<String,Object>> selectEduSbjctCdList() throws Exception;
    /**
     * @Title : selectRequestLendRndList
     * @Description : 신청 대여 차시 정보 호출  
     * @param LendAplyVo
     * @throws Exception
     * @return List<LendRndVo> 객체
     */
    public List<LendRndVo> selectRequestLendRndList(LendAplyVo lendAplyVo) throws Exception;
    /**
     * @Title : selectEduSbjctCdList
     * @Description : 신청 기관 정보 호출
     * @param LendAplyVo
     * @throws Exception
     * @return Map<String,Object> 객체
     */
    public Map<String,Object> selectRequestInstInfo(LendAplyVo lendAplyVo) throws Exception;
    /**
     * @Title : checkOverStockYn
     * @Description : 신청 차시중 재고 이상여부 확인
     * @param LendAplyVo
     * @throws Exception
     * @return String 객체
     */
    public String checkOverStockYn(LendAplyVo lendAplyVo) throws Exception;
    
}