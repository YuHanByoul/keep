package com.kbrainc.plum.front.srvy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kbrainc.plum.front.srvy.model.SrvySbmsnAnsVo;
import com.kbrainc.plum.front.srvy.model.SrvySbmsnVo;
import com.kbrainc.plum.front.srvy.model.SrvyVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;

/**
 * 
 * 설문조사 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.srvy.service
 * - SrvyService.java
 * </pre> 
 *
 * @ClassName : SrvyService
 * @Description : 설문조사 서비스 인터페이스 
 * @author : KBRAINC
 * @date : 2023. 2. 28.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface SrvyService {
    
    /**
    * 설문 목록 조회
    *
    * @Title : selectSrvyList 
    * @Description : 설문 목록 조회
    * @param srvyVo SrvyVo객체
    * @return List<SrvyVo> 설문 목록
    * @throws Exception 예외
    */
    public List<SrvyVo> selectSrvyList(SrvyVo srvyVo) throws Exception;
    
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
    * 설문 문항 목록 조회
    *
    * @Title : selectQitemList
    * @Description : 문항 정보 조회
    * @param srvyVo QitemVo 객체
    * @return List<QitemVo> 문항 목록
    * @throws Exception 예외
    */
    public List<QitemVo> selectQitemList(SrvyVo srvyVo) throws Exception;
    
    /**
    * 설문 제출
    *
    * @Title : insertSrvySbmsn 
    * @Description : 설문 제출
    * @param srvySbmsnVo SrvySbmsnVo객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    public int insertSrvySbmsn(HttpServletRequest request, SrvySbmsnVo srvySbmsnVo, List<SrvySbmsnAnsVo> srvySbmsnAnsList) throws Exception;
    
    /**
    * 설문 답변 목록 조회
    *
    * @Title : selectAnsList
    * @Description : 설문 답변 목록 조회
    * @param srvyVo QitemVo 객체
    * @return List<QitemVo> 설문 답변 목록
    * @throws Exception 예외
    */
    public List<QitemVo> selectAnsList(SrvyVo srvyVo) throws Exception;
    
}