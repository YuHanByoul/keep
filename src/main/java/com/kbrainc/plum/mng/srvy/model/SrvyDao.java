package com.kbrainc.plum.mng.srvy.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.qestnr.model.QestnrVo;

/**
 * 
 * 설문관리 DAO
 *
 * <pre>
 * com.kbrainc.plum.mng.srvy.model
 * - SrvyDao.java
 * </pre> 
 *
 * @ClassName : SrvyDao
 * @Description : 설문관리 DAO 
 * @author : KBRAINC
 * @date : 2022. 12. 21.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface SrvyDao {
    
    /**
     * 대상자설문 등록
     *
     * @Title : insertTrprSrvy 
     * @Description : 대상자설문 등록
     * @param srvyVo SrvyVo객체
     * @return int srvyid
     * @throws Exception 예외
     */
    public int insertTrprSrvy(SrvyVo srvyVo) throws Exception;
    
    /**
     * 설문지 목록 조회
     *
     * @Title : selectQestnrList
     * @Description : 설문지 목록 조회
     * @param srvyVo SrvyVo 객체
     * @return List<QestnrVo> 설문지 목록
     * @throws Exception 예외
     */
    public List<QestnrVo> selectQestnrList(SrvyVo srvyVo) throws Exception;
    
    /**
     * 개인회원 전체 대상 설문 대상자 등록
     *
     * @Title : insertIndvdlMbrSrvyUser 
     * @Description : 개인회원 전체 대상 설문 대상자 등록
     * @param srvyUserVo SrvyUserVo객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertIndvdlMbrSrvyUser(SrvyUserVo srvyUserVo) throws Exception;
    
    /**
     * 대상자설문 목록 조회
     *
     * @Title : selectTrprSrvyList
     * @Description : 대상자설문 목록 조회
     * @param srvyVo SrvyVo 객체
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
     * @return List<SrvyUserVo> 설문지 목록
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
     * 회원 존재 여부 확인
     *
     * @Title : isExistUser 
     * @Description : 회원 존재 여부 확인
     * @param srvyUserVo SrvyUserVo객체
     * @return srvyUserVo SrvyUserVo 객체
     * @throws Exception 예외
     */
    public SrvyUserVo isExistUser(SrvyUserVo srvyUserVo) throws Exception;
    
    /**
     * 설문지 대상자 삭제
     *
     * @Title : deleteTrpr
     * @Description : 설문지 대상자 삭제
     * @param srvyUserVo SrvyUserVo 객체
     * @return int delete 로우수
     * @throws Exception 예외
     */
    public int deleteTrpr(SrvyUserVo srvyUserVo) throws Exception;

    /**
     * 대상자셜문 업데이트
     *
     * @Title : updateTrprSrvy
     * @Description : 대상자셜문 업데이트
     * @param srvyVo SrvyVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateTrprSrvy(SrvyVo srvyVo) throws Exception;
    
}