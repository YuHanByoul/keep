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
import com.kbrainc.plum.mng.srvy.model.SrvyInstVo;
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
         retVal += srvyDao.insertTrprSrvy(srvyVo);
         // 2. 개인회원 전체 대상인 경우 회원 등록
         if(srvyUserVo.getIndvdlMbrYn().equals("Y")) {
             srvyUserVo.setSrvyid(srvyVo.getSrvyid());
             retVal += srvyDao.insertIndvdlMbrSrvyUser(srvyUserVo);
         }
         if(retVal != 0) retVal = srvyVo.getSrvyid();
         
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
     * 개인회원 전체 대상 설문 대상자 등록
     *
     * @Title : insertIndvdlMbrSrvyUser 
     * @Description : 개인회원 전체 대상 설문 대상자 등록
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
        retVal += srvyDao.insertTrpr(srvyUserVo);
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
        retVal += srvyDao.deleteTrpr(srvyUserVo);
        return retVal;
    }
    
    /**
    * 대상자 엑셀 데이터 정합성 체크
    *
    * @Title : trprExcelDataCheck
    * @Description : 대상자 엑셀 데이터 정합성 체크
    * @param list ArrayList 엑셀 데이터
    * @return Map<String, Object> 데이터 정합성 체크 결과
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
     * 대상자설문 업데이트
     *
     * @Title : updateTrprSrvy
     * @Description : 대상자설문 업데이트
     * @param srvyVo SrvyVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    @Override
    public int updateTrprSrvy(SrvyVo srvyVo) throws Exception {
        int retVal = 0;
        retVal = srvyDao.updateTrprSrvy(srvyVo);        
             
        return retVal;
    }
    
    /**
     * 기관설문 등록
     *
     * @Title : insertInstSrvy 
     * @Description : 기관설문 등록
     * @param srvyVo SrvyVo 객체
     * @param srvyInstVo SrvyInstVo 객체
     * @return int srvyid
     * @throws Exception 예외
     */
     @Override
     @Transactional
     public int insertInstSrvy(SrvyVo srvyVo, SrvyInstVo srvyInstVo) throws Exception {
         int retVal = 0;
         // 1. 설문등록
         retVal += srvyDao.insertInstSrvy(srvyVo);
         // 2. 기관 전체 대상인 경우 대상기관 등록
         if(srvyInstVo.getInstYn().equals("Y")) {
             srvyInstVo.setSrvyid(srvyVo.getSrvyid());
             retVal += srvyDao.insertInstTrgtSrvyInst(srvyInstVo);
         }
         if(retVal != 0) retVal = srvyVo.getSrvyid();
         return retVal;
     }
    
    /**
     * 기관 목록 조회
     *
     * @Title : selectInstList
     * @Description : 기관 목록 조회
     * @param srvyInstVo SrvyInstVo 객체
     * @return List<SrvyInstVo> 기관 목록
     * @throws Exception 예외
     */
    public List<SrvyInstVo> selectInstList(SrvyInstVo srvyInstVo) throws Exception {
        return srvyDao.selectInstList(srvyInstVo);
    }
    
    /**
     * 기관설문 목록 조회
     *
     * @Title : selectInstSrvyList
     * @Description : 기관설문 목록 조회
     * @param srvyVo SrvyVo 객체
     * @return List<srvyVo> 대상자설문 목록
     * @throws Exception 예외
     */
    @Override
    public List<SrvyVo> selectInstSrvyList(SrvyVo srvyVo) throws Exception {
        return srvyDao.selectInstSrvyList(srvyVo);
    }
    
    /**
     * 기관설문 업데이트
     *
     * @Title : updateInstSrvy
     * @Description : 기관설문 업데이트
     * @param srvyVo SrvyVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    @Override
    public int updateInstSrvy(SrvyVo srvyVo) throws Exception {
        int retVal = 0;
        retVal = srvyDao.updateInstSrvy(srvyVo);        
             
        return retVal;
    }
    
    /**
     * 기관 전체 대상 설문 대상기관 등록
     *
     * @Title : insertInstTrgtSrvyInst 
     * @Description : 기관 전체 대상 설문 대상기관 등록
     * @param srvyInstVo SrvyInstVo 객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int insertInstTrgtSrvyInst(SrvyInstVo srvyInstVo) throws Exception {
        int retVal = 0;
        retVal = srvyDao.insertInstTrgtSrvyInst(srvyInstVo);
        return retVal;
    }
    
    /**
     * 설문 대상자 등록
     *
     * @Title : insertSrvyInst 
     * @Description : 설문 대상자 등록
     * @param srvyUserVo SrvyInstVo
     * @return int insert 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int insertSrvyInst(SrvyInstVo srvyInstVo) throws Exception {
        int retVal = 0;
        retVal = srvyDao.insertSrvyInst(srvyInstVo);
        return retVal;
    }
    
    /**
     * 설문 대상기관 목록 조회
     *
     * @Title : selectSrvyInstList
     * @Description : 설문 대상기관 목록 조회
     * @param srvyInstVo SrvyInstVo 객체
     * @return List<SrvyInstVo> 설문 대상기관 목록
     * @throws Exception 예외
     */
    public List<SrvyInstVo> selectSrvyInstList(SrvyInstVo srvyInstVo) throws Exception {
        return srvyDao.selectSrvyInstList(srvyInstVo);
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
    public int deleteSrvyInst(SrvyInstVo srvyInstVo) throws Exception {
        int retVal = 0;
        retVal = srvyDao.deleteSrvyInst(srvyInstVo);
        return retVal;
    }
    
    /**
    * 대상기관 엑셀 데이터 정합성 체크
    *
    * @Title : instExcelDataCheck
    * @Description : 대상기관 엑셀 데이터 정합성 체크
    * @param list ArrayList 엑셀 데이터
    * @return Map<String, Object> 데이터 정합성 체크 결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> instExcelDataCheck(ArrayList list) throws Exception {
        Map<String, Object> result = new HashMap<>();
        boolean isValid = true;
        ArrayList checkList = new ArrayList();
        String status = "정상";
        String instCd = "";
        
        if(list != null) {
            ArrayList data = null;
            for(int i = 1; i < list.size(); i++) { // 헤더를 제외하기 위해 1부터 시작
                data = (ArrayList) list.get(i);
                instCd = (String) data.get(0);
                data.add(0);
                data.add("");
                data.add("");
                if((instCd == null || instCd == "")) {
                    isValid = false;
                    status = "기관코드가 입력되지 않았습니다.";
                } else {
                    // 기관 존재 여부 확인
                    SrvyInstVo instVo = new SrvyInstVo();
                    instVo.setInstCd(instCd);
                    instVo = srvyDao.isExistInst(instVo);
                    if(instVo.getIsExist().equals("Y")) {
                        status = "정상";
                        data.set(1,instVo.getInstid());
                        data.set(2, instVo.getInstNm());
                        data.set(3, instVo.getRgnNm());
                    } else {
                        isValid = false;
                        status = "기관코드에 해당하는 기관이 없습니다.";
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
     * 컨설팅만족도설문 등록
     *
     * @Title : insertCnsltngDgstfnSrvy 
     * @Description : 컨설팅만족도설문 등록
     * @param srvyVo SrvyVo 객체
     * @return int srvyid
     * @throws Exception 예외
     */
     @Override
     public int insertCnsltngDgstfnSrvy(SrvyVo srvyVo) throws Exception {
         int retVal = 0;
         // 기본 설문 설정시 다른 컨설팅만족도설문은 미사용으로 등록
         retVal += srvyDao.insertCnsltngDgstfnSrvy(srvyVo);
         
         return retVal;
     }
    
    /**
     * 컨설팅만족도설문 목록 조회
     *
     * @Title : selectCnsltngDgstfnSrvyList
     * @Description : 컨설팅만족도설문 목록 조회
     * @param srvyVo SrvyVo 객체
     * @return List<srvyVo> 컨설팅만족도설문 목록
     * @throws Exception 예외
     */
    @Override
    public List<SrvyVo> selectCnsltngDgstfnSrvyList(SrvyVo srvyVo) throws Exception {
        return srvyDao.selectCnsltngDgstfnSrvyList(srvyVo);
    }
    
    /**
     * 컨설팅만족도설문 컨설팅 목록 조회
     *
     * @Title : selectCnsltngList
     * @Description : 컨설팅만족도설문 컨설팅 목록 조회
     * @param srvyVo SrvyVo 객체
     * @return List<srvyVo> 컨설팅만족도설문 컨설팅 목록
     * @throws Exception 예외
     */
    @Override
    public List<SrvyVo> selectCnsltngList(SrvyVo srvyVo) throws Exception {
        return srvyDao.selectCnsltngList(srvyVo);
    }
    
    /**
     * 컨설팅만족도설문 업데이트
     *
     * @Title : updateCnsltngDgstfnSrvy
     * @Description : 컨설팅만족도설문 업데이트
     * @param srvyVo SrvyVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    @Override
    public int updateCnsltngDgstfnSrvy(SrvyVo srvyVo) throws Exception {
        int retVal = 0;
        retVal = srvyDao.updateCnsltngDgstfnSrvy(srvyVo);        
             
        return retVal;
    }
    
}