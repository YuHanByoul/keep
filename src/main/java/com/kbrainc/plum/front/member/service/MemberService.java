package com.kbrainc.plum.front.member.service;

import java.util.Map;

import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.member.model.TeacherVo;

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
* @Company : Copyright KBRAINC. All Rights Reserved 
*/
public interface MemberService {
	
    /**
    *
    * 회원 정보 등록
    *
    * @Title : insertMember
    * @Description : 
    * @param MemberVo MemberVo객체
    * @return int 
    * @throws Exception 예외
    */
   public int insertMember(MemberVo MemberVo,TeacherVo TeacherVo) throws Exception;
   
   /**
    *
    * 회원 정보 수정
    *
    * @Title : updateMember
    * @Description : 회원 정보 수정 
    * @param MemberVo MemberVo객체
    * @return int 
    * @throws Exception 예외
    */
   public int updateMember(MemberVo MemberVo,TeacherVo TeacherVo) throws Exception;
   
   /**
   *
   * 회원정보 호출
   *
   * @Title : selectTeacherMemberInfo
   * @Description : 회원정보 호출
   * @param MemberVo MemberVo객체
   * @return MemberVo 
   * @throws Exception 예외
   */
  public Map<String,Object> selectTeacherMemberInfo(MemberVo MemberVo) throws Exception;
   
   /**
    *
    * ID 중복 체크
    *
    * @Title : chekcDuplicationUser
    * @Description : ID 중복 체크
    * @param MemberVo MemberVo객체
    * @return int 
    * @throws Exception 예외
    */
   public int chekcDuplicationUser(MemberVo MemberVo) throws Exception;
	
	
}