package com.kbrainc.plum.mng.srvy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.qestnr.model.QestnrVo;
import com.kbrainc.plum.mng.srvy.model.SrvyDao;
import com.kbrainc.plum.mng.srvy.model.SrvyUserVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;
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
public class SrvyServiceImpl extends PlumAbstractServiceImpl implements SrvyService {
    
    @Autowired
    private SrvyDao srvyDao;
    
    /**
     * 대상자설문 등록
     *
     * @Title : insertTrprSrvy 
     * @Description : 대상자설문 등록
     * @param srvyVo SrvyVo 객체
     * @param srvyUserVo SrvyUserVo 객체
     * @return int srvyid
     * @throws Exception 예외
     */
     @Override
     @Transactional
     public int insertTrprSrvy(SrvyVo srvyVo, SrvyUserVo srvyUserVo) throws Exception {
         int retVal = 0;
         
         // 1. 설문등록
         srvyDao.insertTrprSrvy(srvyVo);
         // 2. 개인회원 전체 대상인 경우 회원 등록
         if(srvyUserVo.getIndvdlMbrYn().equals("Y")) {
             srvyUserVo.setSrvyid(srvyVo.getSrvyid());
             retVal += srvyDao.insertIndvdlMbrSrvyUser(srvyUserVo);
         }
         
         retVal = srvyVo.getSrvyid();
         return retVal;
     }
     
    /**
    * 설문지 목록 조회
    *
    * @Title : selectSrvyList
    * @Description : 설문지 목록 조회
    * @param srvyVo SrvyVo 객체
    * @return List<QestnrVo> 설문지 목록
    * @throws Exception 예외
    */
    @Override
    public List<QestnrVo> selectQestnrList(SrvyVo srvyVo) throws Exception {
        return srvyDao.selectQestnrList(srvyVo);
    }
    
    /**
     * 대상자설문 목록 조회
     *
     * @Title : selectTrprSrvyList
     * @Description : 대상자설문 목록 조회
     * @param srvyVo SrvyVo 객체
     * @return List<srvyVo> 대상자설문 목록
     * @throws Exception 예외
     */
    @Override
    public List<SrvyVo> selectTrprSrvyList(SrvyVo srvyVo) throws Exception {
        return srvyDao.selectTrprSrvyList(srvyVo);
    }
     
    /**
     * 설문 정보 조회
     *
     * @Title : selectSrvyInfo
     * @Description : 설문 정보 조회
     * @param srvyVo SrvyVo 객체
     * @return SrvyVo SrvyVo 객체
     * @throws Exception 예외
     */
    @Override
    public SrvyVo selectSrvyInfo(SrvyVo srvyVo) throws Exception {
        return srvyDao.selectSrvyInfo(srvyVo);
    }
    
    /**
     * 설문 대상자 목록 조회
     *
     * @Title : selectTrprList
     * @Description : 설문 대상자 목록 조회
     * @param srvyUserVo SrvyUserVo 객체
     * @return List<SrvyUserVo> 설문 대상자 목록
     * @throws Exception 예외
     */
    public List<SrvyUserVo> selectTrprList(SrvyUserVo srvyUserVo) throws Exception {
        return srvyDao.selectTrprList(srvyUserVo);
    }
    
    /**
     * 회원 목록 조회
     *
     * @Title : selectUserList
     * @Description : 회원 목록 조회
     * @param srvyUserVo SrvyUserVo 객체
     * @return List<SrvyUserVo> 회원 목록
     * @throws Exception 예외
     */
    public List<SrvyUserVo> selectUserList(SrvyUserVo srvyUserVo) throws Exception {
        return srvyDao.selectUserList(srvyUserVo);
    }
    
    /**
     * 설문 대상자 등록
     *
     * @Title : insertIndvdlMbrSrvyUser 
     * @Description : 설문 대상자 등록
     * @param srvyUserVo SrvyUserVo 객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int insertIndvdlMbrSrvyUser(SrvyUserVo srvyUserVo) throws Exception {
        int retVal = 0;
        retVal = srvyDao.insertIndvdlMbrSrvyUser(srvyUserVo);
        return retVal;
    }
    
    /**
     * 설문 대상자 등록
     *
     * @Title : insertTrpr 
     * @Description : 설문 대상자 등록
     * @param srvyUserVo SrvyUserVo
     * @return int insert 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int insertTrpr(SrvyUserVo srvyUserVo) throws Exception {
        int retVal = 0;
        retVal = srvyDao.insertTrpr(srvyUserVo);
        return retVal;
    }
    
    /**
     * 설문 대상자 삭제
     *
     * @Title : deleteTrpr 
     * @Description : 설문 대상자 삭제
     * @param srvyVo SrvyUserVo
     * @return int delete 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int deleteTrpr(SrvyUserVo srvyUserVo) throws Exception {
        int retVal = 0;
        retVal = srvyDao.deleteTrpr(srvyUserVo);
        return retVal;
    }
    
    /**
    * 대상자 엑셀 데이터 정합성 체크
    *
    * @Title : selectSrvyList
    * @Description : 대상자 엑셀 데이터 정합성 체크
    * @param list ArrayList 엑셀 데이터
    * @return List<SrvyUserVo> 설문지 목록
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> trprExcelDataCheck(ArrayList list) throws Exception {
        Map<String, Object> result = new HashMap<>();
        boolean isValid = true;
        ArrayList checkList = new ArrayList();
        String status = "정상";
        String acnt = "";
        String nm = "";
        
        if(list != null) {
            ArrayList data = null;
            for(int i = 1; i < list.size(); i++) { // 헤더를 제외하기 위해 1부터 시작
                data = (ArrayList) list.get(i);
                data.add(0);
                data.add("");
                acnt = (String) data.get(0);
                nm = (String) data.get(1);
                if((acnt == null || acnt == "") && (nm == null || nm == "")) {
                    isValid = false;
                    status = "아이디와 이름이 입력되지 않았습니다.";
                } else if(acnt == null || acnt == "") {
                    isValid = false;
                    data.add("-");
                    status = "아이디가 입력되지 않았습니다.";
                } else if(nm == null || nm == "") {
                    isValid = false;
                    data.add("-");
                    status = "이름이 입력되지 않았습니다.";
                } else {
                    // 회원 존재 여부 확인
                    SrvyUserVo userVo = new SrvyUserVo();
                    userVo.setAcnt((String) data.get(0));
                    userVo.setNm((String) data.get(1));
                    userVo = srvyDao.isExistUser(userVo);
                    if(userVo.getIsExist().equals("Y")) {
                        status = "정상";
                        data.set(2, userVo.getUserid());
                        data.set(3, userVo.getInstNm());
                    } else {
                        isValid = false;
                        status = "아이디와 이름에 해당하는 회원이 없습니다.";
                    }
                }
                data.add(status);
                checkList.add(data);
            }
        }
        result.put("validYn", isValid ? "Y" : "N");
        result.put("checkList", checkList);
        
        return result;
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
//    @Override
//    public int updateQestnr(QestnrVo qestnrVo) throws Exception {
//        int retVal = 0;
//        retVal = qestnrDao.updateQestnr(qestnrVo);        
//             
//        return retVal;
//    }
     
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
     
}