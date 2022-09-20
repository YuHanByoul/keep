package com.kbrainc.plum.mng.site.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SiteDao {
    public List<SiteVo> selectSiteList(SiteVo siteVo) throws Exception;
    public SiteVo selectSiteInfo(Integer siteid) throws Exception;
    public List<SiteDomainVo> selectSiteDomainList(SiteVo siteVo);
    public void insertSite(SiteVo siteVo);
    public void updateSite(SiteVo siteVo);
    public void deleteSite(Integer siteid);
    public void insertSiteDomain(SiteDomainVo siteDomainVo);
    public void updateSiteDomain(SiteDomainVo siteDomainVo);
    public void deleteSiteDomain(SiteDomainVo siteDomainVo);
}
