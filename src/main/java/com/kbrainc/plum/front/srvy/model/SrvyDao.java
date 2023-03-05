package com.kbrainc.plum.front.srvy.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.qestnr.model.QitemExVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;

/**
 * 
 * 설문 DAO
 *
 * <pre>
 * com.kbrainc.plum.front.srvy.model
 * - SrvyDao.java
 * </pre> 
 *
 * @ClassName : SrvyDao
 * @Description : 설문 DAO 
 * @author : KBRAINC
 * @date : 2023. 2. 28.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.SrvyDao")
public interface SrvyDao {
    
    /**
    * 설문 목록 조회
    *
    * @Title : selectSrvyList
    * @Description : 설문 목록 조회
    * @param srvyVo SrvyVo 객체
    * @return List<SrvyVo> 설문 목록
    * @throws Exception 예외
    */
    public List<SrvyVo> selectSrvyList(SrvyVo srvyVo) throws Exception;
    
    /**
    * 설문 문항 목록 조회
    *
    * @Title : selectQitemList
    * @Description : 설문 문항 목록 조회
    * @param qitemVo QitemVo 객체
    * @return List<QitemVo> 설문 문항 목록
    * @throws Exception 예외
    */
    public List<QitemVo> selectQitemList(SrvyVo srvyVo) throws Exception;
    
    /**
    * 설문 문항 보기 목록 조회
    *
    * @Title : selectExList
    * @Description : 설문 문항 보기 목록 조회
    * @param qitemVo QitemVo 객체
    * @return List<QitemExVo> 설문 문항 보기 목록
    * @throws Exception 예외
    */
    public List<QitemExVo> selectExList(QitemVo qitemVo) throws Exception;
    
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
    * 설문 제출
    *
    * @Title : insertSrvySbmsn 
    * @Description : 설문 제출
    * @param srvySbmsnVo SrvySbmsnVo객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    public int insertSrvySbmsn(SrvySbmsnVo srvySbmsnVo) throws Exception;
    
    /**
    * 설문 답변 등록
    *
    * @Title : insertSrvySbmsnAns 
    * @Description : 설문 답변 등록
    * @param srvySbmsnAnsVo SrvySbmsnAnsVo객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    public int insertSrvySbmsnAns(SrvySbmsnAnsVo srvySbmsnAnsVo) throws Exception;
    
    
//    
//    /**
//     * 개인회원 전체 대상 설문 대상자 등록
//     *
//     * @Title : insertIndvdlMbrSrvyUser 
//     * @Description : 개인회원 전체 대상 설문 대상자 등록
//     * @param srvyUserVo SrvyUserVo객체
//     * @return int insert 로우수
//     * @throws Exception 예외
//     */
//    public int insertIndvdlMbrSrvyUser(SrvyUserVo srvyUserVo) throws Exception;
//    
//    /**
//     * 대상자설문 목록 조회
//     *
//     * @Title : selectTrprSrvyList
//     * @Description : 대상자설문 목록 조회
//     * @param srvyVo SrvyVo 객체
//     * @return List<SrvyVo> 대상자설문 목록
//     * @throws Exception 예외
//     */
//    public List<SrvyVo> selectTrprSrvyList(SrvyVo srvyVo) throws Exception;
//    

