package com.kbrainc.plum.front.member.model;

import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.rte.model.UserVo;

/**
* 회원정보 DAO 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.model
* - MemberDao.java
* </pre> 
*
* @ClassName : MemberDao
* @Description : 회원정보 DAO 클래스
* @author : KBRAINC
* @date : 2021. 4. 12.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Mapper("front.memberDao")
public interface MemberDao {

    /**
    * 회원 탈퇴.
    *
    * @Title : updateMemberDel
    * @Description : 회원 탈퇴
    * @param user 사용자세션정보
    * @return int udpate로우수
    * @throws Exception 예외
    */
    public int updateMemberDel(UserVo user) throws Exception;
    
    /**
    * 특정 userid의 간편로그인 정보 전체 삭제.
    *
    * @Title : deleteEsylgnByUserid
    * @Description : 특정 userid의 간편로그인 정보 전체 삭제
    * @param user 사용자세션정보
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteEsylgnByUserid(UserVo user) throws Exception;
    
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

    public int chekcDuplicationUser(MemberVo memberVo) throws Exception;    
    
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