package com.kbrainc.plum.mng.lend.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.lend.model.LendAplyDlivyVo;
import com.kbrainc.plum.mng.lend.model.LendAplyVo;
import com.kbrainc.plum.mng.lend.model.LendPackageindvdChckVo;
import com.kbrainc.plum.mng.lend.model.LendRndPackageindvdVo;
import com.kbrainc.plum.mng.lend.model.LendRndVo;
import com.kbrainc.plum.mng.lend.model.LendVo;
import com.kbrainc.plum.mng.pack.model.PackageVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;

/**
 * 
 * 교구 대여 관리 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.mng.lend.service
 * - LendService.java
 * </pre> 
 *
 * @ClassName : LendService
 * @Description : 교구 대여 관리 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2023. 02. 02.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface LendService {
    
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
     * @param LendVo 객체
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
     * 교구 대여 차시 꾸러마 목록 호출 
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
    public Map<String,String> checkRndStockOver(LendAplyVo lendAplyVo) throws Exception;
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
    /**
     * 대여 출고 목록 호출
     *
     * @Title       : selectLendAplyDlvyList 
     * @Description : 대여 출고 목록 호출 
     * @param LendAplyVo 객체
     * @return List<LendAplyDlivyVo> 객체
     * @throws Exception 예외
     */
    public List<LendAplyDlivyVo> selectLendAplyDlvyList(LendAplyVo lendAplyVo) throws Exception;
    /**
     * 대여 신청 수정
     *
     * @Title       : updateLendAply 
     * @Description : 대여 신청 수정 
     * @param LendAplyVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int updateLendAply(LendAplyVo lendAplyVo) throws Exception;
    /**
     * 대여 모집 차시 목록 호출 (검색용) 
     *
     * @Title       : selectLendRcritRndList 
     * @Description :대여 모집 차시 목록 호출 (검색용) 
     * @param LendVo 객체
     * @return List<LendVo>  목록
     * @throws Exception 예외
     */
    public List<LendVo> selectLendRcritRndList(LendVo LendVo) throws Exception;
    /**
     * 출고 꾸러미 개체 목록 호출
     *
     * @Title       : searchPackageindvdList 
     * @Description :출고 꾸러미 개체 목록 호출 
     * @param LendVo 객체
     * @return List<PackageindvdVo>  목록
     * @throws Exception 예외
     */
    public List<PackageindvdVo> searchPackageindvdList(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 개체 입출고 상태 수정
     *
     * @Title       : updatePackageindvdStts 
     * @Description :꾸러미 개체 입출고 상태 수정 
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public int updatePackageindvdStts(LendAplyVo lendAplyVo) throws Exception;
    /**
     * 대여 신청 출고 등록
     *
     * @Title       : insertLendAplyDlivy 
     * @Description :대여 신청 출고 등록 
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public int insertLendAplyDlivy(LendAplyVo lendAplyVo) throws Exception;
    
    /**************입고 관리 **************/
    /**
     * 대여 신청 출고 상태 (입고)수정
     *
     * @Title       : updateLendAplyDlivy 
     * @Description :대여 신청 출고 상태 (입고)수정 
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public int updateLendAplyDlivy(LendAplyVo lendAplyVo) throws Exception;
    /**
     * 꾸러미 개체 점검 등록
     *
     * @Title       : insertLendPackageindvdChck 
     * @Description :꾸러미 개체 점검 등록 
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public int insertLendPackageindvdChck(LendPackageindvdChckVo lendPackageindvdChckVo) throws Exception;
    /**
     * 꾸러미 개체 점검 답변 등록
     *
     * @Title       : insertLendPackageindvdChckAns 
     * @Description :꾸러미 개체 점검 답변 등록 
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public int insertLendPackageindvdChckAns(LendPackageindvdChckVo lendPackageindvdChckVo) throws Exception;
    /**
     * 입고 확인 꾸러미 개체 목록 호출 
     *
     * @Title       : selectLendPackageindvdList 
     * @Description :입고 확인 꾸러미 개체 목록 호출 
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public List<LendAplyVo> selectLendPackageindvdList(LendAplyVo lendAplyVo) throws Exception;
    /**
     * 교구 점검 양식 문항/보기 리스트 호출
     *
     * @Title       : selectFormExidList 
     * @Description :교구 점검 양식 문항/보기 리스트 호출 
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectFormExidList(Map<String,Object> parmaMap) throws Exception;
    /**
     * 입고 점검 처리 (점검 등록 및 이상처리 등)
     *
     * @Title       : healthChckProcess 
     * @Description :입고 점검 처리 (점검 등록 및 이상처리 등) 
     * @param LendPackageindvdChckVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public Map<String,Object> wrhounsngChckProcess(LendPackageindvdChckVo lendPackageindvdChckVo) throws Exception;
    /**
     * 위생점검 처리 (점검 등록 및 이상처리 등)
     *
     * @Title       : healthChckProcess 
     * @Description :위생점검 처리 (점검 등록 및 이상처리 등) 
     * @param LendPackageindvdChckVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public Map<String,Object> healthChckProcess(LendPackageindvdChckVo lendPackageindvdChckVo) throws Exception;
    /**
     * 대여차시 별 이미 승인 된 꾸러미 개체 여부 확인
     *
     * @Title       : isThereRndPackageindvdYn 
     * @Description : 대여차시 별 이미 승인 된 꾸러미 개체 여부 확인 
     * @param LendPackageindvdChckVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public String isThereRndPackageindvdYn(LendAplyVo lendAplyVo) throws Exception;
    /**
     * 위생체크용 꾸러미 개체 목록 호출
     *
     * @Title       : selectPackageindvdListForHealthChck 
     * @Description : 위생체크용 꾸러미 개체 목록 호출 
     * @param PackageindvdVo 객체
     * @return List<PackageindvdVo>  목록
     * @throws Exception 예외
     */
    public List<PackageindvdVo> selectPackageindvdListForHealthChck(PackageindvdVo packageindvdVo) throws Exception;
    
    
    
    
    
}