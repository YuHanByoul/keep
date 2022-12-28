package com.kbrainc.plum.mng.qestnr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.qestnr.model.QestnrDao;
import com.kbrainc.plum.mng.qestnr.model.QestnrSiteVo;
import com.kbrainc.plum.mng.qestnr.model.QestnrVo;
import com.kbrainc.plum.mng.qestnr.model.QitemExVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;
import com.kbrainc.plum.rte.model.ParentRequestVo.ORDER_DIRECTION;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

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
      * 사이트 목록 조회
      *
      * @Title : selectSiteList
      * @Description : 사이트 목록 조회
      * @param qestnrVo QestnrVo 객체
      * @return List<QestnrVo> 사이트 목록
      * @throws Exception 예외
      */
     @Override
     public List<QestnrVo> selectSiteList(QestnrVo qestnrVo) throws Exception {
         return qestnrDao.selectSiteList(qestnrVo);
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
     * @Description : 설문지 정보 조회
     * @param qestnrVo QestnrVo 객체
     * @return QestnrVo QestnrVo 객체
     * @throws Exception 예외
     */
    @Override
    public QestnrVo selectQestnrInfo(QestnrVo qestnrVo) throws Exception {
        return qestnrDao.selectQestnrInfo(qestnrVo);
    }
      
    /**
     * 설문지 정보 업데이트
     *
     * @Title : updateQestnr
     * @Description : 설문지 정보 업데이트
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
      * @Title : selectQitemList
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
        if(exampleList != null && exampleList.size() > 0) {
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
    
    /**
     * 설문지 문항 정보 조회
     *
     * @Title : selectQitemInfo
     * @Description : 설문지 문항 정보 조회
     * @param qitemVo QitemVo 객체
     * @return QitemVo QitemVo 객체
     * @throws Exception 예외
     */
    @Override
    public QitemVo selectQitemInfo(QitemVo qitemVo) throws Exception {
        return qestnrDao.selectQitemInfo(qitemVo);
    }
    
    /**
     * 설문지 문항 보기 목록 조회
     *
     * @Title : selectQitemExList
     * @Description : 설문지 문항 보기 목록 조회
     * @param QitemVo qitemVo 객체
     * @return List<QitemVo> 설문지 문항 목록
     * @throws Exception 예외
     */
    @Override
    public List<QitemExVo> selectQitemExList(QitemVo qitemVo) throws Exception {
        return qestnrDao.selectQitemExList(qitemVo);
    }
    
    /**
     * 설문지 문항 정보 업데이트
     *
     * @Title : updateQitem
     * @Description : 설문지 정보 업데이트
     * @param qitemVo QitemVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int updateQitem(QitemVo qitemVo) throws Exception {
        int retVal = 0;
        // 1. 이전 등록된 보기 삭제
        String[] deleteIds = new String[1];
        deleteIds[0] = String.valueOf(qitemVo.getQitemid());
        qitemVo.setDeleteQitemids(deleteIds);
        retVal = qestnrDao.deleteQitemEx(qitemVo);
        // 2. 문항 정보 업데이트
        retVal = qestnrDao.updateQitem(qitemVo);
        // 3. 보기 등록
        List<QitemExVo> exampleList = qitemVo.getExampleList();
        if(exampleList != null && exampleList.size() > 0) {
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
    
    /**
     * 설문지 문항 순서 업데이트
     *
     * @Title : updateQestnr
     * @Description : 설문지 정보 업데이트
     * @param qitemVo QitemVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int updateQitemOrdr(QitemVo qitemVo) throws Exception {
        int retVal = 0;
        int ordr = qitemVo.getOrdr();
        String dir = qitemVo.getChangeDir();
        // 1. 다음(이전) 문항 조회
        QitemVo changeQitemVo = null;
        if(dir.equals("next")) { // 다음 문항
            changeQitemVo = qestnrDao.selectNextQitemInfo(qitemVo);
            qitemVo.setOrdr(ordr + 1);
            changeQitemVo.setOrdr(changeQitemVo.getOrdr() - 1);
        } else { // 이전 문항
            changeQitemVo = qestnrDao.selectPrevQitemInfo(qitemVo);
            qitemVo.setOrdr(ordr - 1);
            changeQitemVo.setOrdr(changeQitemVo.getOrdr() + 1);
        }
        // 2. 선택 문항 업데이트
        retVal = qestnrDao.updateQitemOrdr(qitemVo);
        // 3. 다음(이전) 문항 업데이트
        retVal = qestnrDao.updateQitemOrdr(changeQitemVo);
             
        return retVal;
    }
    
    /**
     * 설문지 문항 삭제
     *
     * @Title : updateQestnr
     * @Description : 설문지 정보 업데이트
     * @param qitemVo QitemVo 객체
     * @return int delete 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int deleteQitem(QitemVo qitemVo) throws Exception {
        int retVal = 0;
        
        // 1. 문항에 걸려있는 보기 삭제
        retVal = qestnrDao.deleteQitemEx(qitemVo);
        // 2. 문항 삭제
        retVal = qestnrDao.deleteQitem(qitemVo);
        // 3. 문항 조회 후 순서 업데이트
        qitemVo.setOrderField("ORDR");
        qitemVo.setOrderDirection(ORDER_DIRECTION.asc);
        List<QitemVo> qitemList = qestnrDao.selectQitemList(qitemVo);
        if(qitemList != null && qitemList.size() > 0) {
            for(int i = 0; i < qitemList.size(); i++) {
                QitemVo qitem = qitemList.get(i);
                qitem.setOrdr(i + 1);
                retVal = qestnrDao.updateQitemOrdr(qitem);
            }
        }
        
        return retVal;
    }
     
}