package com.kbrainc.plum.front.member.model;

import java.util.List;
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
    * 회원정보 수정(동의여부, 개인정보 유효기간만 update).
    *
    * @Title : updateMemberAgreYnAndPrvcVldty
    * @Description : 회원정보 수정(동의여부, 개인정보 유효기간만 update)
    * @param memberVo MemberVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateMemberAgreYnAndPrvcVldty(MemberVo memberVo) throws Exception;
    
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
    
    /**
    * 사업자등록번호에 해당하는 기관POOL 정보 조회.
    *
    * @Title : selectInstPoolInfoByBrno
    * @Description : 사업자등록번호에 해당하는 기관POOL 정보 조회
    * @param memberInstVo MemberInstVo객체
    * @return MemberInstVo 기관정보(pool)
    * @throws Exception 예외
    */
    public MemberInstVo selectInstPoolInfoByBrno(MemberInstVo memberInstVo) throws Exception;
    
    /**
    * 기관 테이블 존재여부와 상태값, 기관담당자 존재여부 조회.
    *
    * @Title : selectInstInfoByBrno
    * @Description : 기관 테이블 존재여부와 상태값, 기관담당자 존재여부 조회
    * @param memberInstVo MemberInstVo객체
    * @return MemberInstVo 기관정보
    * @throws Exception 예외
    */
    public MemberInstVo selectInstInfoByBrno(MemberInstVo memberInstVo) throws Exception;
    
    /**
    * 기관정보 insert.
    *
    * @Title : insertInst
    * @Description : 기관정보 insert
    * @param memberInstVo MemberInstVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertInst(MemberInstVo memberInstVo) throws Exception;
    
    /**
    * 기관정보 update.
    *
    * @Title : updateInst
    * @Description : 기관정보 update
    * @param memberInstVo MemberInstVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateInst(MemberInstVo memberInstVo) throws Exception;
    
    /**
    * 회원을 기관회원 마스터로 update.
    *
    * @Title : updateUserInstMaster
    * @Description : 회원을 기관회원 마스터로 update
    * @param memberVo MemberVo객체
    * @return int udpate로우수
    * @throws Exception 예외
    */
    public int updateUserInstMaster(MemberVo memberVo) throws Exception;
    
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
    * 기관코드 수정.
    *
    * @Title : updateInstCd
    * @Description : 기관코드 수정
    * @param instid 기관아이디
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateInstCd(Integer instid) throws Exception;
    
    /**
    * ci 또는 휴대폰번호에 해당하는 회원과 어린이회원의 계정정보를 조회한다.
    *
    * @Title : selectAcntFromCiAndMoblphonList
    * @Description : ci 또는 휴대폰번호에 해당하는 회원과 어린이회원의 계정정보를 조회한다
    * @param memberAcntPswdFindVo MemberAcntPswdFindVo객체
    * @return List<MemberAcntPswdFindVo> 계정 목록
    * @throws Exception 예외
    */
    public List<MemberAcntPswdFindVo> selectAcntFromCiAndMoblphonList(MemberAcntPswdFindVo memberAcntPswdFindVo) throws Exception;
    
    /**
    * ci 또는 휴대폰번호가 일치하는 acnt에 해당하는 회원 정보를 조회한다.
    *
    * @Title : selectAcntFromCiAndMoblphon
    * @Description : ci 또는 휴대폰번호가 일치하는 acnt에 해당하는 회원 정보를 조회한다.
    * @param memberAcntPswdFindVo MemberAcntPswdFindVo객체
    * @return MemberAcntPswdFindVo 계정 정보
    * @throws Exception 예외
    */
    public MemberAcntPswdFindVo selectAcntFromCiAndMoblphon(MemberAcntPswdFindVo memberAcntPswdFindVo) throws Exception;
    
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