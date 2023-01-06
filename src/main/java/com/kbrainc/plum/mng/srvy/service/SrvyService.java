package com.kbrainc.plum.mng.srvy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kbrainc.plum.mng.qestnr.model.QestnrVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;
import com.kbrainc.plum.mng.srvy.model.SrvyAnsVo;
import com.kbrainc.plum.mng.srvy.model.SrvyInstVo;
import com.kbrainc.plum.mng.srvy.model.SrvyUserVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;

/**
 * 
 * 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.qestnr.service
 * - QestnrService.java
 * </pre> 
 *
 * @ClassName : SrvyService
 * @Description : 설문관리 서비스 인터페이스 
 * @author : KBRAINC
 * @date : 2022. 12. 21.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface SrvyService {
    
    /**
     * 대상자설문 등록
     *
     * @Title : insertTrprSrvy 
     * @Description : 대상자설문 등록
     * @param srvyVo SrvyVo객체
     * @param srvyUserVo SrvyUserVo객체
     * @return int srvyid
     * @throws Exception 예외
     */
    public int insertTrprSrvy(SrvyVo srvyVo, SrvyUserVo srvyUserVo) throws Exception;
    
    /**
     * 설문지 목록 조회
     *
     * @Title : selectQestnrList 
     * @Description : 설문지 목록 조회
     * @param srvyVo SrvyVo객체
     * @return List<QestnrVo> 설문지 목록
     * @throws Exception 예외
     */
    public List<QestnrVo> selectQestnrList(SrvyVo srvyVo) throws Exception;
    
    /**
     * 대상자설문 목록 조회
     *
     * @Title : selectTrprSrvyList 
     * @Description : 대상자설문 목록 조회
     * @param srvyVo SrvyVo객체
     * @return List<SrvyVo> 대상자설문 목록
     * @throws Exception 예외
     */
    public List<SrvyVo> selectTrprSrvyList(SrvyVo srvyVo) throws Exception;
    
    /**
     * 설문 정보 조회
     *
     * @Title : selectSrvyInfo
     * @Description : 설문 정보 조회
     * @param srvyVo SrvyVo 객체
     * @return SrvyVo SrvyVo 객체
     * @throws Exception 예외
     */
    public SrvyVo selectSrvyInfo(SrvyVo srvyVo) throws Exception;
    
    /**
     * 설문 대상자 목록 조회
     *
     * @Title : selectTrprList
     * @Description : 설문 대상자 목록 조회
     * @param srvyUserVo SrvyUserVo 객체
     * @return List<SrvyUserVo> 설문 대상자 목록
     * @throws Exception 예외
     */
    public List<SrvyUserVo> selectTrprList(SrvyUserVo srvyUserVo) throws Exception;
    
    /**
     * 회원 목록 조회
     *
     * @Title : selectUserList
     * @Description : 회원 목록 조회
     * @param srvyUserVo SrvyUserVo 객체
     * @return List<SrvyUserVo> 회원 목록
     * @throws Exception 예외
     */
    public List<SrvyUserVo> selectUserList(SrvyUserVo srvyUserVo) throws Exception;
    
    /**
     * 개인회원 전체 대상 설문 대상자 등록
     *
     * @Title : insertIndvdlMbrSrvyUser 
     * @Description : 개인회원 전체 대상 설문 대상자 등록
     * @param srvyUserVo SrvyUserVo 객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertIndvdlMbrSrvyUser(SrvyUserVo srvyUserVo) throws Exception;
    
    /**
     * 설문 대상자 등록
     *
     * @Title : insertTrpr 
     * @Description : 설문 대상자 등록
     * @param srvyUserVo SrvyUserVo객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertTrpr(SrvyUserVo srvyUserVo) throws Exception;
    
    /**
     * 설문 대상자 삭제
     *
     * @Title : deleteTrpr 
     * @Description : 설문 대상자 삭제
     * @param srvyUserVo SrvyUserVo객체
     * @return int delete 로우수
     * @throws Exception 예외
     */
    public int deleteTrpr(SrvyUserVo srvyUserVo) throws Exception;
    
    /**
     * 대상자 엑셀 데이터 정합성 체크
     *
     * @Title : trprExcelDataCheck 
     * @Description : 대상자 엑셀 데이터 정합성 체크
     * @param list ArrayList 엑셀 데이터
     * @return ArrayList 엑셀 데이터 정합성 체크 목록
     * @throws Exception 예외
     */
    public Map<String, Object> trprExcelDataCheck(ArrayList list) throws Exception;
    
    /**
     * 대상자설문 업데이트
     *
     * @Title : updateTrprSrvy
     * @Description : 대상자설문 업데이트
     * @param srvyVo SrvyVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateTrprSrvy(SrvyVo srvyVo) throws Exception;
 
    /**
     * 기관 목록 조회
     *
     * @Title : selectInstList
     * @Description : 기관 목록 조회
     * @param srvyInstVo SrvyInstVo 객체
     * @return List<SrvyInstVo> 기관 목록
     * @throws Exception 예외
     */
    public List<SrvyInstVo> selectInstList(SrvyInstVo srvyInstVo) throws Exception;
    
    /**
     * 기관설문 등록
     *
     * @Title : insertInstSrvy 
     * @Description : 기관설문 등록
     * @param srvyVo SrvyVo객체
     * @param srvyInstVo SrvyInstVo객체
     * @return int srvyid
     * @throws Exception 예외
     */
    public int insertInstSrvy(SrvyVo srvyVo, SrvyInstVo srvyInstVo) throws Exception;
    
    /**
     * 기관설문 목록 조회
     *
     * @Title : selectInstSrvyList 
     * @Description : 기관설문 목록 조회
     * @param srvyVo SrvyVo객체
     * @return List<SrvyVo> 대상자설문 목록
     * @throws Exception 예외
     */
    public List<SrvyVo> selectInstSrvyList(SrvyVo srvyVo) throws Exception;
    
    /**
     * 기관설문 업데이트
     *
     * @Title : updateInstSrvy
     * @Description : 기관설문 업데이트
     * @param srvyVo SrvyVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateInstSrvy(SrvyVo srvyVo) throws Exception;
    
    /**
     * 기관 전체 대상 설문 대상기관 등록
     *
     * @Title : insertInstTrgtSrvyInst 
     * @Description : 기관 전체 대상 설문 대상기관 등록
     * @param srvyInstVo SrvyInstVo 객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertInstTrgtSrvyInst(SrvyInstVo srvyInstVo) throws Exception;
    
    /**
     * 설문 대상기관 등록
     *
     * @Title : insertSrvyInst 
     * @Description : 설문 대상기관 등록
     * @param srvyInstVo SrvyInstVo객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertSrvyInst(SrvyInstVo srvyInstVo) throws Exception;
    
    /**
     * 설문 대상기관 목록 조회
     *
     * @Title : selectSrvyInstList
     * @Description : 설문 대상기관 목록 조회
     * @param srvyInstVo SrvyInstVo 객체
     * @return List<SrvyInstVo> 설문 대상기관 목록
     * @throws Exception 예외
     */
    public List<SrvyInstVo> selectSrvyInstList(SrvyInstVo srvyInstVo) throws Exception;
    
    /**
     * 설문 대상기관 삭제
     *
     * @Title : deleteSrvyInst 
     * @Description : 설문 대상기관 삭제
     * @param srvyInstVo SrvyInstVo객체
     * @return int delete 로우수
     * @throws Exception 예외
     */
    public int deleteSrvyInst(SrvyInstVo srvyInstVo) throws Exception;
    
    /**
     * 대상기관 엑셀 데이터 정합성 체크
     *
     * @Title : instExcelDataCheck 
     * @Description : 대상기관 엑셀 데이터 정합성 체크
     * @param list ArrayList 엑셀 데이터
     * @return ArrayList 엑셀 데이터 정합성 체크 목록
     * @throws Exception 예외
     */
    public Map<String, Object> instExcelDataCheck(ArrayList list) throws Exception;
    
    /**
     * 컨설팅만족도설문 등록
     *
     * @Title : insertCnsltngDgstfnSrvy 
     * @Description : 컨설팅만족도설문 등록
     * @param srvyVo SrvyVo객체
     * @return int srvyid
     * @throws Exception 예외
     */
    public int insertCnsltngDgstfnSrvy(SrvyVo srvyVo) throws Exception;
    
    /**
     * 컨설팅만족도설문 목록 조회
     *
     * @Title : selectCnsltngDgstfnSrvyList 
     * @Description : 컨설팅만족도설문 목록 조회
     * @param srvyVo SrvyVo객체
     * @return List<SrvyVo> 컨설팅만족도설문 목록
     * @throws Exception 예외
     */
    public List<SrvyVo> selectCnsltngDgstfnSrvyList(SrvyVo srvyVo) throws Exception;
    
    /**
     * 컨설팅만족도설문 컨설팅 목록 조회
     *
     * @Title : selectCnsltngList 
     * @Description : 컨설팅만족도설문 컨설팅 목록 조회
     * @param srvyInstVo SrvyInstVo객체
     * @return List<SrvyInstVo> 컨설팅만족도설문 컨설팅 목록
     * @throws Exception 예외
     */
    public List<SrvyInstVo> selectCnsltngList(SrvyInstVo srvyInstVo) throws Exception;
    
    /**
     * 컨설팅만족도설문 업데이트
     *
     * @Title : updateCnsltngDgstfnSrvy
     * @Description : 기관설문 업데이트
     * @param srvyVo SrvyVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateCnsltngDgstfnSrvy(SrvyVo srvyVo) throws Exception;
    
    /**
     * 설문결과 문항 목록 조회
     *
     * @Title : selectSrvyRsltQitmeList 
     * @Description : 설문결과 문항 목록 조회
     * @param qitemVo QitemVo객체
     * @return List<QitemVo> 설문결과 문항 목록
     * @throws Exception 예외
     */
    public List<QitemVo> selectSrvyRsltQitmeList(QitemVo qitemVo) throws Exception;
    
    /**
     * 단답형, 서술형, 혼합형(기타) 답변 목록 조회
     *
     * @Title : selectAnsList 
     * @Description : 단답형, 서술형, 혼합형(기타) 답변 목록 조회
     * @param srvyAnsVo SrvyAnsVo객체
     * @return List<SrvyAnsVo> 답변 목록
     * @throws Exception 예외
     */
    public List<SrvyAnsVo> selectAnsList(SrvyAnsVo srvyAnsVo) throws Exception;
    
}