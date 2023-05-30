package com.kbrainc.plum.front.wbzn.carbon.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.wbzn.carbon.model.CarbonBannerVo;
import com.kbrainc.plum.front.wbzn.carbon.model.CarbonEnveduDao;
import com.kbrainc.plum.front.wbzn.carbon.model.CarbonEnveduVo;
import com.kbrainc.plum.front.wbzn.carbon.model.CarbonPrgrmgdVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 웹진 > 탄소중립 환경교육 Service 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.wbzn.carbon.service
* - CarbonEnveduServiceImpl.java
* </pre>
*
* @ClassName : CarbonEnveduServiceImpl
* @Description : 웹진 > 탄소중립 환경교육 Service 클래스
* @author : JD
* @date : 2023. 2. 22.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.carbonEnveduServiceImpl")
@Alias("front.carbonEnveduServiceImpl")
public class CarbonEnveduServiceImpl extends PlumAbstractServiceImpl implements CarbonEnveduService{
    
    @Resource(name = "front.carbonEnveduDao")
    private CarbonEnveduDao carbonEnveduDao;
    
    /**
    * 탄소중립 환경교육 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 탄소중립 환경교육 목록 조회
    * @param carbonEnveduVo
    * @throws Exception
    * @return List<CarbonEnveduVo>
    */
    @Override
    public List<CarbonEnveduVo> selectEnveduList(CarbonEnveduVo carbonEnveduVo) throws Exception {
        return carbonEnveduDao.selectEnveduList(carbonEnveduVo);
    }
    
    /**
    * 탄소중립 환경교육 배너 목록 조회
    *
    * @Title : selectBannerList
    * @Description : 탄소중립 환경교육 배너 목록 조회
    * @param carbonBannerVo
    * @throws Exception
    * @return List<CarbonBannerVo>
    */
    @Override
    public List<CarbonBannerVo> selectBannerList(CarbonBannerVo carbonBannerVo) throws Exception {
        return carbonEnveduDao.selectBannerList(carbonBannerVo);
    }
    
    /**
    * 탄소중립 환경교육 등록 연도 목록 조회
    *
    * @Title : selectEnveduYrList
    * @Description : 탄소중립 환경교육 등록 연도 목록 조회
    * @param carbonEnveduVo
    * @throws Exception
    * @return List<CarbonEnveduVo>
    */
    @Override
    public List<CarbonEnveduVo> selectEnveduYrList(CarbonEnveduVo carbonEnveduVo) throws Exception {
        return carbonEnveduDao.selectEnveduYrList(carbonEnveduVo);
    }
    
    /**
    * 탄소중립 환경교육 등록 월 목록 조회
    *
    * @Title : selectEnveduMmList
    * @Description : 탄소중립 환경교육 등록 월 목록 조회
    * @param carbonEnveduVo
    * @throws Exception
    * @return List<CarbonEnveduVo>
    */
    @Override
    public List<CarbonEnveduVo> selectEnveduMmList(CarbonEnveduVo carbonEnveduVo) throws Exception {
        return carbonEnveduDao.selectEnveduMmList(carbonEnveduVo);
    }
    
    /**
    * 탄소중립 환경교육 프로그램 등록 연도 조회
    *
    * @Title : selectPrgrmgdYrList
    * @Description : 탄소중립 환경교육 프로그램 등록 연도 조회
    * @param carbonPrgrmgdVo 
    * @throws Exception
    * @return List<CarbonPrgrmgdVo>
    */
    @Override
    public List<CarbonPrgrmgdVo> selectPrgrmgdYrList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        return carbonEnveduDao.selectPrgrmgdYrList(carbonPrgrmgdVo);
    }
    
    /**
    * 탄소중립 환경교육 프로그램 등록 월 조회
    *
    * @Title : selectPrgrmgdMmList
    * @Description : 탄소중립 환경교육 프로그램 등록 월 조회
    * @param carbonPrgrmgdVo
    * @throws Exception
    * @return List<CarbonPrgrmgdVo>
    */
    @Override
    public List<CarbonPrgrmgdVo> selectPrgrmgdMmList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        return carbonEnveduDao.selectPrgrmgdMmList(carbonPrgrmgdVo);
    }
    
    /**
    * 탄소중립 환경교육 상세정보 조회
    *
    * @Title : selectEnveduInfo
    * @Description : 탄소중립 환경교육 상세정보 조회
    * @param carbonEnveduVo
    * @throws Exception
    * @return CarbonEnveduVo
    */
    @Override
    public CarbonEnveduVo selectEnveduInfo(CarbonEnveduVo carbonEnveduVo) throws Exception{
        return carbonEnveduDao.selectEnveduInfo(carbonEnveduVo);
    }
    
    /**
    * 탄소중립 환경교육 프로그램 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 탄소중립 환경교육 프로그램 목록 조회
    * @param carbonPrgrmgdVo
    * @throws Exception
    * @return List<CarbonPrgrmgdVo>
    */
    @Override
    public List<CarbonPrgrmgdVo> selectPrgrmgdList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        return carbonEnveduDao.selectPrgrmgdList(carbonPrgrmgdVo);
    }
    
    /**
    * 탄소중립 환경교육 프로그램 상세정보 조회
    *
    * @Title : selectPrgrmgdInfo
    * @Description : 탄소중립 환경교육 프로그램 상세정보 조회
    * @param carbonPrgrmgdVo
    * @throws Exception
    * @return CarbonPrgrmgdVo
    */
    @Override
    public CarbonPrgrmgdVo selectPrgrmgdInfo(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception{
        return carbonEnveduDao.selectPrgrmgdInfo(carbonPrgrmgdVo);
    }

    @Override
    public int selectEnveduNextCount(CarbonEnveduVo carbonEnveduVo) throws Exception {
        return carbonEnveduDao.selectEnveduNextCount(carbonEnveduVo);
    }

    @Override
    public int selectEnveduPrevCount(CarbonEnveduVo carbonEnveduVo) throws Exception {
        return carbonEnveduDao.selectEnveduPrevCount(carbonEnveduVo);
    }

    @Override
    public int selectEnveduNextCount(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        return carbonEnveduDao.selectEnveduNextCount(carbonPrgrmgdVo);
    }

    @Override
    public int selectEnveduPrevCount(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        return carbonEnveduDao.selectEnveduNextCount(carbonPrgrmgdVo);
    }



}
