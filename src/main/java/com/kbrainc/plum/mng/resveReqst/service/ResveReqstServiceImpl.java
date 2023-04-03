package com.kbrainc.plum.mng.resveReqst.service;

import com.kbrainc.plum.mng.resveReqst.model.ResveReqstDao;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 언론보도관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.resveReqst.service
* - ResveReqstServiceImpl.java
* </pre>
*
* @ClassName : ResveReqstServiceImpl
* @Description : 언론보도관리 서비스 구현 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class ResveReqstServiceImpl extends PlumAbstractServiceImpl implements ResveReqstService {
    
    @Autowired
    private ResveReqstDao resveReqstDao;
    
    /**
    * 예약내역 목록 조회
    *
    * @Title : selectResveReqstList
    * @Description : 예약내역 목록 조회
    * @param resveReqstVo 예약내역 객체
    * @throws Exception 예외
    * @return List<ResveReqstVo>
    */
    @Override
    public List<ResveReqstVo> selectResveReqstList(ResveReqstVo resveReqstVo) throws Exception{
        return resveReqstDao.selectResveReqstList(resveReqstVo);
    }

    /**
    * 상태변경이력 목록 조회
    *
    * @Title : selectResveReqstList
    * @Description : 상태변경이력 목록 조회
    * @param resveReqstVo 예약내역 객체
    * @throws Exception 예외
    * @return List<ResveReqstVo>
    */
    @Override
    public List<ResveReqstVo> selectResveReqstHstryList(ResveReqstVo resveReqstVo) throws Exception{
        return resveReqstDao.selectResveReqstHstryList(resveReqstVo);
    }

    /**
     * 시설명 중복 조회.
     *
     * @Title : checkDuplicateFcltNm
     * @Description : 시설명 중복 조회.
     * @param resveReqstVo
     * @return int
     * @throws Exception 예외
     */
     @Override
     public int checkDuplicateFcltNm(ResveReqstVo resveReqstVo) throws Exception {
         return resveReqstDao.checkDuplicateFcltNm(resveReqstVo);
     }
     
     /**
      * 마지막 시설번호 조회.
      *
      * @Title : selectFcltNo
      * @Description : 마지막 시설번호 (제일 큰 값) 조회.
      * @param resveReqstVo
      * @return ResveReqstVo
      * @throws Exception 예외
      */
     @Override
     public ResveReqstVo selectFcltNo(ResveReqstVo resveReqstVo) throws Exception {
         return resveReqstDao.selectFcltNo(resveReqstVo);
     }
     
     /**
      * 회원 기관데이터 조회.
      *
      * @Title : selectUserInst
      * @Description : 회원 기관데이터 조회.
      * @param instid
      * @return ResveReqstVo
      * @throws Exception 예외
      */
     @Override
     public ResveReqstVo selectUserInst(Integer instid) throws Exception {
         return resveReqstDao.selectUserInst(instid);
     }

    
    /**
    * 시설 등록
    *
    * @Title : insertResveReqst
    * @Description : 시설 등록
    * @param resveReqstVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int insertResveReqst(ResveReqstVo resveReqstVo) throws Exception {
        return resveReqstDao.insertResveReqst(resveReqstVo);
    }
    
    /**
    * 예약내역 상세정보
    *
    * @Title : selectResveReqstInfo
    * @Description : 예약내역 상세정보
    * @param resveReqstVo 예약내역 객체
    * @throws Exception 예외
    * @return ResveReqstVo
    */
    @Override
    public ResveReqstVo selectResveReqstInfo(ResveReqstVo resveReqstVo) throws Exception {
        return resveReqstDao.selectResveReqstInfo(resveReqstVo);
    }

    /**
    * 상태변경이력 사유 상세정보
    *
    * @Title : selectResveReqstInfo
    * @Description : 상태변경이력 사유 상세정보
    * @param resveReqstVo 예약내역 객체
    * @throws Exception 예외
    * @return ResveReqstVo
    */
    @Override
    public ResveReqstVo selectHstryInfo(ResveReqstVo resveReqstVo) throws Exception {
        return resveReqstDao.selectHstryInfo(resveReqstVo);
    }

    /**
    * 시설 수정
    *
    * @Title : updateResveReqst
    * @Description : 시설 수정
    * @param resveReqstVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int updateResveReqst(ResveReqstVo resveReqstVo) throws Exception {
        return resveReqstDao.updateResveReqst(resveReqstVo);
    }

    /**
    * 상태변경이력 추가
    *
    * @Title : insertHstry
    * @Description : 상태변경이력 추가
    * @param resveReqstVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int insertHstry(ResveReqstVo resveReqstVo) throws Exception {
        return resveReqstDao.insertHstry(resveReqstVo);
    }
}
