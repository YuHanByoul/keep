package com.kbrainc.plum.mng.wbzn.carbon.banner.service;

import java.util.List;

import com.kbrainc.plum.mng.wbzn.carbon.banner.model.CarbonBannerVo;

/**
* 탄소중립환경교육 -> 배너관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.banner.service
* - CarbonBannerService.java
* </pre>
*
* @ClassName : CarbonBannerService
* @Description : 탄소중립환경교육 -> 배너관리 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 29.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface CarbonBannerService {
    
    /**
    * 배너 목록 조회
    *
    * @Title : selectBannerList
    * @Description : 배너 목록 조회
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return List<BannerVo>
    */
    public List<CarbonBannerVo> selectBannerList(CarbonBannerVo bannerVo) throws Exception;
    
    /**
    * 노출배너 목록 조회
    *
    * @Title : selectExpsrBannerList
    * @Description : 노출배너 목록 조회
    * @param bannerVo 배너관리 객체
    * @throws Exception
    * @return List<BannerVo>
    */
    public List<CarbonBannerVo> selectExpsrBannerList(CarbonBannerVo bannerVo) throws Exception;

    /**
    * 배너목록에서 노출배너로 등록
    *
    * @Title : updateExpsrBanner
    * @Description : 배너목록에서 노출배너로 등록
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateExpsrBanner(CarbonBannerVo bannerVo) throws Exception;

    /**
    * 노출배너 목록 순서정렬
    *
    * @Title : updateExpsrBannerSort
    * @Description : 노출배너 목록 순서정렬
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateExpsrBannerSort(CarbonBannerVo bannerVo) throws Exception;

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
    public int insertBanner(CarbonBannerVo bannerVo) throws Exception;

    /**
    * 배너정보 상세보기
    *
    * @Title : selectBannerInfo
    * @Description : 배너 상세보기
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return BannerVo
    */
    public CarbonBannerVo selectBannerInfo(CarbonBannerVo bannerVo) throws Exception;
    
    /**
    * 배너정보 수정
    *
    * @Title : updateBanner
    * @Description : 배너정보 수정
    * @param bannerVo 배너관리 객체
    * @throws Exception
    * @return int
    */
    public int updateBanner(CarbonBannerVo bannerVo) throws Exception;

    /**
    * 배너 삭제
    *
    * @Title : deleteBanner
    * @Description : 배너 삭제
    * @param bannerVo 배너관리 객체
    * @throws Exception 에외
    * @return int
    */
    public int deleteBanner(CarbonBannerVo bannerVo) throws Exception;
}
