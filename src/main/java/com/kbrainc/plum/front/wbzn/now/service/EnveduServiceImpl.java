package com.kbrainc.plum.front.wbzn.now.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.wbzn.now.model.BannerVo;
import com.kbrainc.plum.front.wbzn.now.model.EnveduDao;
import com.kbrainc.plum.front.wbzn.now.model.EnveduVo;
import com.kbrainc.plum.front.wbzn.now.model.PrgrmgdVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 웹진 > 환경교육NOW Service 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.wbzn.now.service
* - EnveduServiceImpl.java
* </pre>
*
* @ClassName : EnveduServiceImpl
* @Description : 웹진 > 환경교육NOW Service 구현 클래스
* @author : JD
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.enveduServiceImpl")
@Alias("front.enveduServiceImpl")
public class EnveduServiceImpl extends PlumAbstractServiceImpl implements EnveduService{
    
    @Resource(name = "front.enveduDao")
    private EnveduDao enveduDao;
    
    /**
    * 환경교육NOW 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 환경교육NOW 목록 조회
    * @param enveduVo
    * @throws Exception
    * @return List<EnveduVo>
    */
    @Override
    public List<EnveduVo> selectEnveduList(EnveduVo enveduVo) throws Exception {
        return enveduDao.selectEnveduList(enveduVo);
    }
    
    /**
    * 환경교육NOW 배너 목록 조회
    *
    * @Title : selectBannerList
    * @Description : 환경교육NOW 배너 목록 조회
    * @param enveduVo
    * @throws Exception
    * @return List<BannerVo>
    */
    @Override
    public List<BannerVo> selectBannerList(BannerVo enveduVo) throws Exception {
        return enveduDao.selectBannerList(enveduVo);
    }
    
    /**
    * 환경교육NOW 등록 연도 조회
    *
    * @Title : selectEnveduYrList
    * @Description : 환경교육NOW 등록 연도 조회
    * @param enveduVo
    * @throws Exception
    * @return List<EnveduVo>
    */
    @Override
    public List<EnveduVo> selectEnveduYrList(EnveduVo enveduVo) throws Exception {
        return enveduDao.selectEnveduYrList(enveduVo);
    }
    
    /**
    * 환경교육NOW 등록 월 조회
    *
    * @Title : selectEnveduMmList
    * @Description : 환경교육NOW 등록 월 조회
    * @param enveduVo
    * @throws Exception
    * @return List<EnveduVo>
    */
    @Override
    public List<EnveduVo> selectEnveduMmList(EnveduVo enveduVo) throws Exception {
        return enveduDao.selectEnveduMmList(enveduVo);
    }
    
    /**
    * 환경교육NOW 프로그램 등록 연도 조회
    *
    * @Title : selectPrgrmgdYrList
    * @Description : 환경교육NOW 프로그램 등록 연도 조회
    * @param prgrmgdVo
    * @throws Exception
    * @return List<PrgrmgdVo>
    */
    @Override
    public List<PrgrmgdVo> selectPrgrmgdYrList(PrgrmgdVo prgrmgdVo) throws Exception {
        return enveduDao.selectPrgrmgdYrList(prgrmgdVo);
    }
    
    /**
    * 환경교육NOW 프로그램 등록 월 조회
    *
    * @Title : selectPrgrmgdMmList
    * @Description : 환경교육NOW 프로그램 등록 월 조회
    * @param prgrmgdVo
    * @throws Exception
    * @return List<PrgrmgdVo>
    */
    @Override
    public List<PrgrmgdVo> selectPrgrmgdMmList(PrgrmgdVo prgrmgdVo) throws Exception {
        return enveduDao.selectPrgrmgdMmList(prgrmgdVo);
    }
    
    /**
    * 환경교육NOW 상세정보 조회
    *
    * @Title : selectEnveduInfo
    * @Description : 환경교육NOW 상세정보 조회
    * @param enveduVo
    * @throws Exception
    * @return EnveduVo
    */
    @Override
    public EnveduVo selectEnveduInfo(EnveduVo enveduVo) throws Exception{
        return enveduDao.selectEnveduInfo(enveduVo);
    }
    
    /**
    * 환경교육NOW 프로그램 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 환경교육NOW 프로그램 목록 조회
    * @param prgrmgdVo
    * @throws Exception
    * @return List<PrgrmgdVo>
    */
    @Override
    public List<PrgrmgdVo> selectPrgrmgdList(PrgrmgdVo prgrmgdVo) throws Exception {
        return enveduDao.selectPrgrmgdList(prgrmgdVo);
    }
    
    /**
    * 환경교육NOW 프로그램 상세정보 조회
    *
    * @Title : selectPrgrmgdInfo
    * @Description : 환경교육NOW 프로그램 상세정보 조회
    * @param prgrmgdVo
    * @throws Exception
    * @return PrgrmgdVo
    */
    @Override
    public PrgrmgdVo selectPrgrmgdInfo(PrgrmgdVo prgrmgdVo) throws Exception{
        return enveduDao.selectPrgrmgdInfo(prgrmgdVo);
    }



}
