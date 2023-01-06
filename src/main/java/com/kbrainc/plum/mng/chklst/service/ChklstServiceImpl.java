package com.kbrainc.plum.mng.chklst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.chklst.model.ChklstDao;
import com.kbrainc.plum.mng.chklst.model.ChklstQitemVo;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 체크리스트관리 서비스 구현
 *
 * <pre>
 * com.kbrainc.plum.mng.chklst.service
 * - ChklstServiceImpl.java
 * </pre> 
 *
 * @ClassName : ChklstServiceImpl
 * @Description : 체크리스트관리 서비스 구현 
 * @author : KBRAINC
 * @date : 2023. 01. 04.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class ChklstServiceImpl extends PlumAbstractServiceImpl implements ChklstService {
    
    @Autowired
    private ChklstDao chklstDao;
    
    /**
     * 체크리스트 문항 등록
     *
     * @Title : insertChklstQitem 
     * @Description : 체크리스트 문항 등록
     * @param chklstQitemVo ChklstQitemVo
     * @return int insert 로우수
     * @throws Exception 예외
     */
     @Override
     public int insertChklstQitem(ChklstQitemVo chklstQitemVo) throws Exception {
         int retVal = 0;
         retVal += chklstDao.insertChklstQitem(chklstQitemVo);
         
         return retVal;
     }
     
     /**
      * 체크리스트 문항 구분코드 목록 조회
      *
      * @Title : selectChklstQitemCdList
      * @Description : 체크리스트 문항 구분코드 목록 조회
      * @param codeVo CodeVo 객체
      * @return List<ChklstQitemVo> 체크리스트 문항 목록
      * @throws Exception 예외
      */
     @Override
     public List<CodeVo> selectChklstQitemCdList(CodeVo codeVo) throws Exception {
         return chklstDao.selectChklstQitemCdList(codeVo);
     }
     
    /**
     * 체크리스트 문항 목록 조회
     *
     * @Title : selectChklstQitemList
     * @Description : 체크리스트 문항 목록 조회
     * @param chklstQitemVo ChklstQitemVo 객체
     * @return List<ChklstQitemVo> 체크리스트 문항 목록
     * @throws Exception 예외
     */
    @Override
    public List<ChklstQitemVo> selectChklstQitemList(ChklstQitemVo chklstQitemVo) throws Exception {
        return chklstDao.selectChklstQitemList(chklstQitemVo);
    }
    
    /**
     * 체크리스트 문항 정보 조회
     *
     * @Title : selectChklstQitemInfo
     * @Description : 체크리스트 문항 정보 조회
     * @param chklstQitemVo ChklstQitemVo 객체
     * @return ChklstQitemVo ChklstQitemVo 객체
     * @throws Exception 예외
     */
    @Override
    public ChklstQitemVo selectChklstQitemInfo(ChklstQitemVo chklstQitemVo) throws Exception {
        return chklstDao.selectChklstQitemInfo(chklstQitemVo);
    }
      
    /**
     * 체크리스트 문항 업데이트
     *
     * @Title : updateChklstQitem
     * @Description : 체크리스트 문항 업데이트
     * @param chklstQitemVo ChklstQitemVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    @Override
    public int updateChklstQitem(ChklstQitemVo chklstQitemVo) throws Exception {
        int retVal = 0;
        retVal = chklstDao.updateChklstQitem(chklstQitemVo);        
             
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
//     @Override
//     public List<QitemVo> selectQitemList(QitemVo qitemVo) throws Exception {
//         return qestnrDao.selectQitemList(qitemVo);
//     }
    
    /**
    * 설문지 문항 정보 등록
     *
     * @Title : insertQitem 
     * @Description : 설문지 문항 정보 등록
     * @param qitemVo QitemVo
     * @return int insert 로우수
     * @throws Exception 예외
     */
//    @Override
//    @Transactional
//    public int insertQitem(QitemVo qitemVo) throws Exception {
//        int retVal = 0;
//        retVal = qestnrDao.insertQitem(qitemVo);
//        List<QitemExVo> exampleList = qitemVo.getExampleList();
//        if(exampleList != null && exampleList.size() > 0) {
//            QitemExVo qitemExVo = null;
//            for(int i = 0 ; i < exampleList.size() ; i++) {
//                qitemExVo = exampleList.get(i);
//                qitemExVo.setUser(qitemVo.getUser());
//                qitemExVo.setQitemid(qitemVo.getQitemid());
//                retVal = qestnrDao.insertQitemEx(qitemExVo);
//            }
//        }
//        
//        return retVal;
//    }
    
    /**
     * 설문지 문항 정보 조회
     *
     * @Title : selectQitemInfo
     * @Description : 설문지 문항 정보 조회
     * @param qitemVo QitemVo 객체
     * @return QitemVo QitemVo 객체
     * @throws Exception 예외
     */
//    @Override
//    public QitemVo selectQitemInfo(QitemVo qitemVo) throws Exception {
//        return qestnrDao.selectQitemInfo(qitemVo);
//    }
    
    /**
     * 설문지 문항 보기 목록 조회
     *
     * @Title : selectQitemExList
     * @Description : 설문지 문항 보기 목록 조회
     * @param QitemVo qitemVo 객체
     * @return List<QitemVo> 설문지 문항 목록
     * @throws Exception 예외
     */
//    @Override
//    public List<QitemExVo> selectQitemExList(QitemVo qitemVo) throws Exception {
//        return qestnrDao.selectQitemExList(qitemVo);
//    }
    
    /**
     * 설문지 문항 정보 업데이트
     *
     * @Title : updateQitem
     * @Description : 설문지 정보 업데이트
     * @param qitemVo QitemVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
//    @Override
//    @Transactional
//    public int updateQitem(QitemVo qitemVo) throws Exception {
//        int retVal = 0;
//        // 1. 이전 등록된 보기 삭제
//        String[] deleteIds = new String[1];
//        deleteIds[0] = String.valueOf(qitemVo.getQitemid());
//        qitemVo.setDeleteQitemids(deleteIds);
//        retVal = qestnrDao.deleteQitemEx(qitemVo);
//        // 2. 문항 정보 업데이트
//        retVal = qestnrDao.updateQitem(qitemVo);
//        // 3. 보기 등록
//        List<QitemExVo> exampleList = qitemVo.getExampleList();
//        if(exampleList != null && exampleList.size() > 0) {
//            QitemExVo qitemExVo = null;
//            for(int i = 0 ; i < exampleList.size() ; i++) {
//                qitemExVo = exampleList.get(i);
//                qitemExVo.setUser(qitemVo.getUser());
//                qitemExVo.setQitemid(qitemVo.getQitemid());
//                retVal = qestnrDao.insertQitemEx(qitemExVo);
//            }
//        }
//             
//        return retVal;
//    }
    
    /**
     * 설문지 문항 순서 업데이트
     *
     * @Title : updateQestnr
     * @Description : 설문지 정보 업데이트
     * @param qitemVo QitemVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
//    @Override
//    @Transactional
//    public int updateQitemOrdr(QitemVo qitemVo) throws Exception {
//        int retVal = 0;
//        int ordr = qitemVo.getOrdr();
//        String dir = qitemVo.getChangeDir();
//        // 1. 다음(이전) 문항 조회
//        QitemVo changeQitemVo = null;
//        if(dir.equals("next")) { // 다음 문항
//            changeQitemVo = qestnrDao.selectNextQitemInfo(qitemVo);
//            qitemVo.setOrdr(ordr + 1);
//            changeQitemVo.setOrdr(changeQitemVo.getOrdr() - 1);
//        } else { // 이전 문항
//            changeQitemVo = qestnrDao.selectPrevQitemInfo(qitemVo);
//            qitemVo.setOrdr(ordr - 1);
//            changeQitemVo.setOrdr(changeQitemVo.getOrdr() + 1);
//        }
//        // 2. 선택 문항 업데이트
//        retVal = qestnrDao.updateQitemOrdr(qitemVo);
//        // 3. 다음(이전) 문항 업데이트
//        retVal = qestnrDao.updateQitemOrdr(changeQitemVo);
//             
//        return retVal;
//    }
    
    /**
     * 설문지 문항 삭제
     *
     * @Title : updateQestnr
     * @Description : 설문지 정보 업데이트
     * @param qitemVo QitemVo 객체
     * @return int delete 로우수
     * @throws Exception 예외
     */
//    @Override
//    @Transactional
//    public int deleteQitem(QitemVo qitemVo) throws Exception {
//        int retVal = 0;
//        
//        // 1. 문항에 걸려있는 보기 삭제
//        retVal = qestnrDao.deleteQitemEx(qitemVo);
//        // 2. 문항 삭제
//        retVal = qestnrDao.deleteQitem(qitemVo);
//        // 3. 문항 조회 후 순서 업데이트
//        qitemVo.setOrderField("ORDR");
//        qitemVo.setOrderDirection(ORDER_DIRECTION.asc);
//        List<QitemVo> qitemList = qestnrDao.selectQitemList(qitemVo);
//        if(qitemList != null && qitemList.size() > 0) {
//            for(int i = 0; i < qitemList.size(); i++) {
//                QitemVo qitem = qitemList.get(i);
//                qitem.setOrdr(i + 1);
//                retVal = qestnrDao.updateQitemOrdr(qitem);
//            }
//        }
//        
//        return retVal;
//    }
    
    /**
     * 설문지 문항, 보기 목록 조회
     *
     * @Title : selectQitemWithExList
     * @Description : 설문지 문항, 보기 목록 조회
     * @param QitemVo qitemVo 객체
     * @return List<QitemVo> 설문지 문항, 보기 목록
     * @throws Exception 예외
     */
//    @Override
//    public List<QitemVo> selectQitemWithExList(QitemVo qitemVo) throws Exception {
//        List<QitemVo> qitemList = qestnrDao.selectQitemList(qitemVo);
//        if(qitemList != null && qitemList.size() > 0) {
//            for(int i = 0; i < qitemList.size(); i++) {
//                QitemVo qitem = qitemList.get(i);
//                int exCnt = qitem.getExCnt();
//                if(exCnt > 0) {
//                    List<QitemExVo> qitemExList = qestnrDao.selectQitemExList(qitem);
//                    qitem.setExampleList(qitemExList);
//                }
//            }
//        }
//        
//        return qitemList;
//    }
     
}