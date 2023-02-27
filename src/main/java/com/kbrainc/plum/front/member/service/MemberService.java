package com.kbrainc.plum.front.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.member.model.MemberAcntPswdFindVo;
import com.kbrainc.plum.front.member.model.MemberInstSearchVo;
import com.kbrainc.plum.front.member.model.MemberInstVo;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.rte.model.UserVo;

/**
* 회원정보 서비스 인터페이스.
*
* <pre>
* com.kbrainc.plum.front.member.service
* - MemberService.java
* </pre> 
*
* @ClassName : MemberService
* @Description : 회원정보 서비스 인터페이스
* @author : KBRAINC
* @date : 2021. 4. 12.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
public interface MemberService {
	
    /**
    * 회원 탈퇴 처리.
    *
    * @Title : withdrawalMember 
    * @Description : 회원 탈퇴 처리
    * @param user 사용자세션정보
    * @param session 세션객체
    * @return int DB변경로우수
    * @throws Exception 예외
    */
    public int withdrawalMember(UserVo user, HttpSession session) throws Exception;
    
    /**
    *
    * ID 중복 체크
    *
    * @Title : checkDuplicationUser
    * @Description : ID 중복 체크
    * @param memberVo MemberVo객체
    * @return int 
    * @throws Exception 예외
    */
    public int checkDuplicationUser(MemberVo memberVo) throws Exception;
    
    /**
    *
    * 사업자등록번호 중복 체크
    *
    * @Title : checkDuplicationBrno
    * @Description : 사업자등록번호 중복 체크
    * @param memberInstVo MemberInstVo객체
    * @return MemberInstVo result와 msg리턴 
    * @throws Exception 예외
    */
    public MemberInstVo checkDuplicationBrno(MemberInstVo memberInstVo) throws Exception;
    
    /**
    *
    * 회원 정보 등록
    *
    * @Title : insertMember
    * @Description : 
    * @param memberVo MemberVo객체
    * @param memberInstVo MemmberInstVo객체
    * @return int 
    * @throws Exception 예외
    */
    public int insertMember(MemberVo memberVo, MemberInstVo memberInstVo) throws Exception;
    
    /**
    * ci에 해당하는 userid 조회.
    *
    * @Title : selectUseridByCI
    * @Description : ci에 해당하는 userid 조회
    * @param memberVo MemberVo객체
    * @return String userid
    * @throws Exception 예외
    */
    public String selectUseridByCI(MemberVo memberVo) throws Exception;
    
    /**
    * ci에 해당하는 사용자정보 조회.
    *
    * @Title : selectUserInfoByCI
    * @Description : ci에 해당하는 사용자정보 조회
    * @param memberVo MemberVo객체
    * @return MemberVo 사용자정보
    * @throws Exception 예외
    */
    public MemberVo selectUserInfoByCI(MemberVo memberVo) throws Exception;
    
    /**
    * 부모ci와 이름에 해당하는 userid 조회.
    *
    * @Title : selectUseridByParntsCIandName
    * @Description : 부모ci와 이름에 해당하는 userid 조회
    * @param memberVo MemberVo객체
    * @return String userid
    * @throws Exception 예외
    */
    public String selectUseridByParntsCIandName(MemberVo memberVo) throws Exception;
    
    /**
    * 기관풀 검색 리스트 조회
    *
    * @Title       : selectInstSearchList 
    * @Description : 기관풀 검색 리스트 조회
    * @param memberInstSearchVo MemberInstSearchVo객체
    * @return List<MemberInstSearchVo> 기관검색목록
    * @throws Exception 예외
    */
    public List<MemberInstSearchVo> selectInstSearchList(MemberInstSearchVo memberInstSearchVo) throws Exception;
    
    /**
    * 기관 정보 조회(기관풀)
    *
    * @Title       : selectInstPoolInfo 
    * @Description : 기관 정보 조회(기관풀)
    * @param memberInstSearchVo MemberInstSearchVo객체
    * @return MemberInstSearchVo 기관정보
    * @throws Exception 예외
    */
    public MemberInstSearchVo selectInstPoolInfo(MemberInstSearchVo memberInstSearchVo) throws Exception;
    
    /**
    * 주소로 시군구 코드를 가져온다(카카오 주소API).
    *
    * @Title : getSignguCdWithaddress
    * @Description : 주소로 시군구 코드를 가져온다(카카오 주소API)
    * @param addr 주소
    * @return String 시군구코드
    */
    public String getSignguCdWithaddress(String addr);

    /**
    * 기관 유형 코드 호출 
    *
    * @Title       : selectInstTypeCdList 
    * @Description : 기관 유형 코드 호출 
    * @param Map<String,String> 객체
    * @return List<Map<String,String>> 기관 유형 코드 목록
    * @throws Exception 예외
    */
    public List<Map<String,String>> selectInstTypeCdList() throws Exception;
    
    /**
    * ci에 해당하는 회원과 어린이회원의 계정정보를 조회한다.
    *
    * @Title : selectAcntFromCiList
    * @Description : ci에 해당하는 회원과 어린이회원의 계정정보를 조회한다.
    * @param memberAcntPswdFindVo MemberAcntPswdFindVo객체
    * @return List<MemberAcntPswdFindVo> 계정 목록
    * @throws Exception 예외
    */
    public List<MemberAcntPswdFindVo> selectAcntFromCiList(MemberAcntPswdFindVo memberAcntPswdFindVo) throws Exception;
    
    /**
    * ci와 acnt에 해당하는 회원 정보를 조회한다.
    *
    * @Title : selectAcntFromCi
    * @Description : ci와 acnt에 해당하는 회원 정보를 조회한다.
    * @param memberAcntPswdFindVo MemberAcntPswdFindVo객체
    * @return MemberAcntPswdFindVo 계정 정보
    * @throws Exception 예외
    */
    public MemberAcntPswdFindVo selectAcntFromCi(MemberAcntPswdFindVo memberAcntPswdFindVo) throws Exception;
    
    /**
    * 비밀번호 수정(비밀번호 찾기).
    *
    * @Title : updatePassword
    * @Description : 비밀번호 수정(비밀번호 찾기).
    * @param memberAcntPswdFindVo MemberAcntPswdFindVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updatePassword(MemberAcntPswdFindVo memberAcntPswdFindVo) throws Exception;
    
    /**
     * 회원 상세 정보 호출 
     *
     * @Title : selectMemberInfo
     * @Description : 회원 상세 정보 호출 
     * @param memberVo MemberVo객체
     * @return MemberVo 사용자정보
     * @throws Exception 예외
     */
     public MemberVo selectMemberInfo(MemberVo memberVo) throws Exception;
}