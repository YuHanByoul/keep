package com.kbrainc.plum.mng.pack.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.mng.pack.model.PackageEduSbjctVo;
import com.kbrainc.plum.mng.pack.model.PackageEduTrgtVo;
import com.kbrainc.plum.mng.pack.model.PackageTchaidCmpstnVo;
import com.kbrainc.plum.mng.pack.model.PackageVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdAbnrmlVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdTchaidCmpstnDsctnVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdTchaidCmpstnVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;

/**
 * 
 * 교구 관리 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.mng.tchaid.service
 * - RsvtAplyService.java
 * </pre> 
 *
 * @ClassName : TchaidService
 * @Description : 교구 관리 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2023. 2. 02.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface PackageService {
    
    /**
     * 꾸러미 목록 호출 
     *
     * @Title       : selectPackageList 
     * @Description : 꾸러미 목록 호출 
     * @param PackageVo 객체
     * @return List<PackageVo>  목록
     * @throws Exception 예외
     */
    public List<PackageVo> selectPackageList(PackageVo packageVo) throws Exception;
    
    /**
     * 꾸러미 등록 
     *
     * @Title       : insertPackage 
     * @Description : 교구 등록 
     * @param PackageVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertPackage(PackageVo packageVo) throws Exception;
    
    /**
     * 꾸러미 대상 코드 맵핑 등록 
     *
     * @Title       : insertPackageEduTrgt 
     * @Description : 꾸러미 대상 코드 맵핑 등록 
     * @param PackageVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertPackageEduTrgt(PackageVo packageVo) throws Exception;
    
    /**
     * 꾸러미 교육주제  맵핑 등록 
     *
     * @Title       : insertPackageEduSbjct 
     * @Description : 꾸러미 교육주제  맵핑 등록 
     * @param PackageVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertPackageEduSbjct(PackageVo packageVo) throws Exception;
    
    /**
     * 꾸러미 수정 
     *
     * @Title       : updatePackage 
     * @Description : 꾸러미 교육주제  맵핑 등록 
     * @param PackageVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int updatePackage(PackageVo packageVo) throws Exception;
    
    /**
     * 꾸러미 주제 코드 삭제 
     *
     * @Title       : deletePackageEduSbjct 
     * @Description :  꾸러미 주제 코드 삭제 
     * @param PackageVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deletePackageEduSbjct(PackageVo packageVo) throws Exception;
    
    /**
     * 꾸러미 교육 대상  코드 삭제 
     *
     * @Title       : deletePackageEduSbjct 
     * @Description :  꾸러미 주제 코드 삭제 
     * @param PackageVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deletePackageEduTrgt(PackageVo packageVo) throws Exception;
    
    /**
     * 꾸러미 상세 호출  
     *
     * @Title       : selectPackage 
     * @Description :  꾸러미 상세 호출  
     * @param PackageVo 객체
     * @return int
     * @throws Exception 예외
     */
    public PackageVo selectPackage(PackageVo packageVo) throws Exception;
    
    /**
     * 꾸러미 교구 구성 등록 
     *
     * @Title       : deletePackageEduSbjct 
     * @Description :  꾸러미 주제 코드 삭제 
     * @param PackageVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertPackageTchaidCmpstn(PackageVo packageVo) throws Exception;
    /**
     * 꾸러미 교육 주제 코드 맵핑 리스트 호출
     *
     * @Title       : selectTchaidEduSbjctList 
     * @Description : 꾸러미 교육 주제 코드 맵핑 리스트 호출
     * @param PackageVo 객체
     * @return List<PackageEduSbjctVo>  목록
     * @throws Exception 예외
     */
    public List<PackageEduSbjctVo> selectTchaidEduSbjctList(PackageVo packageVo) throws Exception;
    /**
     * 꾸러미 교육 대상 코드 리스트 호출
     *
     * @Title       : selectTchaidEduTrgtList 
     * @Description : 꾸러미 교육 대상 코드 리스트 호출
     * @param PackageVo 객체
     * @return List<PackageEduTrgtVo>  목록
     * @throws Exception 예외
     */
    public List<PackageEduTrgtVo> selectTchaidEduTrgtList(PackageVo packageVo) throws Exception;
    /**
     * 꾸러미 구성 교구 리스트 호출 
     *
     * @Title       : selectPackageTchaidList 
     * @Description : 꾸러미 구성 교구 리스트 호출 
     * @param PackageVo 객체
     * @return List<TchaidVo>  목록
     * @throws Exception 예외
     */
    public List<PackageTchaidCmpstnVo> selectPackageTchaidList(PackageVo packageVo) throws Exception;
    /**
     * 꾸러미 교구 구성 삭제 
     *
     * @Title       : deletePackageTchaidCmpstn 
     * @Description :  꾸러미 교구 구성 삭제 
     * @param PackageVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deletePackageTchaidCmpstn(PackageVo packageVo) throws Exception;
    
    /*******꾸러미 개체**************************************/
    /**
     * 꾸러미 개체 생성(등록) 
     *
     * @Title       : insertPackageindvdByPackageid 
     * @Description :  꾸러미 개체 생성(등록) 
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertPackageindvdByPackageid(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 교구 구성 생성
     *
     * @Title       : insertPackageindvdTchaidCmpstnByPackageid 
     * @Description :  꾸러미 교구 구성 생성
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertPackageindvdTchaidCmpstnByPackageid(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 개체 수정 
     *
     * @Title       : updatePackageindvd 
     * @Description :  꾸러미 개체 수정 
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int updatePackageindvd(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 개체 목록 호출
     *
     * @Title       : selectPackageindvdList 
     * @Description : 꾸러미 개체 목록 호출
     * @param PackageindvdVo 객체
     * @return List<PackageindvdVo>  목록
     * @throws Exception 예외
     */
    public List<PackageindvdVo> selectPackageindvdList(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 구성 교구 개체 상세 리스트 호출
     *
     * @Title       : selectPackageindvdTchaidList 
     * @Description : 꾸러미 구성 교구 개체 상세 리스트 호출
     * @param PackageindvdVo 객체
     * @return List<PackageindvdTchaidCmpstnVo>  목록
     * @throws Exception 예외
     */
    public List<PackageindvdTchaidCmpstnVo> selectPackageindvdTchaidList(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 입출고 내역 목록 호출
     *
     * @Title       : selectPackageindvdlendList 
     * @Description : 꾸러미 입출고 내역 목록 호출
     * @param PackageindvdVo 객체
     * @return List<Map<String,Object>>  목록
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectPackageindvdlendList(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 이상 내역 목록 호출
     *
     * @Title       : selectPackageindvdlendList 
     * @Description : 꾸러미 이상 내역 목록 호출
     * @param PackageindvdVo 객체
     * @return List<PackageindvdAbnrmlVo>  목록
     * @throws Exception 예외
     */
    public List<PackageindvdAbnrmlVo> selectPackageindvdAbnrmlList(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 개체 이상 등록 
     *
     * @Title       : insertPackageindvdAbnrml 
     * @Description :  꾸러미 개체 이상 등록 
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertPackageindvdAbnrml(PackageindvdAbnrmlVo packageindvdAbnrmlVo) throws Exception;
    
    /**
     * 꾸러미 개체 이상 정상처리 업데이트
     *
     * @Title       : insertPackageindvdAbnrml 
     * @Description :  꾸러미 개체 이상 정상처리 업데이트 
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int updatePackageindvdAbnrml(PackageindvdAbnrmlVo packageindvdAbnrmlVo) throws Exception;
    
    /**
     * 꾸러미 개체 구성 내역 (망실 교구 입력)
     *
     * @Title       : insertPackageindvdTchaidCmpstnDsctn 
     * @Description :  꾸러미 개체 구성 내역 (망실 교구 입력) 
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertPackageindvdTchaidCmpstnDsctn(PackageindvdTchaidCmpstnDsctnVo packageindvdTchaidCmpstnDsctnVo) throws Exception;
    
    /**
     * 꾸러미 개체 구성 삭제
     *
     * @Title       : deletePackageindvdTchaidCmpstn 
     * @Description :  꾸러미 개체 구성 삭제
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deletePackageindvdTchaidCmpstn(PackageindvdVo packageindvdVo) throws Exception;
    
    /**
     * 꾸러미 개체 생성시 교구 재고 감량(업데이트)
     *
     * @Title       : updateTchaidQntyForPackageindvdPlus 
     * @Description :  꾸러미 개체 생성시 교구 재고 감량(업데이트)
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int updateTchaidQntyForPackageindvdPlus(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 개체 상세 호출
     *
     * @Title       : selectPackageindvd 
     * @Description : 꾸러미 개체 상세 호출
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public PackageindvdVo selectPackageindvd(PackageindvdVo packageindvdVo) throws Exception;
    
    /**
     * 꾸러미 개체 교구 추가시 재고 초과 확인
     *
     * @Title       : selectInvtryOverYn 
     * @Description : 꾸러미 개체 교구 추가시 재고 초과 확인
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public String selectInvtryOverYn(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 개체 정상화 처리시 일괄 재고 감량
     *
     * @Title       : updateTchaidQntyForPackageindvdNormalize 
     * @Description :  꾸러미 개체 정상화 처리시 일괄 재고 감량
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int updateTchaidQntyForPackageindvdNormalize(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 개체 정상화 처리시 교구 구성 업데이트 (기존 수량보다 추가 수량이 많을시  )
     *
     * @Title       : updateTchaidQntyForPackageindvdNormalize 
     * @Description : 꾸러미 개체 정상화 처리시 교구 구성 업데이트 (기존 수량보다 추가 수량이 많을시  )
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int updateTchaidCmpstnForPackageindvdNormalize(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 개체 구성 내역 (망실 교구 입력) 일괄 등록
     *
     * @Title       : insertPackageindvdTchaidCmpstnDsctnByList 
     * @Description : 꾸러미 개체 구성 내역 (망실 교구 입력) 일괄 등록
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertPackageindvdTchaidCmpstnDsctnByList(PackageindvdVo packageindvdVo) throws Exception;
    /**
     * 꾸러미 개체 이상 정상 처리 
     *
     * @Title       : insertPackageindvdTchaidCmpstnDsctnByList 
     * @Description : 꾸러미 개체 구성 내역 (망실 교구 입력) 일괄 등록
     * @param PackageindvdVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int packageindvdNormalize(PackageindvdVo packageindvdVo) throws Exception;
}