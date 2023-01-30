package com.kbrainc.plum.cmm.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kbrainc.plum.front.member.model.EsylgnVo;
import com.kbrainc.plum.mng.site.model.SiteVo;

/**
 * 
 * 어플리케이션 전체의 공통 요청을 처리하는 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - CommonService.java
 * </pre> 
 *
 * @ClassName : CommonService
 * @Description : 어플리케이션 전체의 공통 요청을 처리하는 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface CommonService {

    /**
    * 사이트 리스트를 반환한다.
    *
    * @Title       : selectSiteList 
    * @Description : 사이트 리스트를 반환한다.
    * @param site SiteVo객체
    * @return List<SiteVo> 사이트정보 목록
    * @throws Exception 예외
    */
    public List<SiteVo> selectSiteList(SiteVo site) throws Exception;
    
    /**
    * 현재 사용자의 접근가능한 기관목록을 반환한다.
    *
    * @Title : selectAlowedInstList
    * @Description : 현재 사용자의 접근가능한 기관목록을 반환한다.
    * @return List<Map<String,Object>> 기관목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAlowedInstList() throws Exception;
    
    /**
    * 현재 사용자의 접근가능한 사이트목록을 반환한다.
    *
    * @Title : selectAlowedSiteList
    * @Description : 현재 사용자의 접근가능한 사이트목록을 반환한다.
    * @param sysSeCd 시스템구분코드
    * @return List<Map<String,Object>> 사이트목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAlowedSiteList(String sysSeCd) throws Exception;
    
    /**
    * 로그인 성공 후처리.
    *
    * @Title : insertLoginSuccess
    * @Description : 로그인 성공 후처리를 한다.
    * @param request 요청객체
    * @param userid 사용자아이디
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertLoginSuccess(HttpServletRequest request, String userid) throws Exception;
    
    /**
    * 로그인 실패 후처리.
    *
    * @Title : insertLoginFailDescription
    * @Description : 로그인 실패 후처리를 한다.
    * @param request 요청객체
    * @param userid 사용자아이디
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertLoginFail(HttpServletRequest request, String userid) throws Exception;
    
    /**
    * 전체 지역목록을 반환한다.
    *
    * @Title : selectAllRgnList
    * @Description : 전체 지역목록을 반환한다.
    * @return List<Map<String,Object>> 지역목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAllRgnList() throws Exception;
    /**
     * 시도 지역목록을 반환한다.
     *
     * @Title : selectCtprvnList
     * @Description : 시도 지역목록을 반환한다.
     * @return List<Map<String,Object>> 지역목록
     * @throws Exception 예외
     */
    public List<Map<String, Object>> selectCtprvnList() throws Exception;
    
    /**
    * 간편로그인 userkey로 조회하여 userid를 가져온다.
    *
    * @Title : selectUseridByEsylgnUserkey
    * @Description : 간편로그인 userkey로 조회하여 userid를 가져온다.
    * @param esylgnVo EsylgnVo객체
    * @return String userid값
    * @throws Exception 예외
    */
    public String selectUseridByEsylgnUserkey(EsylgnVo esylgnVo) throws Exception;
    
    /**
    * ci로 조회하여 userid를 가져온다.
    *
    * @Title : selectUseridByCi
    * @Description : ci로 조회하여 userid를 가져온다.
    * @param esylgnVo EsylgnVo객체
    * @return String userid값
    * @throws Exception 예외
    */
    public String selectUseridByCi(EsylgnVo esylgnVo) throws Exception;
    
    /**
    * ci에 해당하는 사용자의 userkey를 업데이트한다.
    *
    * @Title : updateEsylgnUserkey
    * @Description : ci에 해당하는 사용자의 userkey를 업데이트한다.
    * @param esylgnVo EsylgnVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateEsylgnUserkey(EsylgnVo esylgnVo) throws Exception;
}
