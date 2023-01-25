package com.kbrainc.plum.front.member.model;

import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

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