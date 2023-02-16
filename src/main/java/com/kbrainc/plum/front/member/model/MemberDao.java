package com.kbrainc.plum.front.member.model;

import java.util.List;

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
    * @Title : checkDuplicationUser
    * @Description : ID 중복 체크
    * @param memberVo MemberVo객체
    * @return int 
    * @throws Exception 예외
    */
    public int checkDuplicationUser(MemberVo memberVo) throws Exception;    

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
    * 관심 환경분야 저장
    *
    * @Title       : insertEnvfld 
    * @Description : 사용자수정.
    * @param memberVo MemberVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int insertEnvfld(MemberVo memberVo) throws Exception;
    
    /**
    * 환경분야 저장
    *
    * @Title       : insertItrstyfld 
    * @Description : 사용자수정.
    * @param memberVo MemberVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int insertItrstfld(MemberVo memberVo) throws Exception;
    
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
    
    
}