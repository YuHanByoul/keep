package com.kbrainc.plum.mng.wbzn.carbon.banner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.wbzn.carbon.banner.model.CarbonBannerDao;
import com.kbrainc.plum.mng.wbzn.carbon.banner.model.CarbonBannerVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 탄소중립환경교육 -> 배너관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.banner.service
* - CarbonBannerServiceImpl.java
* </pre>
*
* @ClassName : CarbonBannerServiceImpl
* @Description : 탄소중립환경교육 -> 배너관리 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 29.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class CarbonBannerServiceImpl extends PlumAbstractServiceImpl implements CarbonBannerService{
    
    @Autowired
    private CarbonBannerDao bannerDao;
    
    /**
    * 배너 목록 조회
    *
    * @Title : selectBannerList
    * @Description : 배너 목록 조회
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return List<BannerVo>
    */
    @Override
    public List<CarbonBannerVo> selectBannerList(CarbonBannerVo bannerVo) throws Exception {
        return bannerDao.selectBannerList(bannerVo);
    }
    
    /**
    * 노출배너 목록 조회
    *
    * @Title : selectExpsrBannerList
    * @Description : 노출배너 목록 조회
    * @param bannerVo 배너관리 객체
    * @throws Exception
    * @return List<BannerVo>
    */
    @Override
    public List<CarbonBannerVo> selectExpsrBannerList(CarbonBannerVo bannerVo) throws Exception {
        return bannerDao.selectExpsrBannerList(bannerVo);
    }
    
    /**
    * 배너목록에서 노출배너로 등록
    *
    * @Title : updateExpsrBanner
    * @Description : 배너목록에서 노출배너로 등록
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return int
    * 
    */
    @Override
    @Transactional
    public int updateExpsrBanner(CarbonBannerVo bannerVo) throws Exception {
        return bannerDao.updateExpsrBanner(bannerVo);
    }

    /**
    * 노출배너 목록 순서정렬
    *
    * @Title : updateExpsrBannerSort
    * @Description : 노출배너 목록 순서정렬
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional
    public int updateExpsrBannerSort(CarbonBannerVo bannerVo) throws Exception {
        return bannerDao.updateExpsrBannerSort(bannerVo);
    }
    
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
    @Override
    @Transactional
    public int insertBanner(CarbonBannerVo bannerVo) throws Exception {
        return bannerDao.insertBanner(bannerVo);
    }
    
    /**
    * 배너정보 상세보기
    *
    * @Title : selectBannerInfo
    * @Description : 배너 상세보기
    * @param bannerVo 배너관리 객체
    * @throws Exception 예외
    * @return BannerVo
    */
    @Override
    public CarbonBannerVo selectBannerInfo(CarbonBannerVo bannerVo) throws Exception {
        return bannerDao.selectBannerInfo(bannerVo);
    }
    
    /**
    * 배너정보 수정
    *
    * @Title : updateBanner
    * @Description : 배너정보 수정
    * @param bannerVo 배너관리 객체
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int updateBanner(CarbonBannerVo bannerVo) throws Exception {
        return bannerDao.updateBanner(bannerVo);
    }
    
    /**
    * 배너 삭제
    *
    * @Title : deleteBanner
    * @Description : 배너 삭제
    * @param bannerVo 배너관리 객체
    * @throws Exception 에외
    * @return int
    */
    @Override
    @Transactional
    public int deleteBanner(CarbonBannerVo bannerVo) throws Exception {
        return bannerDao.deleteBanner(bannerVo);
    }
}
