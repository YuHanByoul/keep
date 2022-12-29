package com.kbrainc.plum.mng.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
