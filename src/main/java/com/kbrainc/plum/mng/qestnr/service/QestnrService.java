package com.kbrainc.plum.mng.qestnr.service;

import java.util.List;

import com.kbrainc.plum.mng.qestnr.model.QestnrVo;
import com.kbrainc.plum.mng.qestnr.model.QitemExVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;

/**
 * 
 * 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.qestnr.service
 * - QestnrService.java
 * </pre> 
 *
 * @ClassName : QestnrService
 * @Description : 설문지관리 서비스 인터페이스 
 * @author : KBRAINC
 * @date : 2022. 11. 29.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface QestnrService {
    
    /**
     * 설문지 정보 등록
     *
     * @Title : insertQestnr 
     * @Description : 설문지 정보 등록
     * @param qestnrVo QestnrVo객체
     * @return int qestrnid
     * @throws Exception 예외
     */
    public int insertQestnr(QestnrVo qestnrVo) throws Exception;
    
    /**
     * 사이트 목록 조회
     *
     * @Title : selectSiteList 
     * @Description : 사이트 목록 조회
     * @param qestnrVo QestnrVo객체
     * @return List<QestnrVo> 사이트 목록
     * @throws Exception 예외
     */
    public List<QestnrVo> selectSiteList(QestnrVo qestnrVo) throws Exception;
    
    /**
     * 설문지 목록 조회
     *
     * @Title : selectQestnrList 
     * @Description : 설문지 목록 조회
     * @param qestnrVo QestnrVo객체
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
     * 설문지 문항 목록 조회
     *
     * @Title : selectQitemList
     * @Description : 설문지 목록 조회
     * @param qitemVo QitemVo 객체
     * @return List<QitemVo> 설문지 문항 목록
     * @throws Exception 예외
     */
    public List<QitemVo> selectQitemList(QitemVo qitemVo) throws Exception;
      
    /**
     * 설문지 문항 등록
     *
     * @Title : insertQitem 
     * @Description : 설문지 문항 등록
     * @param qitemVo QitemVo객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertQitem(QitemVo qitemVo) throws Exception;
    
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
     * @param qitemVo QitemVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateQitem(QitemVo qitemVo) throws Exception;
    
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
    
}