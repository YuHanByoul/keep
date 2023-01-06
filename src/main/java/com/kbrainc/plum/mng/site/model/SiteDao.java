package com.kbrainc.plum.mng.site.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.rte.lib.tree.TreeItem;

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

    /**
    * 사이트에 메뉴가 존재하는지 확인한다.
    *
    * @Title : selectSiteMenuYn
    * @Description : 사이트에 메뉴가 존재하는지 확인한다.
    * @param siteid 사이트아이디
    * @return String 사이트 메뉴 존재 여부("Y" OR "N")
    * @throws Exception 예외
    */
    public String selectSiteMenuYn(Integer siteid) throws Exception;

    /**
    * 분양사이트 메뉴구성을 위한 프로그램 목록을 조회한다.
    *
    * @Title : selectSiteAplySetupPrgrmList
    * @Description : 분양사이트 메뉴구성을 위한 프로그램 목록을 조회한다.
    * @return List<SiteMenuSetupVo> 메뉴구성 프로그램 목록
    * @throws Exception 예외
    */
    public List<SiteMenuSetupVo> selectSiteAplySetupPrgrmList() throws Exception;

    /**
    * 분양사이트를 구성하는 메뉴의 프로그램 목록을 조회한다.
    *
    * @Title : selectSiteAplySetupMenuList
    * @Description : 분양사이트를 구성하는 메뉴의 프로그램 목록을 조회한다.
    * @param siteid 사이트아이디
    * @return List<SiteMenuSetupVo> 메뉴 프로그램 목록
    * @throws Exception 예외
    */
    public List<SiteMenuSetupVo> selectSiteAplySetupMenuList(Integer siteid) throws Exception;

    /**
    * 특정 분양사이트 특정 구성요소의 프로그램 트리.
    *
    * @Title : selectSiteAplyPrgrmTreeList
    * @Description : 특정 분양사이트 특정 구성요소의 프로그램 트리.
    * @param prgrmid 프로그램아이디
    * @return List<TreeItem> 프로그램 트리 목록
    * @throws Exception 예외
    */
    public List<TreeItem> selectSiteAplyPrgrmTreeList(Integer prgrmid) throws Exception;

    /**
    * 일반사용자역할 모든 메뉴 권한 부여.
    *
    * @Title : insertSiteAllMenuRoleForGeneralUser
    * @Description : 일반사용자역할 모든 메뉴 권한 부여.
    * @param siteMenuSetupVo SiteMenuSetupVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertSiteAllMenuRoleForGeneralUser(SiteMenuSetupVo siteMenuSetupVo) throws Exception;

    /**
    * 해당사이트 메뉴 역할 전체 삭제(초기화).
    *
    * @Title : deleteRoleMenuForSiteMenuInit
    * @Description : 해당사이트 메뉴 역할 전체 삭제(초기화).
    * @param siteid 사이트아이디
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteRoleMenuForSiteMenuInit(Integer siteid) throws Exception;

    /**
    * 해당사이트 메뉴 전체 삭제(초기화).
    *
    * @Title : deleteMenuForSiteMenuInit
    * @Description : 해당사이트 메뉴 전체 삭제(초기화).
    * @param siteid 사이트아이디
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteMenuForSiteMenuInit(Integer siteid) throws Exception;
}
