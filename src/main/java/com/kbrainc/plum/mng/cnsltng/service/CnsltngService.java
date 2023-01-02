package com.kbrainc.plum.mng.cnsltng.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;

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

}
