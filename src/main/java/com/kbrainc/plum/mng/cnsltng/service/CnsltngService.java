package com.kbrainc.plum.mng.cnsltng.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.cnsltng.model.CnsltngExprtGrpVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngExprtVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngResultVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;

/**
 * 
 * 사이트관리 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.mng.site.service
 * - SiteService.java
 * </pre> 
 *
 * @ClassName : SiteService
 * @Description : 사이트관리 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2021. 3. 12.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public interface CnsltngService {
    
    /**
    * 컨설팅 리스트 호출 
    *
    * @Title       : selectCnsltngList 
    * @Description :컨설팅 리스트 호출 
    * @param SiteApplyVo SiteApplyVo객체
    * @return List<SiteApplyVo>
    * @throws Exception 예외
    */
    public List<CnsltngVo> selectCnsltngList(CnsltngVo consultVo) throws Exception;
    /**
     * 컨설팅 상세정보 호출 
     *
     * @Title       : selectCnsltngtInfo 
     * @Description : 컨설팅 상세정보 호출 
     * @param SiteApplyVo SiteApplyVo객체
     * @return SiteApplyVo
     * @throws Exception 예외
     */
    public CnsltngVo selectCnsltngtInfo(CnsltngVo consultVo) throws Exception;
    /**
     * 컨설팅 신청 상태 수정 
     *
     * @Title       : updateConsultStatus 
     * @Description : 사이트 신청 상태 수정
     * @param ConsultVo ConsultVo객체
     * @return int
     * @throws Exception 예외
     */
    public int updateConsultStatus(CnsltngVo consultVo) throws Exception;
    /**
     * 컨설턴트 리스트 호출 
     *
     * @Title       : selectCnstntList 
     * @Description : 컨설턴트 리스트 호출 
     * @param CnsltngVo consultVo 객체
     * @return List<CnsltngVo>
     * @throws Exception 예외
     */
    public List<CnsltngVo> selectCnstntList(CnsltngVo consultVo) throws Exception;
    /**
     * 전문가 리스트 호출 
     *
     * @Title       : selectCnsltngExprtList 
     * @Description : 전문가 리스트 호출 
     * @param CnsltngVo consultVo 객체
     * @return List<CnsltngExprtVo>
     * @throws Exception 예외
     */
    public List<CnsltngExprtVo> selectCnsltngExprtList(CnsltngExprtVo cnsltngExprtVo) throws Exception;
    /**
     * 전문가 그룹 리스트 호출 
     *
     * @Title       : selectCnsltngExprtGrpList 
     * @Description : 전문가 리스트 호출 
     * @param CnsltngExprtGrpVo CnsltngExprtGrpVo 객체
     * @return List<CnsltngExprtGrpVo>
     * @throws Exception 예외
     */
    public List<CnsltngExprtGrpVo> selectCnsltngExprtGrpList(CnsltngExprtGrpVo cnsltngExprtGrpVo) throws Exception;
    /**
     * 컨설팅 담당자 일괄 삭제  
     *
     * @Title       : deleteCnsltnt 
     * @Description : 사이트 신청 상태 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    public int deleteCnsltnt(CnsltngVo cnsltngVo) throws Exception;
    /**
     * 시도 리스트 호출 
     *
     * @Title       : selectAddrCtprvnList 
     * @Description : 시도 리스트 호출 
     * @param Map<String,Object> 객체
     * @return List<Map<String,Object>
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectAddrCtprvnList(Map<String,Object> paramMap) throws Exception;
    /**
     * 컨설팅 담당자 등록  
     *
     * @Title       : insertCnsltnt 
     * @Description : 사이트 신청 상태 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    public int insertCnsltnt(CnsltngVo cnsltngVo) throws Exception;
    /**
     * 컨설팅 담당자 일괄 등록  
     *
     * @Title       : insertCnsltntALL 
     * @Description : 사이트 신청 상태 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    public int insertCnsltntALL(CnsltngVo cnsltngVo) throws Exception;
    /**
     * 컨설팅 설문리스트리스트 호출  
     *
     * @Title       : selectCnsltngSrvyList 
     * @Description : 컨설팅 설문리스트리스트 호출 
     * @param SrvyVo SrvyVo 객체
     * @return List<SrvyVo>
     * @throws Exception 예외
     */
    public List<SrvyVo> selectCnsltngSrvyList(SrvyVo srvyVo) throws Exception;
    /**
     * 컨설팅 상태 정보 수정  
     *
     * @Title       : updateCnsltnt 
     * @Description : 컨설팅 상태 정보 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    public int updateCnsltng(CnsltngVo cnsltngVo) throws Exception;
    /**
     * 컨설팅 리스트 엑셀 다운로드  
     *
     * @Title       : updateCnsltnt 
     * @Description : 컨설팅 상태 정보 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    public void cnstlngExcelDownList(CnsltngVo cnsltngVo , HttpServletResponse response, HttpServletRequest request) throws Exception;
    /**
     * 컨설턴트 결과 정보 조회 
     *
     * @Title       : selectCnstntList 
     * @Description : 컨설턴트 리스트 호출 
     * @param CnsltngResultVo CnsltngResultVo 객체
     * @return List<CnsltngResultVo>
     * @throws Exception 예외
     */
    public CnsltngResultVo selectCnsltngtResult(CnsltngResultVo cnsltngResultVo) throws Exception;
    /**
     * 컨설팅 결과 저장   
     *
     * @Title       : mergeCnsltngRslt 
     * @Description : 컨설팅 결과 저장
     * @param CnsltngResultVo cnsltngResultVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int mergeCnsltngRslt(CnsltngResultVo cnsltngResultVo) throws Exception;
    /**
     * 컨설팅 설문 결과 호출  
     *
     * @Title       : selectCnsltngtSrvyResult 
     * @Description : 컨설팅 설문 결과 호출 
     * @param CnsltngVo consultVo 객체
     * @return List<Map<String,Object>>
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectCnsltngtSrvyResult(CnsltngVo consultVo) throws Exception;
}