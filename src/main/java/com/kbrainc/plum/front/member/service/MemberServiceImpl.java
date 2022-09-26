package com.kbrainc.plum.front.member.service;

import java.security.MessageDigest;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Hex;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.member.model.MemberDao;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.member.model.TeacherVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 회원정보 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.service
* - MemberServiceImpl.java
* </pre> 
*
* @ClassName : MemberServiceImpl
* @Description : 회원정보 서비스 구현 클래스
* @author : KBRAINC
* @date : 2021. 11. 18.
* @Version : 
* @Company : Copyright KBRAINC. All Rights Reserved 
*/
@Service("front.memberServiceImpl")
@Alias("front.memberServiceImpl")
public class MemberServiceImpl extends PlumAbstractServiceImpl implements MemberService {

    @Resource(name = "front.memberDao")
    private MemberDao memberDao;
    
    
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
   @Transactional
   public int insertMember(MemberVo memberVo,TeacherVo teacherVo) throws Exception{
	   
       String password = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(memberVo.getPwd().getBytes("UTF-8")));
       
       memberVo.setPwd(password);
       
	   int resRegisterMember = memberDao.insertMember(memberVo);
	   if(resRegisterMember > 0 ) {
		   // T: 선생님 , G:일반, S:학생 
		   if(memberVo.getUser_se_cd().equals("T")){
			   teacherVo.setUserid(memberVo.getUserid());
			   memberDao.insertMemberTeacher(teacherVo);
			   memberDao.updateMember(memberVo);
		   }else if(memberVo.getUser_se_cd().equals("S")){
			  //TO-DO
		   }else {
			 //TO-DO
		   }
	   }
	   return resRegisterMember;
   };
   
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
   @Transactional
   public int updateMember(MemberVo memberVo,TeacherVo teacherVo) throws Exception{
	   
	   String password = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(memberVo.getPwd().getBytes("UTF-8")));
	   
	   memberVo.setPwd(password);
	   
	   int resInt = memberDao.updateMember(memberVo);
	   
	   if(resInt > 0 ) {
		   // T: 선생님 , G:일반, S:학생 
		   if(memberVo.getUser_se_cd().equals("T")){
			   teacherVo.setUserid(memberVo.getUserid());
			   memberDao.updateMemberTeacher(teacherVo);
		   }else if(memberVo.getUser_se_cd().equals("S")){
			  //TO-DO
		   }else {
			 //TO-DO
		   }
	   }
	   return resInt;
   };
   
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
  public Map<String,Object> selectTeacherMemberInfo(MemberVo MemberVo) throws Exception{
	  return memberDao.selectTeacherMemberInfo(MemberVo);
  };
   
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
   public int chekcDuplicationUser(MemberVo MemberVo) throws Exception{
	   return memberDao.chekcDuplicationUser(MemberVo);
   };
   
}