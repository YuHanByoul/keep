package com.kbrainc.plum.mng.member.service;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.cmm.file.service.FileStorageService;
import com.kbrainc.plum.cmm.service.SmsService;
import com.kbrainc.plum.mng.member.model.BlcklstDsctnVo;
import com.kbrainc.plum.mng.member.model.ContractVo;
import com.kbrainc.plum.mng.member.model.EmailVo;
import com.kbrainc.plum.mng.member.model.LoginHistVo;
import com.kbrainc.plum.mng.member.model.MemberDao;
import com.kbrainc.plum.mng.member.model.MemberDtlVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.member.model.SmsVo;
import com.kbrainc.plum.mng.member.model.TempPwdVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.exception.MailSendFailException;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

/**
 * 
 * 사용자관리 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.member.service
 * - MemberServiceImpl.java
 * </pre> 
 *
 * @ClassName : MemberServiceImpl
 * @Description : 사용자관리 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class MemberServiceImpl extends PlumAbstractServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired @Qualifier("MailService")
    private MailService mailService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    private SmsService smsService;
    
    //@Value("${front.server.host}")
    private String frontServerHost;
    
    @Autowired
    private TemplateEngine templateEngine;
    
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
    @Override
    @Transactional
    public int insertMember(MemberVo memberVo) throws Exception {
        int retVal = 0;
        retVal += memberDao.insertMember(memberVo);
        
        memberDao.deleteEnvfld(memberVo);
        memberDao.deleteItrstfld(memberVo);
        //간편로그인 차후 필요시 사용
        //memberDao.deletetEsylgn(memberVo);
        
       if(memberVo.getEnvfldCds()!=null) {
           retVal += memberDao.insertEnvfld(memberVo);
       }
       if(memberVo.getItrstfldCds()!=null) {
           retVal += memberDao.insertItrstfld(memberVo);
       }
       //간편로그인 차후 필요시 사용
       //if(memberVo.getEsylgnCds()!=null) {
       //    retVal += memberDao.insertEsylgn(memberVo);
       //}
       
       return retVal;
    }

    /**
    * 계정중복 확인.
    *
    * @Title       : checkIdYn 
    * @Description : 계정중복 확인.
    * @param memberVo MemberVo개겣
    * @return String Y or N
    * @throws Exception 예외
    */
    @Override
    public String checkIdYn(MemberVo memberVo) throws Exception {
        return memberDao.checkIdYn(memberVo);
    }
    
    /**
    * 사용자정보 조회.
    *
    * @Title       : selectMemberInfo 
    * @Description : 사용자정보 조회.
    * @param memberVo MemberVo객체
    * @return MemberVo MemberVo객체
    * @throws Exception 예외
    */
    @Override
    public MemberVo selectMemberInfo(MemberVo memberVo) throws Exception {
        return memberDao.selectMemberInfo(memberVo);
    }

    /**
    * 사용자상세정보 조회.
    *
    * @Title       : selectMemberDtlInfo 
    * @Description : 사용자상세정보 조회.
    * @param memberVo MemberVo객체
    * @return MemberDtlVo MemberDtlVo객체
    * @throws Exception 예외
    */
    @Override
    public MemberDtlVo selectMemberDtlInfo(MemberVo memberVo) throws Exception {
        MemberDtlVo memberDtlVo = new MemberDtlVo();
        memberDtlVo = memberDao.selectMemberDtlInfo(memberVo);
        String brthdy = StringUtil.nvl(memberDtlVo.getBrthdy());
        
        if(!"".equals(brthdy)) {
            String[] brthdyArr = brthdy.split("-");
            memberDtlVo.setBrthdyYear(brthdyArr[0]);
            memberDtlVo.setBrthdyMonth(brthdyArr[1]);
            memberDtlVo.setBrthdyDay(brthdyArr[2]);
        }
        return memberDtlVo;
    }
    
    /**
    * 사용자정보 목록 조회.
    *
    * @Title       : selectMemberList 
    * @Description : 사용자정보 목록 조회.
    * @param memberVo MemberVo객체
    * @return List<MemberVo> 사용자정보 목록
    * @throws Exception 예외
    */
    @Override
    public List<MemberVo> selectMemberList(MemberVo memberVo) throws Exception {
        return memberDao.selectMemberList(memberVo);
    }

    /**
    * 사용자/사용자상세 정보 수정.
    *
    * @Title       : modifyMember 
    * @Description : 사용자/사용자상세 정보 수정.
    * @param memberVo MemberVo객체
    * @param memberDtlVo MemberDtlVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int modifyMember(MemberVo memberVo) throws Exception {
        int retVal = 0;
        
        retVal += memberDao.updateMember(memberVo);
        
        memberDao.deleteEnvfld(memberVo);
        memberDao.deleteItrstfld(memberVo);
        //간편로그인 차후 필요시 사용
        //memberDao.deletetEsylgn(memberVo);
        
        if(memberVo.getEnvfldCds()!=null && memberVo.getEnvfldCds().length > 0) {
            retVal += memberDao.insertEnvfld(memberVo);
        }
        if(memberVo.getItrstfldCds()!=null && memberVo.getItrstfldCds().length > 0) {
            retVal += memberDao.insertItrstfld(memberVo);
        }
        //간편로그인 차후 필요시 사용
        //if(memberVo.getEsylgnCds()!=null && memberVo.getEsylgnCds().length > 0) {
        //    retVal += memberDao.insertEsylgn(memberVo);
        //}
        
        return retVal;
    }
    
    /**
    * 임시비밀번호를 저장하고 발급방식이 자동인 경우 메일 또는 SMS로 발송처리한다.
    *
    * @Title       : createTempPwd 
    * @Description : 임시비밀번호를 저장하고 발급방식이 자동인 경우 메일 또는 SMS로 발송처리한다.
    * @param tempPwdVo 임시비밀번호Vo 객체
    * @return boolean 성공여부
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public boolean createTempPwd(TempPwdVo tempPwdVo) throws Exception {
        
        MemberVo memVo = new MemberVo();
        memVo.setUserid(tempPwdVo.getUserid());
        MemberVo mebmerInfo = selectMemberInfo(memVo);
        
        String password = "";
        
        if ("nonauto".equals(tempPwdVo.getMethod())) { // 수동인 경우 DB에 저장
            password = tempPwdVo.getPswd();
        } else {
            // 랜덤임시비밀번호 생성
            password = StringUtil.temporaryPassword(8);
        }
        
        // 비밀번호 암호화
        String hashPassword = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(password.getBytes("UTF-8")));
        tempPwdVo.setPswd(hashPassword);
        int retVal = memberDao.updateMemberTempPwd(tempPwdVo);
        
        if ("nonauto".equals(tempPwdVo.getMethod())) { // 수동인 경우 DB에 저장
            if(retVal > 0) {
                return true;
            }
        } else { // 자동인 경우 전송수단에 따라서 이메일 또는 SMS발송 처리       
            String transType = tempPwdVo.getTransType();
            
            if ("email".equals(transType)) {
                Context context = new Context();
                context.setVariable("pssword", password);
                context.setVariable("frontServerHost", frontServerHost);
                String mailContents = templateEngine.process("mail/mail_tempPwd", context);
                
                String mailTitle = "임시비밀번호 발급";
                MailVo mailVo = new MailVo(null, mebmerInfo.getEml(), mailTitle, mailContents, Integer.valueOf(tempPwdVo.getUser().getUserid()), "U", mebmerInfo.getUserid());
                                
                Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                String result = (String)resMap.get("result");
                
                if (Constant.REST_API_RESULT_SUCCESS.equals(result)) {
                    return true;
                } else {
                    throw new MailSendFailException("메일발송에 실패하였습니다.");
                }
            } 
            
            //SMS 차후 처리 할것 
            else if("sms".equals(transType)) {
                // sms 발송
                String phone = mebmerInfo.getMoblphon();
                if ("".equals(StringUtil.nvl(phone))) {
                    return false;
                }
                String msg = "[임시비밀번호 : " + password + "] KEEP 에서 보낸 임시비밀번호 입니다.";
                Map<String, Object> resMap = smsService.sendOneSms(phone, msg); // sms 발송
                String result = (String)resMap.get("result");
                
                if ("OK".equals(result)) {
                    return true;
                } else {
                    return false;
                }
            }
            
            
            
            else {
                return false;
            }
        }

        return true;
    }

    /**
    * 사용자들의 연락정보를 조회한다.
    *
    * @Title       : selectUserContactInfoList 
    * @Description : 사용자들의 연락정보를 조회한다.
    * @param contractVo contractVo객체
    * @return List<ContractVo> 사용자들의 연락정보 목록
    * @throws Exception 예외
    */
    @Override
    public List<ContractVo> selectUserContactInfoList(ContractVo contractVo) throws Exception {
        return memberDao.selectUserContactInfoList(contractVo);
    }
    
    /**
    * 휴면계정 사용자들의 연락정보를 조회한다.
    *
    * @Title       : selectDrmncyUserContactInfoList 
    * @Description : 휴면계정 사용자들의 연락정보를 조회한다.
    * @param contractVo contractVo객체
    * @return List<ContractVo> 휴면계정 사용자들의 연락정보 목록
    * @throws Exception 예외
    */
    @Override
    public List<ContractVo> selectUserDrmncyContactInfoList(ContractVo contractVo) throws Exception {
        return memberDao.selectUserDrmncyContactInfoList(contractVo);
    }
     
    /**
    * email발송.
    *
    * @Title       : sendMail 
    * @Description : email발송.
    * @param emailVo 이메일Vo객체
    * @return boolean 메일발송 성공여부
    * @throws Exception 예외
    */
    @Override
    public boolean sendMail(EmailVo emailVo) throws Exception {
        List<MailRcptnVo> to= emailVo.getEmails();
        String text = emailVo.getEmailTitle();
        String content = emailVo.getEmailContent();
        
        Context context = new Context();
        context.setVariable("text", text);
        context.setVariable("contents", content);
        context.setVariable("frontServerHost", frontServerHost);
        String contents = templateEngine.process("mail/mail_basic_template", context);
        
        MailVo mailVo = new MailVo(null, null, text, content, Integer.valueOf(emailVo.getUser().getUserid()), "U", 0);
        Map<String, Object> resMap = mailService.sendMultiMail(to, mailVo); // 이메일 발송
        String result = (String)resMap.get("result");
        
        if (Constant.REST_API_RESULT_SUCCESS.equals(result)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
    * sms발송.
    *
    * @Title       : sendSms 
    * @Description : sms발송.
    * @param smsVo SmsVo객체
    * @return boolean sms발송 성공여부
    * @throws Exception 예외
    */
    @Override
    public boolean sendSms(SmsVo smsVo) throws Exception {
        String[] mobnos = smsVo.getMobnos();
        String smsText = smsVo.getSmsText();
        
        Map<String, Object> resMap = null;
        
        if ("1".equals(smsVo.getSmsSendType())) {
            resMap = smsService.sendMultiSms(mobnos, smsText); // SMS 발송
        } else if ("2".equals(smsVo.getSmsSendType())) {
            resMap = smsService.sendMultiReserveSms(mobnos, smsText, smsVo.getReserveDate()); // SMS 예약발송
        }
        
        String result = "";
        if (resMap != null) {
            result = (String)resMap.get("result");
        }
        
        if ("OK".equals(result)) {
            return true;
        } else {
            return false;
        }
    }
      
    /**
    * @Title : selectDrmncyMemberList
    * @Description : 휴면회원정보 목록 조회
    * @param memberVo MemberVo 타입의 인자
    * @throws Exception
    * @return List<MemberVo>
    */
    @Override
    public List<MemberVo> selectDrmncyMemberList(MemberVo memberVo) throws Exception {
        return memberDao.selectDrmncyMemberList(memberVo);
    }

    /**
    * 로그인접속정보 이력 조회.
    *
    * @Title       : selectLoginHistList 
    * @Description : 로그인접속정보 이력 조회.
    * @param memberVo MemberVo객체
    * @return List<LoginHistVo> 로그인접속정보이력
    * @throws Exception 예외
    */
    @Override
    public List<LoginHistVo> selectLoginHistList(MemberVo memberVo) throws Exception {
        return memberDao.selectLoginHistList(memberVo);
    }
 
    /**
    * 프로필사진을 가져온다.
    *
    * @Title       : getProfileImg 
    * @Description : 프로필사진을 가져온다.
    * @param fileid 파일아이디
    * @return String base64이미지
    */
    @Override
    public String getProfileImg(int fileid) {
        FileVo fileVo = new FileVo();
        fileVo.setFileid(fileid);
        String fileName = "";
        String base64Img = "";
        
        try {
            fileVo = fileService.selectFile(fileVo);
            if (fileVo == null) {
                return "";
            }
            fileName = fileVo.getSaveFileNm();
            base64Img = fileStorageService.imgToStringByBase64(fileVo.getFilePath() + "/THUMB_" + fileName);            
        } catch (SQLException e) {
            return "";
        }  catch (Exception e) {
            return "";
        } 
                        
        return base64Img;
    }
    
    /**
    * 수신이메일의 정보를 가져온다.
    *
    * @Title       : selectEamilSndngHistInfo 
    * @Description : 수신이메일의 정보를 가져온다.
    * @param mailVo MailVo객체
    * @return MailVo 수신이메일정보
    * @throws Exception 예외
    */
    public MailVo selectEamilSndngHistInfo(MailVo mailVo) throws Exception {
        return memberDao.selectEamilSndngHistInfo(mailVo);
    }
    
    /**
    * 수신이메일 목록 조회.
    *
    * @Title       : selectEmailReceiveHistList 
    * @Description : 수신이메일 목록 조회.
    * @param mailVo MailVo객체
    * @return List<MailVo> 수신이메일 목록
    * @throws Exception 예외
    */
    public List<MailVo> selectEmailReceiveHistList(MailVo mailVo) throws Exception {
        return memberDao.selectEmailReceiveHistList(mailVo);
    }
    
    /**
    * 사용자정보 목록 엑셀 다운로드 리스트
    *
    * @Title       : selectMemberExcelList 
    * @Description : 사용자정보 목록 조회.
    * @param param MemberVo객체
    * @return List<MemberVo> 사용자정보 목록
    * @throws Exception 예외
    */
    public List<MemberVo> selectMemberExcelList(MemberVo memberVo) throws Exception{
    	return memberDao.selectMemberExcelList(memberVo);
    };
    
    /**
     *  사용자 블랙리스트 여부 업데이트 
     *
     * @Title       : deletetEsylgn 
     * @Description : 사용자수정.
     * @param BlcklstDsctnVo BlcklstDsctnVo객체
     * @return int update로우수
     * @throws Exception 예외
     */
    @Transactional
    public Map<String,Object> updateMemberBlcklstYn(BlcklstDsctnVo blcklstDsctnVo) throws Exception{
        
        Map<String,Object> result = new HashMap();
        
        List<BlcklstDsctnVo> emeList = memberDao.selectBlcklstMemberChkList(blcklstDsctnVo);
        
        boolean isUnmatchedgUser = false;
        Integer unmatchedgUserCnt = 0;
        
        List<BlcklstDsctnVo> insertBlcklstList = new ArrayList();
        List<BlcklstDsctnVo> updateUserBlckYnList = new ArrayList();
        
        List<String> insertBlcklstArr = new ArrayList();
        List<String> updateUserBlckYnArr = new ArrayList();
        
        for(BlcklstDsctnVo vo :  emeList) {
            if(vo.getUpdtCd().equals("1")){
                
                insertBlcklstArr.add(String.valueOf(vo.getUserid()));
                updateUserBlckYnArr.add(String.valueOf(vo.getUserid()));
            }else if(vo.getUpdtCd().equals("2")) {
                updateUserBlckYnArr.add(String.valueOf(vo.getUserid()));
            }else if(vo.getUpdtCd().equals("3")) {
                insertBlcklstArr.add(String.valueOf(vo.getUserid()));
            }else {
                isUnmatchedgUser = true;
                unmatchedgUserCnt++;
            }
        }
        
        if(insertBlcklstArr.size() > 0) {
            String[] insertIds = insertBlcklstArr.toArray(new String[insertBlcklstArr.size()]);
            blcklstDsctnVo.setBlcklstIds(insertIds);
            result.put("blackInsertCnt",memberDao.insertBlcklstDsctn(blcklstDsctnVo));
        }
        
        if(updateUserBlckYnArr.size() > 0) {
            String[] updateIds = updateUserBlckYnArr.toArray(new String[updateUserBlckYnArr.size()]);
            blcklstDsctnVo.setBlcklstIds(updateIds);
            result.put("userUpdateCnt",memberDao.updateMemberBlcklstYn(blcklstDsctnVo));
        }
        
        result.put("isUnmatchedgUser",isUnmatchedgUser);
        result.put("unmatchedgUserCnt",unmatchedgUserCnt);
        
        return result ;
    };
    
    /**
    * 블랙리스트 대상 사용자 리스트
    *
    * @Title       : selectBlcklstMemberList 
    * @Description : 사용자정보 목록 조회.
    * @param param BlcklstDsctnVo객체
    * @return List<MemberVo> 블랙리스트 체크 목록
    * @throws Exception 예외
    */
    public List<MemberVo> selectBlcklstMemberList(BlcklstDsctnVo blcklstDsctnVo) throws Exception{
        return memberDao.selectBlcklstMemberList(blcklstDsctnVo);
    };
    
    
    /**
     *  사용자 계정잠금 해제 처리  
     *
     * @Title       : updateLockStts 
     * @Description : 사용자수정.
     * @param MemberVo memberVo 객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int updateLockStts(MemberVo memberVo) throws Exception{
        return memberDao.updateLockStts(memberVo);
    };
    /**
     *  사용자 탈퇴 처리  
     *
     * @Title       : updateMemberDelYn 
     * @Description : 사용자수정.
     * @param MemberVo memberVo 객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int updateMemberDelYn(MemberVo memberVo) throws Exception{
        return memberDao.updateMemberDelYn(memberVo);
    };
    /**
     *  회원 기관정보 수정  
     *
     * @Title       : updateInstMemberRole 
     * @Description : 회원 기관정보 수정
     * @param MemberVo memberVo 객체
     * @return int update로우수
     * @throws Exception 예외
     */
    public int updateInstMemberRole(MemberVo memberVo) throws Exception{
        return memberDao.updateInstMemberRole(memberVo);
    };
    
}