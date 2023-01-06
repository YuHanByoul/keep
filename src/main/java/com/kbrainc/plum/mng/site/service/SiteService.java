package com.kbrainc.plum.mng.site.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.site.model.SiteDomainVo;
import com.kbrainc.plum.mng.site.model.SiteMenuSetupVo;
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
    * 분양사이트 메뉴구성을 저장한다.
    *
    * @Title : saveSiteAplyMenuSetup
    * @Description : 분양사이트 메뉴구성을 저장한다.
    * @param siteMenuSetupVo SiteMenuSetupVo객체
    * @return int 변경된 로우수
    * @throws Exception 예외
    */
    public int saveSiteAplyMenuSetup(SiteMenuSetupVo siteMenuSetupVo) throws Exception;
    
    /**
    * 분양사이트 메뉴구성을 초기화 한다.
    *
    * @Title : initSiteAplyMenuSetup
    * @Description : 분양사이트 메뉴구성을 초기화 한다.
    * @param siteid 사이트아이디
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int initSiteAplyMenuSetup(Integer siteid) throws Exception;
}
