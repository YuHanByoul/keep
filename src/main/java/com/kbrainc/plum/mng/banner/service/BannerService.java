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
    
    /**
    * 사이트 목록 조회(사용자 포털)
    *
    * @Title : selectSiteList
    * @Description : 사이트 목록 조회(사용자 포털)
    * @param siteVo 사이트정보 객체
    * @throws Exception 예외
    * @return List<SiteVo>
    */
    public List<SiteVo> selectSiteList(SiteVo siteVo) throws Exception;
    
    /**
    * 배너 목록 조회
    *
    * @Title : selectBannerList
    * @Description : 배너 목록 조회
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return List<BannerVo>
    */
    public List<BannerVo> selectBannerList(BannerVo bannerVo) throws Exception;
    
    /**
    * 노출배너 목록 조회
    *
    * @Title : selectExpsrBannerList
    * @Description : 노출배너 목록 조회
    * @param bannerVo 배너관리 객체
    * @throws Exception
    * @return List<BannerVo>
    */
    public List<BannerVo> selectExpsrBannerList(BannerVo bannerVo) throws Exception;
    
    /**
    * 배너목록에서 노출배너로 등록
    *
    * @Title : updateExpsrBanner
    * @Description : 배너목록에서 노출배너로 등록
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateExpsrBanner(BannerVo bannerVo) throws Exception;
    
    /**
    * 노출배너 목록 순서정렬
    *
    * @Title : updateExpsrBannerSort
    * @Description : 노출배너 목록 순서정렬
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateExpsrBannerSort(BannerVo bannerVo) throws Exception;
    
    /**
    * 배너 등록
    *
    * @Title : insertBanner
    * @Description : 배너 등록
    * @param bannerVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertBanner(BannerVo bannerVo) throws Exception;
    
    /**
    * 배너정보 상세보기
    *
    * @Title : selectBannerInfo
    * @Description : 배너 상세보기
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return BannerVo
    */
    public BannerVo selectBannerInfo(BannerVo bannerVo) throws Exception;
    
    /**
    * 배너정보 수정
    *
    * @Title : updateBanner
    * @Description : 배너정보 수정
    * @param bannerVo 배너관리 객체
    * @throws Exception
    * @return int
    */
    public int updateBanner(BannerVo bannerVo) throws Exception;
    
    /**
    * 배너 삭제
    *
    * @Title : deleteBanner
    * @Description : 배너 삭제
    * @param bannerVo 배너관리 객체
    * @throws Exception 에외
    * @return int
    */
    public int deleteBanner(BannerVo bannerVo) throws Exception;
}
