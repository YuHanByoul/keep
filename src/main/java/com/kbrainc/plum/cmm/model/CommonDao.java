package com.kbrainc.plum.cmm.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.model.DrmncyInfoVo;
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
    * 현재 사용자의 접근가능한 시도목록을 반환한다.
    *
    * @Title : selectAlowedCtprvnList
    * @Description : 현재 사용자의 접근가능한 시도목록을 반환한다.
    * @param userVo UserVo객체
    * @return List<Map<String,Object>> 시도목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAlowedCtprvnList(UserVo userVo) throws Exception;
    
    /**
    * 현재 사용자의 접근가능한 시군구목록을 반환한다.
    *
    * @Title : selectAlowedSignguList
    * @Description : 현재 사용자의 접근가능한 시군구목록을 반환한다.
    * @param userVo UserVo객체
    * @return List<Map<String,Object>> 시군구목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAlowedSignguList(UserVo userVo) throws Exception;
    
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
    * 로그인 실패 횟수를 0으로 수정하고 로그인일시를 수정한다.
    *
    * @Title : updateUserLgnInfo
    * @Description : 로그인 실패 횟수를 0으로 수정하고 로그인일시를 수정한다.
    * @param userid 사용자아이디
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateUserLgnInfo(String userid) throws Exception;
    
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
    * 사용자 휴면 데이터로 부터 사용자 정보를 수정한다.
    *
    * @Title : updateUserFromDrmncy
    * @Description : 사용자 휴면 데이터로 부터 사용자 정보를 수정한다
    * @param drmncyInfoVo DrmncyInfoVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateUserFromDrmncy(DrmncyInfoVo drmncyInfoVo) throws Exception;
    
    /**
    * 사용자 휴면 데이터를 삭제 한다.
    *
    * @Title : deleteUserDrmncy
    * @Description : 사용자 휴면 데이터를 삭제 한다
    * @param userid 사용자아이디
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteUserDrmncy(String userid) throws Exception;
    
    /**
    * 사용자에게 부여된 역할을 삭제 한다.
    *
    * @Title : deleteRoleUser
    * @Description : 사용자에게 부여된 역할을 삭제 한다
    * @param userid 사용자아이디
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteRoleUser(String userid) throws Exception;
    
    /**
    * 관리자 접속 허가 IP목록을 조회한다.
    *
    * @Title : selectCertIpList
    * @Description : 관리자 접속 허가 IP목록을 조회한다.
    * @return List<String> 허가된 IP목록
    * @throws Exception 예외
    */
    public List<String> selectCertIpList() throws Exception;
}
