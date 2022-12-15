package com.kbrainc.plum.cmm.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.model.RoleInfoVo;
import com.kbrainc.plum.rte.model.UserVo;

/**
 * 
 * 어플리케이션 전체의 공통 요청을 처리하기 위한 DAO 클래스
 *
 * <pre>
 * com.kbrainc.plum.cmm.model
 * - CommonDao.java
 * </pre> 
 *
 * @ClassName : CommonDao
 * @Description : 어플리케이션 전체의 공통 요청을 처리하기 위한 DAO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface CommonDao {
    
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
    * @param userVo UserVo객체
    * @return List<Map<String,Object>> 기관목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAlowedInstList(UserVo userVo) throws Exception;
    
    /**
    * 현재 사용자의 접근가능한 사이트목록을 반환한다.
    *
    * @Title : selectAlowedSiteList
    * @Description : 현재 사용자의 접근가능한 사이트목록을 반환한다.
    * @param param Map타입의 인자
    * @return List<Map<String,Object>> 사이트목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAlowedSiteList(Map<String, Object> param) throws Exception;
    
    /**
    * 로그인 성공 이력을 저장한다.
    *
    * @Title : insertLoginDescription
    * @Description : 로그인 성공 이력을 저장한다.
    * @param Map타입의 인자
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertLoginDescription(Map<String, String> param) throws Exception;
    
    /**
    * 로그인 실패 횟수를 0으로 수정한다.
    *
    * @Title : updateLgnFailCntZero
    * @Description : 로그인 실패 횟수를 0으로 수정한다.
    * @param userid 사용자아이디
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateLgnFailCntZero(String userid) throws Exception;
    
    /**
    * 로그인 실패 이력을 저장한다.
    *
    * @Title : insertLoginFailDescription
    * @Description : 로그인 실패 이력을 저장한다.
    * @param Map타입의 인자
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertLoginFailDescription(Map<String, String> param) throws Exception;
    
    /**
    * 로그인 실패 횟수를 1회 증가시킨다.
    *
    * @Title : updateLgnFailCntPlusOne
    * @Description : 로그인 실패 횟수를 1회 증가시킨다.
    * @param userid 사용자아이디
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateLgnFailCntPlusOne(String userid) throws Exception;
    
    /**
    * 계정을 잠근다(비밀번호 5회 초과 사유).
    *
    * @Title : updateAccountLock
    * @Description : 계정을 잠근다(비밀번호 5회 초과 사유)
    * @param userid 사용자아이디
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateAccountLock(String userid) throws Exception;
    
}
