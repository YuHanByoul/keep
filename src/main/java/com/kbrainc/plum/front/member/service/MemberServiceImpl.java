package com.kbrainc.plum.front.member.service;

import java.security.MessageDigest;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.member.model.MemberDao;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.rte.model.UserVo;
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
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Service("front.memberServiceImpl")
@Alias("front.memberServiceImpl")
public class MemberServiceImpl extends PlumAbstractServiceImpl implements MemberService {

    @Resource(name = "front.memberDao")
    private MemberDao memberDao;
    
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
    @Transactional
    public int withdrawalMember(UserVo user, HttpSession session) throws Exception {
        int retVal = 0;
        retVal += memberDao.updateMemberDel(user);
        retVal += memberDao.deleteEsylgnByUserid(user);
        
        if (session != null) {
            session.invalidate();
        }
        return retVal;
    }
    
    /**
    *
    * ID 중복 체크
    *
    * @Title : chekcDuplicationUser
    * @Description : ID 중복 체크
    * @param memberVo MemberVo객체
    * @return int 
    * @throws Exception 예외
    */
    public int chekcDuplicationUser(MemberVo memberVo) throws Exception{
        return memberDao.chekcDuplicationUser(memberVo);
    };
    
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
   public int insertMember(MemberVo memberVo) throws Exception{
	   
       String password = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(memberVo.getPswd().getBytes("UTF-8")));
       
       memberVo.setPswd(password);
       
	   int resRegisterMember = memberDao.insertMember(memberVo);
	   
	   //TO-DO    
	   //상황에 맞도록 멤버 member detail insert 및 update 작업 할 것
	   //PMD 검사로 인하 주석 처리(기존 하이점프 역할로 되어 있음 ) 작업 이후 삭제 할것
	   
	   //if(resRegisterMember > 0 ) {
		//// T: 선생님 , G:일반, S:학생 
		//if(memberVo.getUserSeCd().equals("T")){
		// teacherVo.setUserid(memberVo.getUserid());
		// memberDao.insertMemberTeacher(teacherVo);
		// memberDao.updateMember(memberVo);
		//}else if(memberVo.getUserSeCd().equals("S")){
		////TO-DO
		//}else {
		///TO-DO
		//}
		   
	  //}
	   
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
   public int updateMember(MemberVo memberVo) throws Exception{
	   
	   String password = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(memberVo.getPswd().getBytes("UTF-8")));
	   
	   memberVo.setPswd(password);
	   
	   int resInt = memberDao.updateMember(memberVo);

		//TO-DO    
		//상황에 맞도록 멤버 member detail update 작업 할 것
		///PMD 검사로 인하 주석 처리(기존 하이점프 역할로 되어 있음 ) 작업 이후 삭제 할것
	   
	   //if(resInt > 0 ) {
		// // T: 선생님 , G:일반, S:학생 
		// if(memberVo.getUserSeCd().equals("T")){
		//     teacherVo.setUserid(memberVo.getUserid());
		//	   memberDao.updateMemberTeacher(teacherVo);
		// }else if(memberVo.getUserSeCd().equals("S")){
		//	  //TO-DO
		// }else {
		//	 //TO-DO
		// }
	   //}
	   
	   return resInt;
   };
      
   
}