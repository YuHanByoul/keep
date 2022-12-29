package com.kbrainc.plum.mng.site.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.site.model.SiteDomainVo;
import com.kbrainc.plum.mng.site.model.SiteVo;

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
public interface SiteService {
	public List<SiteVo> selectSiteList(SiteVo siteVo) throws Exception;

    public SiteVo selectSiteInfo(Integer siteid) throws Exception;
    
    public List<SiteDomainVo> selectSiteDomainList(SiteVo siteVo) throws Exception;

    public Boolean insertSite(SiteVo siteVo) throws Exception;

    public Boolean updateSite(SiteVo siteVo) throws Exception;

    /**
    * 사이트 도메인정보 저장.
    *
    * @Title : saveSiteDomain
    * @Description : 사이트 도메인정보 저장
    * @param siteDomainVo SiteDomainVo객체
    * @return int deleteInsert로우수
    * @throws Exception 예외
    */
    public int saveSiteDomain(SiteDomainVo siteDomainVo) throws Exception;
    
    /**
    * 동일한 도메인이 있는지 조회한다.
    *
    * @Title : selectSameSiteDomains
    * @Description : 동일한 도메인이 있는지 조회한다
    * @param siteDomainVo SiteDomainVo객체
    * @return SiteDomainVo 중복도메인정보(domains)
    * @throws Exception 예외
    */
    public SiteDomainVo selectSameSiteDomains(SiteDomainVo siteDomainVo) throws Exception;

}
