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
    * 설문 참여 목록 조회
    *
    * @Title : selectSrvyHstryList
    * @Description : 설문 참여 목록 조회
    * @param srvyVo SrvyVo 객체
    * @return List<SrvyVo> 설문 참여 목록
    * @throws Exception 예외
    */
    public List<SrvyVo> selectSrvyHstryList(SrvyVo srvyVo) throws Exception;
    
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
    
    /**
    * 설문 답변 목록 조회
    *
    * @Title : selectAnsList
    * @Description : 설문 답변 목록 조회
    * @param qitemVo QitemVo 객체
    * @return List<QitemVo> 설문 답변 목록
    * @throws Exception 예외
    */
    public List<QitemVo> selectAnsList(SrvyVo srvyVo) throws Exception;
    
}