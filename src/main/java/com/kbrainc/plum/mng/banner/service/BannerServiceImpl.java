package com.kbrainc.plum.mng.banner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.banner.model.BannerDao;
import com.kbrainc.plum.mng.banner.model.BannerVo;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 배너관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.banner.service
* - BannerServiceImpl.java
* </pre>
*
* @ClassName : BannerServiceImpl
* @Description : 배너관리 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 22.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class BannerServiceImpl extends PlumAbstractServiceImpl implements BannerService{
    
    @Autowired
    private BannerDao bannerDao;
    
    public List<SiteVo> selectSiteList(SiteVo siteVo) throws Exception {
        return bannerDao.selectSiteList(siteVo);
    }

    public List<BannerVo> selectBannerList(BannerVo bannerVo) throws Exception {
        return bannerDao.selectBannerList(bannerVo);
    }
    
    public List<BannerVo> selectExpsrBannerList(BannerVo bannerVo) throws Exception {
        return bannerDao.selectExpsrBannerList(bannerVo);
    }    
    
    
    public int updateExpsrBanner(BannerVo bannerVo) throws Exception {
        return bannerDao.updateExpsrBanner(bannerVo);
    }

    public int updateExpsrBannerSort(BannerVo bannerVo) throws Exception {
        return bannerDao.updateExpsrBannerSort(bannerVo);
    }
    
    public int insertBanner(BannerVo bannerVo) throws Exception {
        return bannerDao.insertBanner(bannerVo);
    }
    
    public BannerVo selectBannerInfo(BannerVo bannerVo) throws Exception {
        return bannerDao.selectBannerInfo(bannerVo);
    }
}
