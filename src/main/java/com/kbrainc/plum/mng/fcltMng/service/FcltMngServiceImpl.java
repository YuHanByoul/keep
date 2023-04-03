package com.kbrainc.plum.mng.fcltMng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.fcltMng.model.FcltMngDao;
import com.kbrainc.plum.mng.fcltMng.model.FcltMngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 언론보도관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.fcltMng.service
* - FcltMngServiceImpl.java
* </pre>
*
* @ClassName : FcltMngServiceImpl
* @Description : 언론보도관리 서비스 구현 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class FcltMngServiceImpl extends PlumAbstractServiceImpl implements FcltMngService{
    
    @Autowired
    private FcltMngDao fcltMngDao;
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectFcltMngList
    * @Description : 시설 목록 조회
    * @param fcltMngVo 시설정보 객체
    * @throws Exception 예외
    * @return List<FcltMngVo>
    */
    @Override
    public List<FcltMngVo> selectFcltMngList(FcltMngVo fcltMngVo) throws Exception{
        return fcltMngDao.selectFcltMngList(fcltMngVo);
    }
    
    /**
     * 시설명 중복 조회.
     *
     * @Title : checkDuplicateFcltNm
     * @Description : 시설명 중복 조회.
     * @param fcltMngVo
     * @return int
     * @throws Exception 예외
     */
     @Override
     public int checkDuplicateFcltNm(FcltMngVo fcltMngVo) throws Exception {
         return fcltMngDao.checkDuplicateFcltNm(fcltMngVo);
     }
     
     /**
      * 마지막 시설번호 조회.
      *
      * @Title : selectFcltNo
      * @Description : 마지막 시설번호 (제일 큰 값) 조회.
      * @param fcltMngVo
      * @return FcltMngVo
      * @throws Exception 예외
      */
     @Override
     public FcltMngVo selectFcltNo(FcltMngVo fcltMngVo) throws Exception {
         return fcltMngDao.selectFcltNo(fcltMngVo);
     }
     
     /**
      * 회원 기관데이터 조회.
      *
      * @Title : selectUserInst
      * @Description : 회원 기관데이터 조회.
      * @param instid
      * @return FcltMngVo
      * @throws Exception 예외
      */
     @Override
     public FcltMngVo selectUserInst(Integer instid) throws Exception {
         return fcltMngDao.selectUserInst(instid);
     }

    
    /**
    * 시설 등록
    *
    * @Title : insertFcltMng
    * @Description : 시설 등록
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int insertFcltMng(FcltMngVo fcltMngVo) throws Exception {
        return fcltMngDao.insertFcltMng(fcltMngVo);
    }
    
    /**
    * 시설 상세정보
    *
    * @Title : selectFcltMngInfo
    * @Description : 시설 상세정보
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return FcltMngVo
    */
    @Override
    public FcltMngVo selectFcltMngInfo(FcltMngVo fcltMngVo) throws Exception {
        return fcltMngDao.selectFcltMngInfo(fcltMngVo);
    }
    
    /**
    * 시설 수정
    *
    * @Title : updateFcltMng
    * @Description : 시설 수정
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int updateFcltMng(FcltMngVo fcltMngVo) throws Exception {
        return fcltMngDao.updateFcltMng(fcltMngVo);
    }
    
    /**
    * 시설 삭제
    *
    * @Title : deleteFcltMng
    * @Description : 시설 삭제
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int deleteFcltMng(FcltMngVo fcltMngVo) throws Exception {
        return fcltMngDao.deleteFcltMng(fcltMngVo);
    }

    /**
    * 공간 목록 조회
    *
    * @Title : selectFcltMngSpceList
    * @Description : 공간 목록 조회
    * @param fcltMngVo 시설정보 객체
    * @throws Exception 예외
    * @return List<FcltMngVo>
    */
    @Override
    public List<FcltMngVo> selectFcltMngSpceList(FcltMngVo fcltMngVo) throws Exception{
        return fcltMngDao.selectFcltMngSpceList(fcltMngVo);
    }
}
