package com.kbrainc.plum.mng.siteApply.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.site.model.SiteDomainVo;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.mng.siteApply.model.SiteApplyMenuVo;
import com.kbrainc.plum.mng.siteApply.model.SiteApplyVo;

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
public interface SiteApplyService {
    
    
    /**
    * 사이트 신청 리스트 호출 
    *
    * @Title       : selectSiteApplyList 
    * @Description :사이트 신청 리스트 호출
    * @param SiteApplyVo SiteApplyVo객체
    * @return List<SiteApplyVo>
    * @throws Exception 예외
    */
    public List<SiteApplyVo> selectSiteApplyList(SiteApplyVo siteApplyVo) throws Exception;
    /**
     * 사이트 신청 상세정보 호출 
     *
     * @Title       : selectSiteApplyInfo 
     * @Description : 사이트 신청 상세정보 호출
     * @param SiteApplyVo SiteApplyVo객체
     * @return SiteApplyVo
     * @throws Exception 예외
     */
    public SiteApplyVo selectSiteApplyInfo(SiteApplyVo siteApplyVo) throws Exception;
    /**
     * 사이트 신청 상태 수정 
     *
     * @Title       : updateSiteApplyStatus 
     * @Description : 사이트 신청 상태 수정
     * @param SiteApplyVo SiteApplyVo객체
     * @return List<SiteApplyVo>
     * @throws Exception 예외
     */
    public int updateSiteApplyStatus(SiteApplyVo siteApplyVo) throws Exception;
    /**
    * 사이트 신청 메뉴 리스트 호출 
    *
    * @Title       : selectSiteApplyMenuList 
    * @Description :사이트 신청 메뉴 리스트 호출 
    * @param SiteApplyVo SiteApplyVo객체
    * @return List<SiteApplyVo>
    * @throws Exception 예외
    */
    public List<SiteApplyMenuVo> selectSiteApplyMenuList(SiteApplyVo siteApplyVo) throws Exception;

}
