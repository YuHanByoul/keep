package com.kbrainc.plum.mng.qestnr.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 * 설문지관리 DAO
 *
 * <pre>
 * com.kbrainc.plum.mng.qestnr.model
 * - QestnrDao.java
 * </pre> 
 *
 * @ClassName : QestnrDao
 * @Description : 설문지관리 DAO 
 * @author : KBRAINC
 * @date : 2022. 11. 29.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface QestnrDao {
    
    /**
     * 설문지 정보 등록
     *
     * @Title : insertQestnr 
     * @Description : 설문지 정보 등록
     * @param qestnrrVo QestnrVo객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertQestnr(QestnrVo qestnrVo) throws Exception;
    
    /**
     * 사이트 목록 조회
     *
     * @Title : selectSiteList
     * @Description : 사이트 목록 조회
     * @param qestnrVo QestnrVo 객체
     * @return List<QestnrVo> 사이트 목록
     * @throws Exception 예외
     */
    public List<QestnrVo> selectSiteList(QestnrVo qestnrVo) throws Exception;
    
    /**
     * 설문지 목록 조회
     *
     * @Title : selectQestnrList
     * @Description : 설문지 목록 조회
     * @param qestnrVo QestnrVo 객체
     * @return List<QestnrVo> 설문지 목록
     * @throws Exception 예외
     */
    public List<QestnrVo> selectQestnrList(QestnrVo qestnrVo) throws Exception;
    
    /**
     * 설문지 정보 조회
     *
     * @Title : selectQestnrInfo
     * @Description : 설문지 정보 조회
     * @param qestnrVo QestnrVo 객체
     * @return QestnrVo QestnrVo 객체
     * @throws Exception 예외
     */
    public QestnrVo selectQestnrInfo(QestnrVo qestnrVo) throws Exception;
    
    /**
     * 설문지 정보 업데이트
     *
     * @Title : updateQestnr
     * @Description : 설문지 정보 업데이트
     * @param qestnrVo QestnrVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateQestnr(QestnrVo qestnrVo) throws Exception;
    
    /**
     * 설문지 문항 조회
     *
     * @Title : selectQitemList
     * @Description : 설문지 목록 조회
     * @param qestnrVo QestnrVo 객체
     * @return List<QitemVo> 설문지 목록
     * @throws Exception 예외
      */
    public List<QitemVo> selectQitemList(QitemVo qitemVo) throws Exception;
     
    /**
     * 설문지 문항 정보 등록
     *
     * @Title : insertQitem 
     * @Description : 설문지 문항 정보 등록
     * @param qitemVo QitemVo객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertQitem(QitemVo qitemVo) throws Exception;
    
    /**
     * 설문지 문항 보기 정보 등록
     *
     * @Title : insertQitemEx 
     * @Description : 설문지 문항 보기 정보 등록
     * @param qitemExVo List<QitemExVo>객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertQitemEx(QitemExVo qitemExVo) throws Exception;

    /**
     * 설문지 문항 정보 조회
     *
     * @Title : selectQitemInfo
     * @Description : 설문지 문항 정보 조회
     * @param qitemVo QitemVo 객체
     * @return QitemVo QitemVo 객체
     * @throws Exception 예외
     */
    public QitemVo selectQitemInfo(QitemVo qitemVo) throws Exception;
    
    /**
     * 설문지 문항 보기 목록 조회
     *
     * @Title : selectQitemExList
     * @Description : 설문지 문항 보기 목록 조회
     * @param qitemVo QitemVo 객체
     * @return List<QitemExVo> 설문지 문항 보기 목록
     * @throws Exception 예외
     */
    public List<QitemExVo> selectQitemExList(QitemVo qitemVo) throws Exception;
    
    /**
     * 설문지 문항 정보 업데이트
     *
     * @Title : updateQitem
     * @Description : 설문지 문항 정보 업데이트
     * @param qitemVo qitemVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateQitem(QitemVo qitemVo) throws Exception;
    
    /**
     * 설문지 다음 문항 정보 조회
     *
     * @Title : selectNextQitemInfo
     * @Description : 설문지 다음 문항 정보 조회
     * @param qitemVo QitemVo 객체
     * @return QitemVo QitemVo 객체
     * @throws Exception 예외
     */
    public QitemVo selectNextQitemInfo(QitemVo qitemVo) throws Exception;
    
    /**
     * 설문지 이전 문항 정보 조회
     *
     * @Title : selectNextQitemInfo
     * @Description : 설문지 이전 문항 정보 조회
     * @param qitemVo QitemVo 객체
     * @return QitemVo QitemVo 객체
     * @throws Exception 예외
     */
    public QitemVo selectPrevQitemInfo(QitemVo qitemVo) throws Exception;
    
    /**
     * 설문지 문항 순서 업데이트
     *
     * @Title : updateQitemOrdr
     * @Description : 설문지 문항 순서 업데이트
     * @param qitemVo QitemVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateQitemOrdr(QitemVo qitemVo) throws Exception;
    
    /**
     * 설문지 문항 삭제
     *
     * @Title : deleteQitem
     * @Description : 설문지 문항 삭제
     * @param qitemVo QitemVo 객체
     * @return int delete 로우수
     * @throws Exception 예외
     */
    public int deleteQitem(QitemVo qitemVo) throws Exception;
    
    /**
     * 설문지 문항 보기 삭제
     *
     * @Title : deleteQitemEx
     * @Description : 설문지 문항 보기 삭제
     * @param qitemVo QitemVo 객체
     * @return int delete 로우수
     * @throws Exception 예외
     */
    public int deleteQitemEx(QitemVo qitemVo) throws Exception;
    
}