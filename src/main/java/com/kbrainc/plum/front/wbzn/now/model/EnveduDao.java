package com.kbrainc.plum.front.wbzn.now.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 웹진 > 환경교육NOW Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.wbzn.now.model
* - EnveduDao.java
* </pre>
*
* @ClassName : EnveduDao
* @Description : 웹진 > 환경교육NOW Dao 클래스
* @author : JD
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.enveduDao")
public interface EnveduDao {
    
    /**
    * 환경교육 NOW 메인 웹진 목록 조회
    *
    * @Title : selectEnveduExpsrList
    * @Description : 환경교육 NOW 메인 웹진 목록 조회
    * @param enveduVo
    * @throws Exception
    * @return List<EnveduVo>
    */
    public List<EnveduVo> selectEnveduExpsrList(EnveduVo enveduVo) throws Exception;
    
    /**
    * 환경교육NOW 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 환경교육NOW 목록 조회
    * @param enveduVo
    * @throws Exception
    * @return List<EnveduVo>
    */
    public List<EnveduVo> selectEnveduList(EnveduVo enveduVo) throws Exception;
    
    /**
    * 환경교육NOW 배너 목록 조회
    *
    * @Title : selectBannerList
    * @Description : 환경교육NOW 배너 목록 조회
    * @param enveduVo
    * @throws Exception
    * @return List<BannerVo>
    */
    public List<BannerVo> selectBannerList(BannerVo enveduVo) throws Exception;
    
    /**
    * 환경교육NOW 등록 연도 조회
    *
    * @Title : selectEnveduYrList
    * @Description : 환경교육NOW 등록 연도 조회
    * @param enveduVo
    * @throws Exception
    * @return List<EnveduVo>
    */
    public List<EnveduVo> selectEnveduYrList(EnveduVo enveduVo) throws Exception;
    
    /**
    * 환경교육NOW 등록 월 조회
    *
    * @Title : selectEnveduMmList
    * @Description : 환경교육NOW 등록 월 조회
    * @param enveduVo
    * @throws Exception
    * @return List<EnveduVo>
    */
    public List<EnveduVo> selectEnveduMmList(EnveduVo enveduVo) throws Exception;
    
    /**
    * 환경교육NOW 프로그램 등록 연도 조회
    *
    * @Title : selectPrgrmgdYrList
    * @Description : 환경교육NOW 프로그램 등록 연도 조회
    * @param prgrmgdVo
    * @throws Exception
    * @return List<PrgrmgdVo>
    */
    public List<PrgrmgdVo> selectPrgrmgdYrList(PrgrmgdVo prgrmgdVo) throws Exception;
    
    /**
    * 환경교육NOW 프로그램 등록 월 조회
    *
    * @Title : selectPrgrmgdMmList
    * @Description : 환경교육NOW 프로그램 등록 월 조회
    * @param prgrmgdVo
    * @throws Exception
    * @return List<PrgrmgdVo>
    */
    public List<PrgrmgdVo> selectPrgrmgdMmList(PrgrmgdVo prgrmgdVo) throws Exception;

    /**
    * 환경교육NOW 상세정보 조회
    *
    * @Title : selectEnveduInfo
    * @Description : 환경교육NOW 상세정보 조회
    * @param enveduVo
    * @throws Exception
    * @return EnveduVo
    */
    public EnveduVo selectEnveduInfo(EnveduVo enveduVo) throws Exception;
    
    /**
    * 환경교육NOW 프로그램 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 환경교육NOW 프로그램 목록 조회
    * @param prgrmgdVo
    * @throws Exception
    * @return List<PrgrmgdVo>
    */
    public List<PrgrmgdVo> selectPrgrmgdList(PrgrmgdVo prgrmgdVo) throws Exception;
    
    /**
    * 환경교육NOW 프로그램 상세정보 조회
    *
    * @Title : selectPrgrmgdInfo
    * @Description : 환경교육NOW 프로그램 상세정보 조회
    * @param prgrmgdVo
    * @throws Exception
    * @return PrgrmgdVo
    */
    public PrgrmgdVo selectPrgrmgdInfo(PrgrmgdVo prgrmgdVo) throws Exception;

    public int selectEnveduNextCount(EnveduVo enveduVo) throws Exception;
    
    public int selectEnveduPrevCount(EnveduVo enveduVo) throws Exception;
    
    public int selectPrgrmgdNextCount(PrgrmgdVo prgrmgdVo) throws Exception;
    
    public int selectPrgrmgdPrevCount(PrgrmgdVo prgrmgdVo) throws Exception;
    
}
