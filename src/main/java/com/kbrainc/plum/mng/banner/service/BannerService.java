package com.kbrainc.plum.mng.banner.service;

import java.util.List;

import com.kbrainc.plum.mng.banner.model.BannerVo;
import com.kbrainc.plum.mng.site.model.SiteVo;

/**
* 배너관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.banner.service
* - BannerService.java
* </pre>
*
* @ClassName : BannerService
* @Description : 배너관리 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 22.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface BannerService {
    
    public List<SiteVo> selectSiteList(SiteVo siteVo) throws Exception;

    public List<BannerVo> selectBannerList(BannerVo bannerVo) throws Exception;

    public List<BannerVo> selectExpsrBannerList(BannerVo bannerVo) throws Exception;

    public int updateExpsrBanner(BannerVo bannerVo) throws Exception;

    public int updateExpsrBannerSort(BannerVo bannerVo) throws Exception;

    public int insertBanner(BannerVo bannerVo) throws Exception;

    public BannerVo selectBannerInfo(BannerVo bannerVo) throws Exception;
    
    public int updateBanner(BannerVo bannerVo) throws Exception;

    public int deleteBanner(BannerVo bannerVo) throws Exception;
    
}
