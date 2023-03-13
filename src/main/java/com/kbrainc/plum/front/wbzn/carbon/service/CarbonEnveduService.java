package com.kbrainc.plum.front.wbzn.carbon.service;

import java.util.List;

import com.kbrainc.plum.front.wbzn.carbon.model.CarbonBannerVo;
import com.kbrainc.plum.front.wbzn.carbon.model.CarbonEnveduVo;
import com.kbrainc.plum.front.wbzn.carbon.model.CarbonPrgrmgdVo;

/**
* 웹진 > 탄소중립 환경교육 Service 클래스
*
* <pre>
* com.kbrainc.plum.front.wbzn.carbon.service
* - CarbonEnveduService.java
* </pre>
*
* @ClassName : CarbonEnveduService
* @Description : 웹진 > 탄소중립 환경교육 Service 클래스
* @author : JD
* @date : 2023. 2. 22.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface CarbonEnveduService {
    
    /**
    * 탄소중립 환경교육 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 탄소중립 환경교육 목록 조회
    * @param carbonEnveduVo
    * @throws Exception
    * @return List<CarbonEnveduVo>
    */
    public List<CarbonEnveduVo> selectEnveduList(CarbonEnveduVo carbonEnveduVo) throws Exception;
    
    /**
    * 탄소중립 환경교육 배너 목록 조회
    *
    * @Title : selectBannerList
    * @Description : 탄소중립 환경교육 배너 목록 조회
    * @param carbonBannerVo
    * @throws Exception
    * @return List<CarbonBannerVo>
    */
    public List<CarbonBannerVo> selectBannerList(CarbonBannerVo carbonBannerVo) throws Exception;
    
    /**
    * 탄소중립 환경교육 등록 연도 목록 조회
    *
    * @Title : selectEnveduYrList
    * @Description : 탄소중립 환경교육 등록 연도 목록 조회
    * @param carbonEnveduVo
    * @throws Exception
    * @return List<CarbonEnveduVo>
    */
    public List<CarbonEnveduVo> selectEnveduYrList(CarbonEnveduVo carbonEnveduVo) throws Exception;
    
    /**
    * 탄소중립 환경교육 등록 월 목록 조회
    *
    * @Title : selectEnveduMmList
    * @Description : 탄소중립 환경교육 등록 월 목록 조회
    * @param carbonEnveduVo
    * @throws Exception
    * @return List<CarbonEnveduVo>
    */
    public List<CarbonEnveduVo> selectEnveduMmList(CarbonEnveduVo carbonEnveduVo) throws Exception;
    
    /**
    * 탄소중립 환경교육 프로그램 등록 연도 조회
    *
    * @Title : selectPrgrmgdYrList
    * @Description : 탄소중립 환경교육 프로그램 등록 연도 조회
    * @param carbonPrgrmgdVo 
    * @throws Exception
    * @return List<CarbonPrgrmgdVo>
    */
    public List<CarbonPrgrmgdVo> selectPrgrmgdYrList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;
    
    /**
    * 탄소중립 환경교육 프로그램 등록 월 조회
    *
    * @Title : selectPrgrmgdMmList
    * @Description : 탄소중립 환경교육 프로그램 등록 월 조회
    * @param carbonPrgrmgdVo
    * @throws Exception
    * @return List<CarbonPrgrmgdVo>
    */
    public List<CarbonPrgrmgdVo> selectPrgrmgdMmList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;

    /**
    * 탄소중립 환경교육 상세정보 조회
    *
    * @Title : selectEnveduInfo
    * @Description : 탄소중립 환경교육 상세정보 조회
    * @param carbonEnveduVo
    * @throws Exception
    * @return CarbonEnveduVo
    */
    public CarbonEnveduVo selectEnveduInfo(CarbonEnveduVo carbonEnveduVo) throws Exception;
    
    /**
    * 탄소중립 환경교육 프로그램 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 탄소중립 환경교육 프로그램 목록 조회
    * @param carbonPrgrmgdVo
    * @throws Exception
    * @return List<CarbonPrgrmgdVo>
    */
    public List<CarbonPrgrmgdVo> selectPrgrmgdList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;
    
    /**
    * 탄소중립 환경교육 프로그램 상세정보 조회
    *
    * @Title : selectPrgrmgdInfo
    * @Description : 탄소중립 환경교육 프로그램 상세정보 조회
    * @param carbonPrgrmgdVo
    * @throws Exception
    * @return CarbonPrgrmgdVo
    */
    public CarbonPrgrmgdVo selectPrgrmgdInfo(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;
    
}
