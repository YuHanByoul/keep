package com.kbrainc.plum.mng.lend.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.mng.cmpnt.model.CmpntVo;
import com.kbrainc.plum.mng.lend.model.LendRndPackageindvdVo;
import com.kbrainc.plum.mng.lend.model.LendRndVo;
import com.kbrainc.plum.mng.lend.model.LendVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidCmpntCmpstnVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidEduSbjctVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidEduTrgtVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidWrhousngVo;

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
}