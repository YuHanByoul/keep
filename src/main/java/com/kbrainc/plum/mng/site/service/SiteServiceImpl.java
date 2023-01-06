package com.kbrainc.plum.mng.site.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.menu.model.MenuDao;
import com.kbrainc.plum.mng.menu.model.MenuVo;
import com.kbrainc.plum.mng.site.model.SiteDao;
import com.kbrainc.plum.mng.site.model.SiteDomainVo;
import com.kbrainc.plum.mng.site.model.SiteMenuSetupVo;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 사이트관리를 위한 서비스 구현.
 *
 * <pre>
 * com.kbrainc.plum.mng.site.service
 * - SiteServiceImpl.java
 * </pre> 
 *
 * @ClassName : SiteServiceImpl
 * @Description : 사이트관리를
 * @author : KBRAINC
 * @date : 2021. 3. 16.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class SiteServiceImpl extends PlumAbstractServiceImpl implements SiteService {
    
    @Autowired
    SiteDao siteDao;
    
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<SiteVo> selectSiteList(SiteVo siteVo) throws Exception {
        List<SiteVo> list = siteDao.selectSiteList(siteVo);
        return list;
    }

    @Override
    public SiteVo selectSiteInfo(Integer siteid) throws Exception {
        SiteVo siteVo = siteDao.selectSiteInfo(siteid); 
        siteVo.setSiteDomainList(this.selectSiteDomainList(siteVo));
        
        return siteVo;
    }

    @Override
    public List<SiteDomainVo> selectSiteDomainList(SiteVo siteVo) throws Exception {
        return siteDao.selectSiteDomainList(siteVo);
    }

    @Override
    @Transactional
    public Boolean insertSite(SiteVo siteVo) throws Exception {
        siteDao.insertSite(siteVo);
        if ("T".equals(siteVo.getSysKndCd()) && "Y".equals(siteVo.getUseYn())) { // 분양사이트이면서 사용여부가 Y일때
            // 기관회원의 기관관리자 역할 부여
            siteDao.insertInstRoleUser(siteVo);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean updateSite(SiteVo siteVo) throws Exception {
        siteDao.updateSite(siteVo);
        if ("T".equals(siteVo.getSysKndCd()) && !siteVo.getOldUseYn().equals(siteVo.getUseYn())) { // 분양사이트의 사용여부를 변경했을때
            if ("Y".equals(siteVo.getUseYn())) {
                // 기관회원의 기관관리자 역할 부여
                siteDao.insertInstRoleUser(siteVo);
            } else if("N".equals(siteVo.getUseYn())) {
                // 기관회원의 기관관리자 역할 회수
                siteDao.deleteInstRoleUser(siteVo);
            }
        }
        return true;
    }

    /**
    * 사이트 도메인정보 저장.
    *
    * @Title : saveSiteDomain
    * @Description : 사이트 도메인정보 저장
    * @param siteDomainVo SiteDomainVo객체
    * @return int deleteInsert로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int saveSiteDomain(SiteDomainVo siteDomainVo) throws Exception {
        int retVal = 0;
        retVal += siteDao.deleteSiteDomain(siteDomainVo);
        retVal += siteDao.insertSiteDomains(siteDomainVo);
        
        return retVal;
    }

    /**
    * 동일한 도메인이 있는지 조회한다.
    *
    * @Title : selectSameSiteDomains
    * @Description : 동일한 도메인이 있는지 조회한다
    * @param siteDomainVo SiteDomainVo객체
    * @return SiteDomainVo 중복도메인정보(domains)
    * @throws Exception 예외
    */
    @Override
    public SiteDomainVo selectSameSiteDomains(SiteDomainVo siteDomainVo) throws Exception {
        return siteDao.selectSameSiteDomains(siteDomainVo);
    }

    /**
    * 사이트에 메뉴가 존재하는지 확인한다.
    *
    * @Title : selectSiteMenuYn
    * @Description : 사이트에 메뉴가 존재하는지 확인한다.
    * @param siteid 사이트아이디
    * @return String 사이트 메뉴 존재 여부("Y" OR "N") 
    * @throws Exception 예외
    */
    @Override
    public String selectSiteMenuYn(Integer siteid) throws Exception {
        return siteDao.selectSiteMenuYn(siteid);
    }
    
    /**
    * 분양사이트 메뉴구성을 위한 프로그램 목록을 조회한다.
    *
    * @Title : selectSiteAplySetupPrgrmList
    * @Description : 분양사이트 메뉴구성을 위한 프로그램 목록을 조회한다.
    * @return List<SiteMenuSetupVo> 메뉴구성 프로그램 목록
    * @throws Exception 예외
    */
    @Override
    public List<SiteMenuSetupVo> selectSiteAplySetupPrgrmList() throws Exception {
        return siteDao.selectSiteAplySetupPrgrmList();
    }
    
    /**
    * 분양사이트를 구성하는 메뉴의 프로그램 목록을 조회한다.
    *
    * @Title : selectSiteAplySetupMenuList
    * @Description : 분양사이트를 구성하는 메뉴의 프로그램 목록을 조회한다. 
    * @param siteid 사이트아이디
    * @return List<SiteMenuSetupVo> 메뉴 프로그램 목록
    * @throws Exception 예외
    */
    @Override
    public List<SiteMenuSetupVo> selectSiteAplySetupMenuList(Integer siteid) throws Exception {
        return siteDao.selectSiteAplySetupMenuList(siteid);
    }
    
    /**
    * 분양사이트 메뉴구성을 저장한다.
    *
    * @Title : saveSiteAplyMenuSetup
    * @Description : 분양사이트 메뉴구성을 저장한다.
    * @param siteMenuSetupVo SiteMenuSetupVo객체
    * @return int 변경된 로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int saveSiteAplyMenuSetup(SiteMenuSetupVo siteMenuSetupVo) throws Exception {
        int retVal = 0;
        int rootOrder = 1;
        String pkey = "";
        String key = "";
        String order = "";
        MenuVo menu = null;
        Map<String, Integer> keyToMenuid = null;
        Map param = new HashMap<String, String>();
        param.put("siteid", siteMenuSetupVo.getSiteid());
        
        for (String prgrmid : siteMenuSetupVo.getPrgrmids()) {
            keyToMenuid = new HashMap<String, Integer>();
            List<TreeItem> prgrmList = siteDao.selectSiteAplyPrgrmTreeList(Integer.valueOf(prgrmid));
            
            for(TreeItem prgrmItem : prgrmList) {
                pkey = prgrmItem.getPkey();
                key = prgrmItem.getKey();
                order = prgrmItem.getExt1();
                
                if (order.equals("0")) {
                    order = String.valueOf(rootOrder);
                    keyToMenuid.put(pkey, 0);
                    rootOrder++;
                }
                
                menu = new MenuVo();
                menu.setUser(siteMenuSetupVo.getUser());
                menu.setPrgrmid(Integer.valueOf(key));
                menu.setOrd(Integer.valueOf(order));
                menu.setSiteid(siteMenuSetupVo.getSiteid());
                menu.setUpprMenuid(keyToMenuid.get(pkey));
                
                if (siteMenuSetupVo.getHidePrgrmids().stream().filter(s -> s.equals(prgrmItem.getKey())).count() > 0) {
                    menu.setHideYn("Y");
                }
                
                retVal += menuDao.insertMenuTreeList(menu);
                keyToMenuid.put(key, menu.getMenuid());
            }
        }
        // 트리뎁스 재조정
        retVal += menuDao.updateMenuTreeOrder(param);
        retVal += siteDao.insertSiteAllMenuRoleForGeneralUser(siteMenuSetupVo);
        
        return retVal;
    }
    
    /**
    * 분양사이트 메뉴구성을 초기화한다.
    *
    * @Title : initSiteAplyMenuSetup
    * @Description : 분양사이트 메뉴구성을 초기화 한다.
    * @param siteid 사이트아이디
    * @return int delete로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int initSiteAplyMenuSetup(Integer siteid) throws Exception {
        int retVal = 0;
        retVal += siteDao.deleteRoleMenuForSiteMenuInit(siteid);
        retVal += siteDao.deleteMenuForSiteMenuInit(siteid);
        
        return retVal;
    }
}
