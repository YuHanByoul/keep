package com.kbrainc.plum.mng.refndMng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.refndMng.model.RefndMngDao;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstDao;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

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
    
    @Autowired
    private ResveReqstDao resveReqstDao;
    
    /**
    * 환불 신청 목록 조회 
    *
    * @Title : selectRefndMngList
    * @Description : 환불 신청 목록 조회
    * @param ResveReqstVo  객체
    * @throws Exception 예외
    * @return List<RefndMngVo>
    */
    @Override
    public List<ResveReqstVo> selectRefndMngList(ResveReqstVo resveReqstVo) throws Exception{
        return refndMngDao.selectRefndMngList(resveReqstVo);
    }
    
    /**
     * 환불 완료 목록 조회
     *
     * @Title : selectRefndMngCompleteList
     * @Description : 환불 완료 목록 조회
     * @param ResveReqstVo 객체
     * @throws Exception 예외
     * @return List<RefndMngVo>
     */
     public List<ResveReqstVo> selectRefndMngCompleteList(ResveReqstVo resveReqstVo) throws Exception{
         return refndMngDao.selectRefndMngList(resveReqstVo);
     }

    /**
     * 환불 완료 처리 
     *
     * @Title : updateRefndComplete
     * @Description : 환불 완료 처리
     * @param ResveReqstVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Transactional
    public int updateRefndComplete(ResveReqstVo resveReqstVo) throws Exception{
        
        int resInt = 0 ;
        resInt += refndMngDao.updateRefndComplete(resveReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyid(resveReqstVo.getAplyid());
        paramVo.setUser(resveReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        return resInt;
    }

    /**
     * 환불 요청 취소 처리
     *
     * @Title : updateRefndCancel
     * @Description : 환불 요청 취소 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Transactional
    public int updateRefndCancel(ResveReqstVo resveReqstVo) throws Exception{
        int resInt = 0 ;
        resInt += refndMngDao.updateRefndCancel(resveReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyid(resveReqstVo.getAplyid());
        paramVo.setUser(resveReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        return resInt;
        
    }

    /**
     * 환불완료취소 처리
     *
     * @Title : updateRefndRollback
     * @Description : 환불완료취소 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Transactional
    public int updateRefndRollback(ResveReqstVo resveReqstVo) throws Exception{
        int resInt = 0 ;
        resInt += refndMngDao.updateRefndRollback(resveReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyid(resveReqstVo.getAplyid());
        paramVo.setUser(resveReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        return resInt;
    }
}
