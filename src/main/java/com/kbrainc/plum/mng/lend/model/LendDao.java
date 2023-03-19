package com.kbrainc.plum.mng.lend.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.pack.model.PackageVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;

/**
 * 
 * 대여 모집 관리 DAO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.lend.model
 * - LendDao.java
 * </pre>        
 *
 * @ClassName : LendDao
 * @Description : 대여 모집 관리 DAO 클래스. 
 * @author : KBRAINC
 * @date : 2023. 02. 21
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface LendDao {
    
    /**
     * 교구 대여 모집 목록 호출 
     *
     * @Title       : selectLendList 
     * @Description : 교구 대여 모집 목록 호출 
     * @param LendVo 객체
     * @return List<LendVo>  목록
     * @throws Exception 예외
     */
    public List<LendVo> selectLendList(LendVo lendVo) throws Exception;
    
    /**
     * 교구 대여 모집 등록 
     *
     * @Title       : insertLend 
     * @Description : 교구 대여 모집 등록 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertLend(LendVo lendVo) throws Exception;
    
    /**
     * 교구 대여 차시 등록 
     *
     * @Title       : insertLendRnd 
     * @Description : 교구 대여 차시 등록 
     * @param lendRndVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertLendRnd(LendRndVo lendRndVo) throws Exception;
    /**
     * 교구 대여 차시 꾸러미 등록 
     *
     * @Title       : insertRndPackageindvd 
     * @Description : 교구 대여 차시 등록 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertRndPackageindvd(LendRndVo lendRndVo) throws Exception;
    /**
     * 교구 대여 수정 
     *
     * @Title       : udateLend 
     * @Description : 교구 대여 수정 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int udateLend(LendVo lendVo) throws Exception;
    /**
     * 교구 대여 삭제
     *
     * @Title       : deleteLend 
     * @Description : 교구 대여 수정 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deleteLend(LendVo lendVo) throws Exception;
    /**
     * 교구 대여 차시 삭제
     *
     * @Title       : deleteLendRnd 
     * @Description : 교구 대여 수정 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deleteLendRnd(LendRndVo lendRndVo) throws Exception;
    /**
     * 교구 대여 차시 꾸러미 삭제
     *
     * @Title       : deleteLendRnd 
     * @Description : 교구 대여 차시 꾸러미 삭제 
     * @param LendRndPackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deleteLendRndPackageindvd(LendRndVo lendRndVo) throws Exception;
    /**
     * 교구 대여 모집 상세 호출 
     *
     * @Title       : selectLend 
     * @Description : 교구 대여 모집 상세 호출 
     * @param LendVo 객체
     * @return List<LendVo>  목록
     * @throws Exception 예외
     */
    public LendVo selectLend(LendVo lendVo) throws Exception;
    /**
     * 교구 대여 차시 목록 호출 
     *
     * @Title       : selectLend 
     * @Description : 교구 대여 모집 상세 호출 
     * @param LendVo 객체
     * @return List<LendRndVo>  목록
     * @throws Exception 예외
     */
    public List<LendRndVo> selectLendRndList(LendVo lendVo) throws Exception;
    /**
     * 교구 대여 차시 꾸러미 목록 호출 
     *
     * @Title       : selectLendRndPackageindvdList 
     * @Description : 교구 대여 차시 꾸러마 목록 호출 
     * @param LendVo 객체
     * @return List<LendRndPackageindvdVo>  목록
     * @throws Exception 예외
     */
    public List<LendRndPackageindvdVo> selectLendRndPackageindvdList(LendRndVo lendRndVo) throws Exception;
    /**
     * 검색용 꾸러미 개체 호출 
     *
     * @Title       : selectPackageindvdList 
     * @Description : 검색용 꾸러미 개체 호출 
     * @param LendVo 객체
     * @return List<PackageindvdVo>  목록
     * @throws Exception 예외
     */
    public List<PackageindvdVo> selectPackageindvdList(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 개체 대여 신청여부 확인  
     *
     * @Title       : selectLendApplyYn 
     * @Description : 꾸러미 개체 대여 신청여부 확인  
     * @param LendVo 객체
     * @return String [Y/N]
     * @throws Exception 예외
     */
    public String selectLendApplyYn(LendVo lendVo) throws Exception;
    /**
     * 검색용 꾸러미 목록 호출 
     *
     * @Title       : selectPackageList 
     * @Description : 검색용 꾸러미 목록 호출 
     * @param LendVo 객체
     * @return List<PackageVo>  목록
     * @throws Exception 예외
     */
    public List<PackageVo> selectPackageList(PackageVo packageVo) throws Exception;
    /**
     * 꾸러미 대여 중복 체크 
     *
     * @Title       : checkPackageDuplicationYn 
     * @Description : 꾸러미 대여 중복 체크 
     * @param LendVo 객체
     * @return String  목록
     * @throws Exception 예외
     */
    public String checkPackageDuplicationYn(LendVo lendVo) throws Exception;
    
    /****** 꾸러미 대여 신청 관리**********/
    /**
     * 대여 신청 목록 호출 
     *
     * @Title       : selectLendAplyList 
     * @Description :대여 신청 목록 호출 
     * @param LendVo 객체
     * @return List<LendAplyVo>  목록
     * @throws Exception 예외
     */
    public List<LendAplyVo> selectLendAplyList(LendAplyVo LendAplyVo) throws Exception;
    /**
     * 대여 모집 목록 호출 
     *
     * @Title       : selectLendRcritList 
     * @Description :대여 모집 목록 호출 
     * @param LendVo 객체
     * @return List<LendAplyVo>  목록
     * @throws Exception 예외
     */
    public List<LendVo> selectLendRcritList(LendVo lendVo) throws Exception;
    /**
     * 대여 신청 승인 전 재고 및 수량 체크  
     *
     * @Title       : checkRndStockOver 
     * @Description :대여 모집 목록 호출 
     * @param LendVo 객체
     * @return Map<String,String>
     * @throws Exception 예외
     */
    public Map<String,String> checkRndStockOver(LendAplyVo LendAplyVo) throws Exception;
    /**
     * 대여 신청 일괄 상태 변경
     *
     * @Title       : updateLendAplyStts 
     * @Description : 대여 신청 일괄 상태 변경 
     * @param LendAplyVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int updateLendAplyStts(LendAplyVo LendAplyVo) throws Exception;
    /**
     * 대여 신청 상세 호출
     *
     * @Title       : selectLendAplyInfo 
     * @Description : 대여 신청 상세 호출 
     * @param LendAplyVo 객체
     * @return int
     * @throws Exception 예외
     */
    public LendAplyVo selectLendAplyInfo(LendAplyVo LendAplyVo) throws Exception;
    
}