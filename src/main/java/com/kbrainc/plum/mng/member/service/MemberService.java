package com.kbrainc.plum.mng.member.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.mng.member.model.BlcklstDsctnVo;
import com.kbrainc.plum.mng.member.model.ContractVo;
import com.kbrainc.plum.mng.member.model.EmailVo;
import com.kbrainc.plum.mng.member.model.LoginHistVo;
import com.kbrainc.plum.mng.member.model.MemberDtlVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.member.model.SmsVo;
import com.kbrainc.plum.mng.member.model.TempPwdVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;

/**
 * 
 * 사용자관리 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.mng.member.service
 * - MemberService.java
 * </pre> 
 *
 * @ClassName : MemberService
 * @Description : 사용자관리 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface MemberService {

    /**
    * 사용자정보 등록.
    *
    * @Title       : insertMember 
    * @Description : 사용자정보 저장.
    * @param memberVo MemberVo객체
    * @param memberDtlVo MemberDtlVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertMember(MemberVo memberVo) throws Exception;

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
    * @param memberVo MemberVo객체
    * @return List<MemberVo> 사용자정보 목록
    * @throws Exception 예외
    */
    public List<MemberVo> selectMemberList(MemberVo memberVo) throws Exception;

    /**
    * 사용자정보 수정.
    *
    * @Title       : modifyMember 
    * @Description : 사용자/사용자상세 정보 수정.
    * @param memberVo MemberVo객체
    * @param memberDtlVo MemberDtlVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int modifyMember(MemberVo memberVo) throws Exception;
    
    /**
    * 회원의 임시비밀번호를 수정한다.
    *
    * @Title       : updateMemberTempPwd 
    * @Description : 회원의 임시비밀번호를 수정한다.
    * @param tempPwdVo 임시비밀번호Vo 객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public boolean createTempPwd(TempPwdVo tempPwdVo) throws Exception;
     
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
    * email발송.
    *
    * @Title       : sendMail 
    * @Description : email발송.
    * @param emailVo 이메일Vo객체
    * @return boolean 메일발송 성공여부
    * @throws Exception 예외
    */
    public boolean sendMail(EmailVo emailVo) throws Exception;
    
    /**
    * sms발송.
    *
    * @Title       : sendSms 
    * @Description : sms발송.
    * @param smsVo SmsVo객체
    * @return boolean sms발송 성공여부
    * @throws Exception 예외
    */
    public boolean sendSms(SmsVo smsVo) throws Exception;
    
    /**
    * @Title : selectDrmncyMemberList
    * @Description : 휴면회원정보 목록 조회
    * @param memberVo MemberVo 타입의 인자
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
    * 프로필사진을 가져온다.
    *
    * @Title       : getProfileImg 
    * @Description : 프로필사진을 가져온다.
    * @param fileid 파일아이디
    * @return String base64이미지
    */
    public String getProfileImg(int fileid) ;
    
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
    
    /**
     *  사용자 블랙리스트 여부 업데이트 
     *
     * @Title       : deletetEsylgn 
     * @Description : 사용자수정.
     * @param BannerVo BlcklstDsctnVo객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public Map<String,Object> updateMemberBlcklstYn(BlcklstDsctnVo blcklstDsctnVo) throws Exception;
    
    /**
    * 블랙리스트 대상 사용자 리스트
    *
    * @Title       : selectBlcklstMemberList 
    * @Description : 사용자정보 목록 조회.
    * @param param BlcklstDsctnVo객체
    * @return List<MemberVo> 블랙리스트 체크 목록
    * @throws Exception 예외
    */
    public List<MemberVo> selectBlcklstMemberList(BlcklstDsctnVo blcklstDsctnVo) throws Exception;
    
    /**
     *  사용자 계정잠금 해제 처리  
     *
     * @Title       : updateLockStts 
     * @Description : 사용자수정.
     * @param MemberVo memberVo 객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int updateLockStts(MemberVo memberVo) throws Exception;
    /**
     *  사용자 탈퇴 처리  
     *
     * @Title       : updateMemberDelYn 
     * @Description : 사용자수정.
     * @param MemberVo memberVo 객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int updateMemberDelYn(MemberVo memberVo) throws Exception;
    /**
     *  회원 기관정보 수정  
     *
     * @Title       : updateInstMemberRole 
     * @Description : 회원 기관정보 수정
     * @param MemberVo memberVo 객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int updateInstMemberRole(MemberVo memberVo) throws Exception;
    /**
     *  원패스 가입여부 확인    
     *
     * @Title       : checkJoinWithOnepassYn 
     * @Description : 사용자수정.
     * @param MemberVo memberVo 객체
     * @return String 
     * @throws Exception 예외
     */
    public String checkJoinWithOnepassYn(MemberVo memberVo) throws Exception;
    
    
}