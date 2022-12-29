package com.kbrainc.plum.mng.site.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SiteDao {
    public List<SiteVo> selectSiteList(SiteVo siteVo) throws Exception;
    public SiteVo selectSiteInfo(Integer siteid) throws Exception;
    public List<SiteDomainVo> selectSiteDomainList(SiteVo siteVo) throws Exception;
    public void insertSite(SiteVo siteVo) throws Exception;
    public void updateSite(SiteVo siteVo) throws Exception;
    public int insertSiteDomains(SiteDomainVo siteDomainVo) throws Exception;
    public int deleteSiteDomain(SiteDomainVo siteDomainVo);
    
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
    
    /**
    * 기관회원의 기관관리자 역할 부여.
    *
    * @Title : insertInstRoleUser
    * @Description : 기관회원의 기관관리자 역할 부여
    * @param siteVo SiteVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertInstRoleUser(SiteVo siteVo) throws Exception;
    
    /**
    * 기관회원의 기관관리자 역할 회수.
    *
    * @Title : deleteInstRoleUser
    * @Description : 기관회원의 기관관리자 역할 회수
    * @param siteVo SiteVo객체
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteInstRoleUser(SiteVo siteVo) throws Exception;
}
