package com.kbrainc.plum.mng.banner.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.site.model.SiteVo;

/**
* 배너관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.banner.model
* - BannerDao.java
* </pre>
*
* @ClassName : BannerDao
* @Description : 배너관리 Dao 클래스
* @author : JD
* @date : 2022. 12. 22.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface BannerDao {
    
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
