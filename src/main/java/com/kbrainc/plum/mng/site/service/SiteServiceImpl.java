package com.kbrainc.plum.mng.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.site.model.SiteDao;
import com.kbrainc.plum.mng.site.model.SiteDomainVo;
import com.kbrainc.plum.mng.site.model.SiteVo;
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
    public Boolean insertSite(SiteVo siteVo) throws Exception {
        siteDao.insertSite(siteVo);
        return true;
    }

    @Override
    public Boolean updateSite(SiteVo siteVo) throws Exception {
        siteDao.updateSite(siteVo);
        return true;
    }

    @Override
    public Boolean deleteSite(Integer siteid) throws Exception {
        siteDao.deleteSite(siteid);
        return true;
    }

    @Override
    public Boolean insertSiteDomain(SiteDomainVo siteDomainVo) throws Exception {
        try {
            siteDao.insertSiteDomain(siteDomainVo);
        }catch(DataIntegrityViolationException e) { //도메인 중복.
            //e.printStackTrace();
            return false;
        }
        
        return true;
    }

    @Override
    public Boolean updateSiteDomain(SiteDomainVo siteDomainVo) throws Exception {
        try {
            siteDao.updateSiteDomain(siteDomainVo);
        }catch(DataIntegrityViolationException e) { //도메인 중복.
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteSiteDomain(SiteDomainVo siteDomainVo) throws Exception {
        siteDao.deleteSiteDomain(siteDomainVo);
        return true;
    }

}
