package com.kbrainc.plum.mng.refndMng.service;

import com.kbrainc.plum.mng.refndMng.model.RefndMngDao;
import com.kbrainc.plum.mng.refndMng.model.RefndMngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 언론보도관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.refndMng.service
* - RefndMngServiceImpl.java
* </pre>
*
* @ClassName : RefndMngServiceImpl
* @Description : 언론보도관리 서비스 구현 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class RefndMngServiceImpl extends PlumAbstractServiceImpl implements RefndMngService {
    
    @Autowired
    private RefndMngDao refndMngDao;
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectRefndMngList
    * @Description : 시설 목록 조회
    * @param refndMngVo 시설정보 객체
    * @throws Exception 예외
    * @return List<RefndMngVo>
    */
    @Override
    public List<RefndMngVo> selectRefndMngList(RefndMngVo refndMngVo) throws Exception{
        return refndMngDao.selectRefndMngList(refndMngVo);
    }
    
    /**
     * 시설명 중복 조회.
     *
     * @Title : checkDuplicateFcltNm
     * @Description : 시설명 중복 조회.
     * @param refndMngVo
     * @return int
     * @throws Exception 예외
     */
     @Override
     public int checkDuplicateFcltNm(RefndMngVo refndMngVo) throws Exception {
         return refndMngDao.checkDuplicateFcltNm(refndMngVo);
     }
     
     /**
      * 마지막 시설번호 조회.
      *
      * @Title : selectFcltNo
      * @Description : 마지막 시설번호 (제일 큰 값) 조회.
      * @param refndMngVo
      * @return RefndMngVo
      * @throws Exception 예외
      */
     @Override
     public RefndMngVo selectFcltNo(RefndMngVo refndMngVo) throws Exception {
         return refndMngDao.selectFcltNo(refndMngVo);
     }
     
     /**
      * 회원 기관데이터 조회.
      *
      * @Title : selectUserInst
      * @Description : 회원 기관데이터 조회.
      * @param instid
      * @return RefndMngVo
      * @throws Exception 예외
      */
     @Override
     public RefndMngVo selectUserInst(Integer instid) throws Exception {
         return refndMngDao.selectUserInst(instid);
     }

    
    /**
    * 시설 등록
    *
    * @Title : insertRefndMng
    * @Description : 시설 등록
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int insertRefndMng(RefndMngVo refndMngVo) throws Exception {
        return refndMngDao.insertRefndMng(refndMngVo);
    }
    
    /**
    * 시설 상세정보
    *
    * @Title : selectRefndMngInfo
    * @Description : 시설 상세정보
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return RefndMngVo
    */
    @Override
    public RefndMngVo selectRefndMngInfo(RefndMngVo refndMngVo) throws Exception {
        return refndMngDao.selectRefndMngInfo(refndMngVo);
    }
    
    /**
    * 시설 수정
    *
    * @Title : updateRefndMng
    * @Description : 시설 수정
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int updateRefndMng(RefndMngVo refndMngVo) throws Exception {
        return refndMngDao.updateRefndMng(refndMngVo);
    }
    
    /**
    * 시설 삭제
    *
    * @Title : deleteRefndMng
    * @Description : 시설 삭제
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int deleteRefndMng(RefndMngVo refndMngVo) throws Exception {
        return refndMngDao.deleteRefndMng(refndMngVo);
    }

    /**
    * 공간 목록 조회
    *
    * @Title : selectRefndMngSpceList
    * @Description : 공간 목록 조회
    * @param refndMngVo 시설정보 객체
    * @throws Exception 예외
    * @return List<RefndMngVo>
    */
    @Override
    public List<RefndMngVo> selectRefndMngSpceList(RefndMngVo refndMngVo) throws Exception{
        return refndMngDao.selectRefndMngSpceList(refndMngVo);
    }
}
