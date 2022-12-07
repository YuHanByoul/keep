package com.kbrainc.plum.mng.member.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.rte.util.mail.model.MailVo;

/**
 * 
 * 사용자관리 DAO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - MemberDao.java
 * </pre> 
 *
 * @ClassName : MemberDao
 * @Description : 사용자관리 DAO 클래스. 
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface MemberDao {

    /**
    * 사용자정보 저장.
    *
    * @Title       : insertMember 
    * @Description : 사용자정보 저장.
    * @param memberVo MemberVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertMember(MemberVo memberVo) throws Exception;

    /**
    * 개인상세정보 저장.
    *
    * @Title       : insertMemberDtl 
    * @Description : 개인상세정보 저장.
    * @param memberVo MemberDtlVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertMemberDtl(MemberDtlVo memberDtlVo) throws Exception;

    /**
    * 계정중복 확인.
    *
    * @Title       : checkIdYn 
    * @Description : 계정중복 확인.
    * @param memberVo MemberVo개겣
    * @return String Y or N
    * @throws Exception 예외
    */
    public String checkIdYn(MemberVo memberVo) throws Exception;

    /**
    * 사용자정보 조회.
    *
    * @Title       : selectMemberInfo 
    * @Description : 사용자정보 조회.
    * @param memberVo MemberVo객체
    * @return MemberVo MemberVo객체
    * @throws Exception 예외
    */
    public MemberVo selectMemberInfo(MemberVo memberVo) throws Exception;

    /**
    * 사용자상세정보 조회.
    *
    * @Title       : selectMemberDtlInfo 
    * @Description : 사용자상세정보 조회.
    * @param memberVo MemberVo객체
    * @return MemberDtlVo MemberDtlVo객체
    * @throws Exception 예외
    */
    public MemberDtlVo selectMemberDtlInfo(MemberVo memberVo) throws Exception;

    /**
    * 사용자정보 목록 조회.
    *
    * @Title       : selectMemberList 
    * @Description : 사용자정보 목록 조회.
    * @param param MemberVo객체
    * @return List<MemberVo> 사용자정보 목록
    * @throws Exception 예외
    */
    public List<MemberVo> selectMemberList(MemberVo memberVo) throws Exception;

    /**
    * 사용자수정.
    *
    * @Title       : updateMember 
    * @Description : 사용자수정.
    * @param memberVo MemberVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateMember(MemberVo memberVo) throws Exception;
    
    /**
    * 사용자상세수정.
    *
    * @Title       : updateMemberDtl 
    * @Description : 사용자상세수정.
    * @param memberVo MemberVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateMemberDtl(MemberDtlVo memberVo) throws Exception;
      
    /**
    * 회원의 임시비밀번호를 수정한다.
    *
    * @Title       : updateMemberTempPwd 
    * @Description : 회원의 임시비밀번호를 수정한다.
    * @param tempPwdVo 임시비밀번호Vo 객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateMemberTempPwd(TempPwdVo tempPwdVo) throws Exception;
    
    /**
    * 사용자들의 연락정보를 조회한다.
    *
    * @Title       : selectUserContactInfoList 
    * @Description : 사용자들의 연락정보를 조회한다.
    * @param contractVo contractVo객체
    * @return List<ContractVo> 연락처정보 목록
    * @throws Exception 예외
    */
    public List<ContractVo> selectUserContactInfoList(ContractVo contractVo) throws Exception;
    
    /**
    * 휴면계정 사용자들의 연락정보를 조회한다.
    *
    * @Title       : selectDrmncyUserContactInfoList 
    * @Description : 휴면계정 사용자들의 연락정보를 조회한다.
    * @param contractVo contractVo객체
    * @return List<ContractVo> 휴면계정 사용자들의 연락정보 목록
    * @throws Exception 예외
    */
    public List<ContractVo> selectUserDrmncyContactInfoList(ContractVo contractVo) throws Exception;

    /**
    * 휴면회원정보 목록 조회.
    *
    * @Title       : selectDrmncyMemberList 
    * @Description : 휴면회원정보 목록 조회.
    * @param memberVo MemberVo객체
    * @return List<MemberVo> 휴면회원정보 목록
    * @throws Exception 예외
    */
    public List<MemberVo> selectDrmncyMemberList(MemberVo memberVo) throws Exception;
           
    /**
    * 로그인접속정보 이력 조회.
    *
    * @Title       : selectLoginHistList 
    * @Description : 로그인접속정보 이력 조회.
    * @param memberVo MemberVo객체
    * @return List<LoginHistVo> 로그인접속정보이력
    * @throws Exception 예외
    */
    public List<LoginHistVo> selectLoginHistList(MemberVo memberVo) throws Exception;
   
    /**
    * 수신이메일의 정보를 가져온다.
    *
    * @Title       : selectEamilSndngHistInfo 
    * @Description : 수신이메일의 정보를 가져온다.
    * @param mailVo MailVo객체
    * @return MailVo 수신이메일정보
    * @throws Exception 예외
    */
    public MailVo selectEamilSndngHistInfo(MailVo mailVo) throws Exception;
    
    /**
    * 수신이메일 목록 조회.
    *
    * @Title       : selectEmailReceiveHistList 
    * @Description : 수신이메일 목록 조회.
    * @param mailVo MailVo객체
    * @return List<MailVo> 수신이메일 목록
    * @throws Exception 예외
    */
    public List<MailVo> selectEmailReceiveHistList(MailVo mailVo) throws Exception;
    
    /**
    * 사용자정보 목록 엑셀 다운로드 리스트
    *
    * @Title       : selectMemberExcelList 
    * @Description : 사용자정보 목록 조회.
    * @param param MemberVo객체
    * @return List<MemberVo> 사용자정보 목록
    * @throws Exception 예외
    */
    public List<MemberVo> selectMemberExcelList(MemberVo memberVo) throws Exception;

    
    /*****************************************************************/
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
     *  환경분야 저장
     *
     * @Title       : insertItrstyfld 
     * @Description : 사용자수정.
     * @param memberVo MemberVo객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int insertItrstfld(MemberVo memberVo) throws Exception;
    /**
     *  간편로그인 저장
     *
     * @Title       : insertEsylgn 
     * @Description : 사용자수정.
     * @param memberVo MemberVo객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int insertEsylgn(MemberVo memberVo) throws Exception;
    
    /**
     * 환경분야 삭제
     *
     * @Title       : deleteEnvfld 
     * @Description : 사용자수정.
     * @param memberVo MemberVo객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int deleteEnvfld(MemberVo memberVo) throws Exception;
    /**
     *  관심분야 삭제
     *
     * @Title       : deleteItrstyfld 
     * @Description : 사용자수정.
     * @param memberVo MemberVo객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int deleteItrstfld(MemberVo memberVo) throws Exception;
    /**
     *  간편로그인 삭제
     *
     * @Title       : deletetEsylgn 
     * @Description : 사용자수정.
     * @param memberVo MemberVo객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int deletetEsylgn(MemberVo memberVo) throws Exception;
    
    
    
}