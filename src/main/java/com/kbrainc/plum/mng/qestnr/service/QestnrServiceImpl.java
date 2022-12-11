package com.kbrainc.plum.mng.qestnr.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.qestnr.model.QestnrDao;
import com.kbrainc.plum.mng.qestnr.model.QestnrVo;
import com.kbrainc.plum.mng.qestnr.model.QitemExVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;

/**
 * 
 * 설문지관리 서비스 구현
 *
 * <pre>
 * com.kbrainc.plum.mng.qestnr.service
 * - QestnrServiceImpl.java
 * </pre> 
 *
 * @ClassName : QestnrServiceImpl
 * @Description : 설문지관리 서비스 구현 
 * @author : KBRAINC
 * @date : 2022. 11. 29.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class QestnrServiceImpl extends PlumAbstractServiceImpl implements QestnrService {
    
    @Autowired
    private QestnrDao qestnrDao;
    
    /**
     * 설문지 정보 등록
     *
     * @Title : insertQestnr 
     * @Description : 설문지 정보 등록
     * @param qestnrVo QestnrVo
     * @return int insert 로우수
     * @throws Exception 예외
     */
     @Override
     public int insertQestnr(QestnrVo qestnrVo) throws Exception {
         int retVal = 0;
         retVal += qestnrDao.insertQestnr(qestnrVo);
         
         return retVal;
     }
     
    /**
     * 설문지 목록 조회
     *
     * @Title : selectQestnrList
     * @Description : 설문지 목록 조회
     * @param qestnrVo QestnrVo 객체
     * @return List<QestnrVo> 설문지 목록
     * @throws Exception 예외
     */
    @Override
    public List<QestnrVo> selectQestnrList(QestnrVo qestnrVo) throws Exception {
        return qestnrDao.selectQestnrList(qestnrVo);
    }
      
    /**
     * 설문지 정보 조회
     *
     * @Title : selectQestnrInfo
     * @Description : 설문지 상세정보 조회
     * @param qestnrVo QestnrVo 객체
     * @return QestnrVo QestnrVo 객체
     * @throws Exception 예외
     */
    @Override
    public QestnrVo selectQestnrInfo(QestnrVo qestnrVo) throws Exception {
        return qestnrDao.selectQestnrInfo(qestnrVo);
    }
      
    /**
     * 설문지 정보 수정
     *
     * @Title : updateQestnr
     * @Description : 설문지 정보 수정
     * @param qestnrVo QestnrVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    @Override
    public int updateQestnr(QestnrVo qestnrVo) throws Exception {
        int retVal = 0;
        retVal = qestnrDao.updateQestnr(qestnrVo);        
             
        return retVal;
    }
     
     /**
      * 설문지 문항 목록 조회
      *
      * @Title : selectQestnrList
      * @Description : 설문지 목록 조회
      * @param QitemVo qitemVo 객체
      * @return List<QitemVo> 설문지 문항 목록
      * @throws Exception 예외
      */
     @Override
     public List<QitemVo> selectQitemList(QitemVo qitemVo) throws Exception {
         return qestnrDao.selectQitemList(qitemVo);
     }
    
    /**
    * 설문지 문항 정보 등록
     *
     * @Title : insertQitem 
     * @Description : 설문지 문항 정보 등록
     * @param qitemVo QitemVo
     * @return int insert 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int insertQitem(QitemVo qitemVo) throws Exception {
        int retVal = 0;
        retVal = qestnrDao.insertQitem(qitemVo);
        List<QitemExVo> exampleList = qitemVo.getExampleList();
        if(exampleList.size() > 0) {
            QitemExVo qitemExVo = null;
            for(int i = 0 ; i < exampleList.size() ; i++) {
                qitemExVo = exampleList.get(i);
                qitemExVo.setUser(qitemVo.getUser());
                qitemExVo.setQitemid(qitemVo.getQitemid());
                retVal = qestnrDao.insertQitemEx(qitemExVo);
            }
        }
        
        return retVal;
    }
     
}