//    
//    /**
//     * 설문 대상자 목록 조회
//     *
//     * @Title : selectTrprList
//     * @Description : 설문 대상자 목록 조회
//     * @param srvyUserVo SrvyUserVo 객체
//     * @return List<SrvyUserVo> 설문지 목록
//     * @throws Exception 예외
//     */
//    public List<SrvyUserVo> selectTrprList(SrvyUserVo srvyUserVo) throws Exception;
//    
//    /**
//     * 회원 목록 조회
//     *
//     * @Title : selectUserList
//     * @Description : 회원 목록 조회
//     * @param srvyUserVo SrvyUserVo 객체
//     * @return List<SrvyUserVo> 회원 목록
//     * @throws Exception 예외
//     */
//    public List<SrvyUserVo> selectUserList(SrvyUserVo srvyUserVo) throws Exception;
//    
//    /**
//     * 회원 존재 여부 확인
//     *
//     * @Title : isExistUser 
//     * @Description : 회원 존재 여부 확인
//     * @param srvyUserVo SrvyUserVo객체
//     * @return srvyUserVo SrvyUserVo 객체
//     * @throws Exception 예외
//     */
//    public SrvyUserVo isExistUser(SrvyUserVo srvyUserVo) throws Exception;
//    
//    /**
//     * 설문 대상자 등록
//     *
//     * @Title : insertTrpr 
//     * @Description : 설문 대상자 등록
//     * @param srvyUserVo SrvyUserVo객체
//     * @return int insert 로우수
//     * @throws Exception 예외
//     */
//    public int insertTrpr(SrvyUserVo srvyUserVo) throws Exception;
//    
//    /**
//     * 설문 대상자 삭제
//     *
//     * @Title : deleteTrpr
//     * @Description : 설문 대상자 삭제
//     * @param srvyUserVo SrvyUserVo 객체
//     * @return int delete 로우수
//     * @throws Exception 예외
//     */
//    public int deleteTrpr(SrvyUserVo srvyUserVo) throws Exception;
//
//    /**
//     * 대상자셜문 업데이트
//     *
//     * @Title : updateTrprSrvy
//     * @Description : 대상자셜문 업데이트
//     * @param srvyVo SrvyVo 객체
//     * @return int update 로우수
//     * @throws Exception 예외
//     */
//    public int updateTrprSrvy(SrvyVo srvyVo) throws Exception;
//    
//    /**
//     * 기관설문 등록
//     *
//     * @Title : insertInstSrvy 
//     * @Description : 기관설문 등록
//     * @param srvyVo SrvyVo객체
//     * @return int srvyid
//     * @throws Exception 예외
//     */
//    public int insertInstSrvy(SrvyVo srvyVo) throws Exception;
//    
//    /**
//     * 기관설문 목록 조회
//     *
//     * @Title : selectInstSrvyList
//     * @Description : 기관설문 목록 조회
//     * @param srvyVo SrvyVo 객체
//     * @return List<SrvyVo> 대상자설문 목록
//     * @throws Exception 예외
//     */
//    public List<SrvyVo> selectInstSrvyList(SrvyVo srvyVo) throws Exception;
//    
//    /**
//     * 기관설문 업데이트
//     *
//     * @Title : updateInstSrvy
//     * @Description : 기관설문 업데이트
//     * @param srvyVo SrvyVo 객체
//     * @return int update 로우수
//     * @throws Exception 예외
//     */
//    public int updateInstSrvy(SrvyVo srvyVo) throws Exception;
//    
//    /**
//     * 기관 전체 대상 설문 대상기관 등록
//     *
//     * @Title : insertInstTrgtSrvyInst 
//     * @Description : 기관 전체 대상 설문 대상기관 등록
//     * @param srvyInstVo SrvyInstVo객체
//     * @return int insert 로우수
//     * @throws Exception 예외
//     */
//    public int insertInstTrgtSrvyInst(SrvyInstVo srvyInstVo) throws Exception;
//    
//    /**
//     * 설문 대상기관 등록
//     *
//     * @Title : insertSrvyInst 
//     * @Description : 설문 대상기관 등록
//     * @param srvyInstVo SrvyInstVo객체
//     * @return int insert 로우수
//     * @throws Exception 예외
//     */
//    public int insertSrvyInst(SrvyInstVo srvyInstVo) throws Exception;
//    
//    /**
//     * 기관 목록 조회
//     *
//     * @Title : selectInstList
//     * @Description : 기관 목록 조회
//     * @param srvyInstVo SrvyInstVo 객체
//     * @return List<SrvyInstVo> 기관 목록
//     * @throws Exception 예외
//     */
//    public List<SrvyInstVo> selectInstList(SrvyInstVo srvyInstVo) throws Exception;
//    
//    /**
//     * 설문 대상기관 목록 조회
//     *
//     * @Title : selectSrvyInstList
//     * @Description : 설문 대상기관 목록 조회
//     * @param srvyInstVo SrvyInstVo 객체
//     * @return List<SrvyInstVo> 설문 대상기관 목록
//     * @throws Exception 예외
//     */
//    public List<SrvyInstVo> selectSrvyInstList(SrvyInstVo srvyInstVo) throws Exception;
//    
//    /**
//     * 설문 대상기관 삭제
//     *
//     * @Title : deleteSrvyInst
//     * @Description : 설문 대상기관 삭제
//     * @param srvyInstVo SrvyInstVo 객체
//     * @return int delete 로우수
//     * @throws Exception 예외
//     */
//    public int deleteSrvyInst( SrvyInstVo srvyInstVo) throws Exception;
//    
//    /**
//     * 기관 존재 여부 확인
//     *
//     * @Title : isExistInst 
//     * @Description : 기관 존재 여부 확인
//     * @param srvyInstVo SrvyInstVo객체
//     * @return srvyInstVo SrvyInstVo 객체
//     * @throws Exception 예외
//     */
//    public SrvyInstVo isExistInst(SrvyInstVo srvyInstVo) throws Exception;
//    
//    /**
//     * 컨설팅만족도설문 등록
//     *
//     * @Title : insertCnsltngDgstfnSrvy 
//     * @Description : 컨설팅만족도설문 등록
//     * @param srvyVo SrvyVo객체
//     * @return int srvyid
//     * @throws Exception 예외
//     */
//    public int insertCnsltngDgstfnSrvy(SrvyVo srvyVo) throws Exception;
//    
//    /**
//     * 컨설팅만족도설문 미사용 업데이트
//     *
//     * @Title : updateCnstlngDgsfnSrvyUseYn 
//     * @Description : 컨설팅만족도설문 미사용 업데이트
//     * @param srvyVo SrvyVo객체
//     * @return int update 수
//     * @throws Exception 예외
//     */
//    public void updateCnstlngDgsfnSrvyUseYn() throws Exception;
//    
//    /**
//     * 컨설팅만족도설문 목록 조회
//     *
//     * @Title : selectCnsltngDgstfnSrvyList
//     * @Description : 컨설팅만족도설문 목록 조회
//     * @param srvyVo SrvyVo 객체
//     * @return List<SrvyVo> 대상자설문 목록
//     * @throws Exception 예외
//     */
//    public List<SrvyVo> selectCnsltngDgstfnSrvyList(SrvyVo srvyVo) throws Exception;
//    
//    /**
//     * 컨설팅만족도설문 컨설팅 목록 조회
//     *
//     * @Title : selectCnsltngList
//     * @Description : 컨설팅만족도설문 컨설팅 목록 조회
//     * @param srvyInstVo SrvyInstVo 객체
//     * @return List<SrvyInstVo> 컨설팅만족도설문 목록
//     * @throws Exception 예외
//     */
//    public List<SrvyInstVo> selectCnsltngList(SrvyInstVo srvyInstVo) throws Exception;
//    
//    /**
//     * 컨설팅만족도설문 업데이트
//     *
//     * @Title : updateCnsltngDgstfnSrvy
//     * @Description : 컨설팅만족도설문 업데이트
//     * @param srvyVo SrvyVo 객체
//     * @return int update 로우수
//     * @throws Exception 예외
//     */
//    public int updateCnsltngDgstfnSrvy(SrvyVo srvyVo) throws Exception;
//    
//    /**
//     * 보기형 문항 응답자 수 조회
//     *
//     * @Title : selectQitemExAnsCnt
//     * @Description : 보기형 문항 응답자 수 조회
//     * @param qitemExVo QitemExVo 객체
//     * @return qitemExVo itemExVo 객체
//     * @throws Exception 예외
//     */
//    public QitemExVo selectQitemExAnsInfo(QitemExVo qitemExVo) throws Exception;
//    
//    /**
//     * 단답형, 서술형, 혼합형(기타) 답변 목록 조회
//     *
//     * @Title : selectAnsList 
//     * @Description : 단답형, 서술형, 혼합형(기타) 답변 목록 조회
//     * @param srvyAnsVo SrvyAnsVo객체
//     * @return List<SrvyAnsVo> 답변 목록
//     * @throws Exception 예외
//     */
//    public List<SrvyAnsVo> selectAnsList(SrvyAnsVo srvyAnsVo) throws Exception;
    
}