package com.kbrainc.plum.front.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.member.model.MemberInstSearchVo;
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
    * 회원 정보 등록
    *
    * @Title : insertMember
    * @Description : 
    * @param memberVo MemberVo객체
    * @return int 
    * @throws Exception 예외
    */
    public int insertMember(MemberVo memberVo) throws Exception;
    
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
    * 기관 검색 리스트 조회
    *
    * @Title       : selectInstSearchList 
    * @Description : 기관 검색 리스트 조회
    * @param memberInstSearchVo MemberInstSearchVo객체
    * @return List<MemberInstSearchVo> 기관검색목록
    * @throws Exception 예외
    */
    public List<MemberInstSearchVo> selectInstSearchList(MemberInstSearchVo memberInstSearchVo) throws Exception;
    
    /**
    *
    * 회원 정보 수정
    *
    * @Title : updateMember
    * @Description : 회원 정보 수정 
    * @param memberVo MemberVo객체
    * @return int 
    * @throws Exception 예외
    */
    public int updateMember(MemberVo memberVo) throws Exception;
}