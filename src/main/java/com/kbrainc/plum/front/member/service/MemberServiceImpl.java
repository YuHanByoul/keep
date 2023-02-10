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
    * @Title : checkDuplicationUser
    * @Description : ID 중복 체크
    * @param memberVo MemberVo객체
    * @return int 
    * @throws Exception 예외
    */
    public int checkDuplicationUser(MemberVo memberVo) throws Exception{
        return memberDao.checkDuplicationUser(memberVo);
    }
    
    
    @Transactional
    public int insertMember(MemberVo memberVo) throws Exception{
        int retVal = 0;
        
        String password = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(memberVo.getPswd().getBytes("UTF-8")));
        memberVo.setPswd(password);
       
        // 회원정보 입력
	    retVal += memberDao.insertMember(memberVo);
	    
	    // 맞춤환경정보 입력
	    if (memberVo.getEnvfldCds() != null & memberVo.getEnvfldCds().length > 0) {
	        retVal += memberDao.insertEnvfld(memberVo);
        }
	    
	    // 관심분야 입력
        if (memberVo.getItrstfldCds() != null & memberVo.getItrstfldCds().length > 0) {
            retVal += memberDao.insertItrstfld(memberVo);
        }
	    
	    // 기관정보 입력
	    
	    
	    // 디지털원패스 userKey 입력
	    	    
	   
	    return retVal;
    }
   
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
 	    return resInt;
    }
      
    /**
    * ci에 해당하는 userid 조회.
    *
    * @Title : selectUseridByCI
    * @Description : ci에 해당하는 userid 조회
    * @param memberVo MemberVo객체
    * @return String userid
    * @throws Exception 예외
    */
    public String selectUseridByCI(MemberVo memberVo) throws Exception {
        return memberDao.selectUseridByCI(memberVo);
    }
    
    /**
    * 부모ci와 이름에 해당하는 userid 조회.
    *
    * @Title : selectUseridByParntsCIandName
    * @Description : 부모ci와 이름에 해당하는 userid 조회
    * @param memberVo MemberVo객체
    * @return String userid
    * @throws Exception 예외
    */
    public String selectUseridByParntsCIandName(MemberVo memberVo) throws Exception {
        return memberDao.selectUseridByParntsCIandName(memberVo);
    }
}