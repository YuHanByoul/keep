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

    public Boolean deleteSite(Integer siteid) throws Exception;

    public Boolean insertSiteDomain(SiteDomainVo siteDomainVo) throws Exception;

    public Boolean updateSiteDomain(SiteDomainVo siteDomainVo) throws Exception;

    public Boolean deleteSiteDomain(SiteDomainVo siteDomainVo) throws Exception;

}
