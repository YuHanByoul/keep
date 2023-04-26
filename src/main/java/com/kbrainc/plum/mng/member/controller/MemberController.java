package com.kbrainc.plum.mng.member.controller;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Hex;
import org.egovframe.rte.fdl.cryptography.EgovCryptoService;
import org.egovframe.rte.fdl.cryptography.EgovDigestService;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.member.model.BlcklstDsctnVo;
import com.kbrainc.plum.mng.member.model.ContractVo;
import com.kbrainc.plum.mng.member.model.EmailVo;
import com.kbrainc.plum.mng.member.model.LoginHistVo;
import com.kbrainc.plum.mng.member.model.MemberDtlVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.member.model.SmsVo;
import com.kbrainc.plum.mng.member.model.TempPwdVo;
import com.kbrainc.plum.mng.member.service.MemberServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.DateTimeUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.penta.scpdb.ScpDbAgent;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 사용자관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.member.controller
 * - MemberController.java
 * </pre> 
 *
 * @ClassName : MemberController
 * @Description : 사용자관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;
    
    /**
    * 개인회원관리 리스트화면 이동.
    *
    * @Title       : memberForm 
    * @Description : 개인회원관리 리스트화면 이동.
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/memberForm.html")
    public String memberForm() throws Exception {
        return "mng/member/member";
    }
    
    /**
    * 개인회원관리 등록화면 이동.
    *
    * @Title       : memberInsertForm 
    * @Description : 개인회원관리 등록화면 이동.
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/memberInsertForm.html")
    public String memberInsertForm(Model model) throws Exception {
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[curYear - 1939 + 1];
        
        for(int i = curYear, j = 0; i >= 1939; i--, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("cdgrpid",105);
        paramMap.put("upprCd", 0);
        model.addAttribute("itrstfldList", memberService.selectItrstfldCd(paramMap));
        
       // return "mng/member/memberInsert";
        return "mng/member/memberForm";
    }
    
    /**
    * 개인회원관리 상세화면 이동.
    *
    * @Title       : memberDetailForm 
    * @Description : 개인회원관리 상세화면 이동.
    * @param memberVo MemberVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/memberDetailForm.html")
    public String memberDetailForm(MemberVo memberVo, Model model) throws Exception {
        //model.addAttribute("etcInfo", memberService.selectEtcInfo(memberVo.getUserid()));
        //return "mng/member/memberDetailForm";
        
        MemberVo resultVo = memberService.selectMemberInfo(memberVo);
        
        ScpDbAgent agt = new ScpDbAgent();
        String decStr = "";
        if (System.getenv("PC_KIND") == null) {
            decStr = agt.ScpDecStr(CommonUtil.damoScpIniFilePath, "KEY1", resultVo.getGndr());
        } else {
            decStr = "M"; // 암호화 모듈을 사용할수 없는 MAC인경우 무조건 남자로 설정.
        }

        resultVo.setGndr(decStr);
        
        model.addAttribute("member", resultVo);
        
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("cdgrpid",105);
        paramMap.put("upprCd", 0);
        model.addAttribute("itrstfldList", memberService.selectItrstfldCd(paramMap));
        
        return "mng/member/memberUpdate";
    }

    /**
    * 개인회원관리 수정화면 이동.
    *
    * @Title       : membermodifyForm 
    * @Description : 개인회원관리 수정화면 이동.
    * @param memberVo MemberVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/memberModifyForm.html")
    public String membermodifyForm(MemberVo memberVo, Model model) throws Exception {
        model.addAttribute("member", memberService.selectMemberInfo(memberVo));
        model.addAttribute("memberDtl", memberService.selectMemberDtlInfo(memberVo));
        
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[81];
        for(int i = curYear, j = 0; i >= curYear - 80; i--, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        model.addAttribute("portalUrl", CommonUtil.portalUrl);

        return "mng/member/memberUpdate2";
    }

    /**
    * 계정중복 확인.
    *
    * @Title       : checkIdYn 
    * @Description : 계정중복 확인.
    * @param memberVo MemberVo객체
    * @return Map<String,String>
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/checkIdYn.do")
    @ResponseBody
    public Map<String, String> checkIdYn(MemberVo memberVo) throws Exception {

        Map<String, String> resultMap = new HashMap<>();
        try {

            String result = memberService.checkIdYn(memberVo);
            resultMap.put("result", result);
        } catch (SQLException e) {
            log.error("checkIdYn.Exception.156L");
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
        } catch (Exception e) {
            log.error("checkIdYn.Exception.156L");
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
        }
        return resultMap;
    }
    
    
    /**
    * 개인회원등록.
    *
    * @Title       : insertMember 
    * @Description : 개인회원등록.
    * @param memberVo MemberVo객체
    * @param bindingResult1 memberVo 유효성검증결과
    * @param memberDtlVo MemberDtlVo객체
    * @param bindingResult2 memberDtlVo 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/insertMember.do")
    @ResponseBody
    public Map<String, Object> insertMember(@Valid MemberVo memberVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        memberVo.setUser(user);

        int retVal = 0;
        
        String password = memberVo.getPswd();
        String hashPassword = "";
        if (System.getenv("PC_KIND") == null) {
            ScpDbAgent agt = new ScpDbAgent();
            hashPassword = agt.ScpHashStr(CommonUtil.damoScpIniFilePath, 73, new String(password.getBytes(), "UTF-8")).toLowerCase();
        } else {
            hashPassword = Hex.encodeHexString(MessageDigest.getInstance("SHA-512").digest(password.getBytes("UTF-8")));
        }
        memberVo.setPswd(hashPassword);
        
        ScpDbAgent agt = new ScpDbAgent();
        String encStr = "";
        if (System.getenv("PC_KIND") == null) {
            encStr = agt.ScpEncStr(CommonUtil.damoScpIniFilePath, "KEY1", new String(memberVo.getGndr().getBytes(), "UTF-8"));
        } else {
            encStr = "5D960651E824637099A116BB4A6BA665A6BA3C25"; // 암호화 모듈을 사용할수 없는 MAC인경우 무조건 남자로 설정.
        }
        
        memberVo.setGndr(encStr);
        retVal = memberService.insertMember(memberVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }

    /**
    * 개인회원목록 조회.
    *
    * @Title       : selectMemberList 
    * @Description : 개인회원목록 조회.
    * @param memberVo MemberVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/selectMemberList.do")
    @ResponseBody
    public Map<String, Object> selectMemberList(MemberVo memberVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MemberVo> result = null;
        
        result = memberService.selectMemberList(memberVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    /**
    * 개인회원수정.
    *
    * @Title       : modifyMember 
    * @Description : 개인회원수정.
    * @param memberVo MemberVo객체
    * @param bindingResult1 memberVo 유효성검증결과
    * @param memberDtlVo MemberDtlVo객체
    * @param bindingResult2 memberDtlVo 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/modifyMember.do")
    @ResponseBody
    public Map<String, Object> modifyMember(@Valid MemberVo memberVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        memberVo.setUser(user);

        int retVal = 0;
        
        ScpDbAgent agt = new ScpDbAgent();
        String encStr = "";
        if (System.getenv("PC_KIND") == null) {
            encStr = agt.ScpEncStr(CommonUtil.damoScpIniFilePath, "KEY1", new String(memberVo.getGndr().getBytes(), "UTF-8"));
        } else {
            encStr = "5D960651E824637099A116BB4A6BA665A6BA3C25"; // 암호화 모듈을 사용할수 없는 MAC인경우 무조건 남자로 설정.
        }
        
        memberVo.setGndr(encStr);
        
        retVal = memberService.modifyMember(memberVo);

        //사용자의 세션을 차단하는 기능을 구현
        //PMD 검사로 인한 주석처리 구현시 주석해제 할 것
        
        //if ("Y".equals(memberVo.getAcntLockYn())) {
        //}
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 임시비밀번호 레이어팝업화면.
    *
    * @Title       : tempPwdPop 
    * @Description : 임시비밀번호 레이어팝업화면.
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/tempPwdPop.html")
    public String tempPwdPop(TempPwdVo tempPwdVo,Model model) throws Exception {
        model.addAttribute("tempPwdVo",tempPwdVo);
        return "mng/member/tempPwdPopup";
    }

    /**
    * 임시비밀번호를 저장하고 발급방식이 자동인 경우 메일 또는 SMS로 발송처리한다.
    *
    * @Title       : createTempPwd 
    * @Description : 임시비밀번호를 저장하고 발급방식이 자동인 경우 메일 또는 SMS로 발송처리한다.
    * @param tempPwdVo 임시비밀번호Vo객체
    * @param bindingResult 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/createTempPwd.do")
    @ResponseBody
    public Map<String, Object> createTempPwd(TempPwdVo tempPwdVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        tempPwdVo.setUser(user);
        boolean retVal = false;
        retVal = memberService.createTempPwd(tempPwdVo);
        
        if (retVal) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "임시비밀번호발급에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "임시비밀번호발급에 실패했습니다.");
        }

        return resultMap;
    }
    
    /**
    * sms발송 레이어팝업화면.
    *
    * @Title       : smsPop 
    * @Description : sms발송 레이어팝업화면.
    * @param contractVo ContractVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/smsPop.html")
    public String smsPop(ContractVo contractVo, Model model) throws Exception {
        List<ContractVo> contactInfoList = null;
        
        if ("Y".equals(contractVo.getDrmncyYn())) { // 휴면계정인 경우
            contactInfoList = memberService.selectUserDrmncyContactInfoList(contractVo);
        } else {
            contactInfoList = memberService.selectUserContactInfoList(contractVo);
        }
        model.addAttribute("contactInfoList", contactInfoList);
        model.addAttribute("contactInfoItemsCount", contactInfoList.size());
        return "mng/member/smsPop";
    }
    
    /**
    * email발송 레이어팝업화면.
    *
    * @Title       : emailPop 
    * @Description : email발송 레이어팝업화면.
    * @param contractVo ContractVo객체
    * @param model 모델
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/emailPop.html")
    public String emailPop(ContractVo contractVo, Model model) throws Exception {
        List<ContractVo> contactInfoList = null;
        
        if ("Y".equals(contractVo.getDrmncyYn())) { // 휴면계정인 경우
            contactInfoList = memberService.selectUserDrmncyContactInfoList(contractVo);
        } else {
            contactInfoList = memberService.selectUserContactInfoList(contractVo);
        }
        model.addAttribute("contactInfoList", contactInfoList);
        model.addAttribute("contactInfoItemsCount", contactInfoList.size());
        return "mng/member/emailPop";
    }

    /**
    * email발송.
    *
    * @Title       : sendEmail 
    * @Description : email발송.
    * @param emailVo EmailVo객체
    * @param bindingResult 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/sendEmail.do")
    @ResponseBody
    public Map<String, Object> sendEmail(@Valid EmailVo emailVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        emailVo.setUser(user);           
        boolean retVal = false;
        retVal = memberService.sendMail(emailVo);
        
        if (retVal) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "메일 발송에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "메일 발송에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * sms발송.
    *
    * @Title       : sendSms 
    * @Description : sms발송.
    * @param smsVo SmsVo객체
    * @param bindingResult 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/sendSms.do")
    @ResponseBody
    public Map<String, Object> sendSms(@Valid SmsVo smsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        smsVo.setUser(user);           
        boolean retVal = false;
        retVal = memberService.sendSms(smsVo);
        
        if (retVal) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "SMS 발송에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "SMS 발송에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 휴면회원정보 리스트화면 이동.
    *
    * @Title       : memberDrmncyForm 
    * @Description : 휴면회원정보 리스트화면 이동.
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/memberDrmncyForm.html")
    public String memberDrmncyForm() throws Exception {
        return "mng/member/memberDrmncy";
    }

    /**
    * 휴면회원정보 목록(개인) 조회.
    *
    * @Title       : selectDrmncyMemberList 
    * @Description : 휴면회원정보 목록(개인) 조회. 
    * @param memberVo MemberVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/selectDrmncyMemberList.do")
    @ResponseBody
    public Map<String, Object> selectDrmncyMemberList(MemberVo memberVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MemberVo> result = memberService.selectDrmncyMemberList(memberVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * sms수신내역 리스트 화면.
    *
    * @Title       : smsReceiveHistoryForm 
    * @Description : sms수신내역 리스트 화면.
    * @param userid 사용자아이디
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/smsReceiveHistoryForm.html")
    public String smsReceiveHistoryForm(String userid) throws Exception {
        return "mng/member/smsReceiveHistory";
    }
    
    /**
    * email수신내역 리스트 화면.
    *
    * @Title       : emailReceiveHistoryForm 
    * @Description : email수신내역 리스트 화면.
    * @param userid 사용자아이디
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/emailReceiveHistoryForm.html")
    public String emailReceiveHistoryForm(String userid) throws Exception {
        return "mng/member/emailReceiveHistory";
    } 
    
    /**
    * 수신이메일 목록 조회.
    *
    * @Title       : selectLoginHistList 
    * @Description : 수신이메일 목록 조회.
    * @param memberVo MemberVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/selectEmailReceiveHistList.do")
    @ResponseBody
    public Map<String, Object> selectEmailReceiveHistList(MailVo mailVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MailVo> result = memberService.selectEmailReceiveHistList(mailVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.get(0).getTotalCount());
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 수신이메일 상세화면 이동.
    *
    * @Title       : emailReceiveHistoryDetail 
    * @Description : 수신이메일 상세화면 이동.
    * @param mailVo MailVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/emailReceiveHistoryDetail.html")
    public String emailReceiveHistoryDetail(MailVo mailVo, Model model) throws Exception {
        model.addAttribute("emailInfo", memberService.selectEamilSndngHistInfo(mailVo));
        return "mng/member/emailReceiveHistoryDetail";
    }
    
    /**
    * 로그인 접속이력 화면.
    *
    * @Title       : loginHistoryForm 
    * @Description : 로그인 접속이력 화면.
    * @param userid 사용자이이디
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/loginHistoryForm.html")
    public String loginHistoryForm(String userid) throws Exception {
        return "mng/member/loginHistory";
    }
    
    /**
    * 로그인 접속정보 조회.
    *
    * @Title       : selectLoginHistList 
    * @Description : 로그인 접속정보 조회.
    * @param memberVo MemberVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/selectLoginHistList.do")
    @ResponseBody
    public Map<String, Object> selectLoginHistList(MemberVo memberVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<LoginHistVo> result = memberService.selectLoginHistList(memberVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.get(0).getTotalCount());
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /********************************************************************/
    /**
    * 블랙리스트 레이어팝업화면.
    *
    * @Title       : blcklstDsctnPopup 
    * @Description : 임시비밀번호 레이어팝업화면.
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/blcklstDsctnPopup.html")
    public String blcklstDsctnPopup(BlcklstDsctnVo blcklstDsctnVo,Model model) throws Exception {
        List<MemberVo> result = memberService.selectBlcklstMemberList(blcklstDsctnVo);
        model.addAttribute("users", result);
        return "mng/member/blcklstDsctnPopup";
    }
    
    /**
    * 블랙리스트 지정 및 해제 처리.
    *
    * @Title       : updatetBlcklstDsctn 
    * @Description : 블랙리스트 지정 및 해제 처리
    * @param BannerVo blcklstDsctnVo객체
    * @param bindingResult 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/updatetBlcklstDsctn.do")
    @ResponseBody
    public Map<String, Object> updatetBlcklstDsctn(BlcklstDsctnVo blcklstDsctnVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> resMap = new HashMap<String, Object>();
        
        int retVal = 0 ;
        
        blcklstDsctnVo.setUser(user);
        
        resMap = memberService.updateMemberBlcklstYn(blcklstDsctnVo);
        
        if((boolean)resMap.get("isUnmatchedgUser")) {
            resultMap.put("msg"
                    , (blcklstDsctnVo.getBlcklstYn().equals("Y"))? 
                       "이미 블랙리스트로 지정된 사용자를 제외하고 처리되었습니다."
                       :"블랙리스트에 포함되지 않은 사용자를 제외하고 처리되었습니다");
        }else {
            resultMap.put("msg"
                    , (blcklstDsctnVo.getBlcklstYn().equals("Y"))? 
                            "블랙리스트로 지정처리 되었습니다"
                            :"블랙리스트에서 해지처리되었습니다");
        }
        
        return resultMap;
    }
    
    /**
    * 계정 잠금 해제 
    *
    * @Title       : clearAcntLock 
    * @Description : 계정 잠금 해제
    * @param MemberVo memberVo 
    * @param bindingResult 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/member/clearAcntLock.do")
    @ResponseBody
    public Map<String, Object> clearAcntLock(MemberVo memberVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        memberVo.setUser(user);           
        int retVal = 0;
        
        retVal = memberService.updateLockStts(memberVo); 
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "계정잠금을 해제 하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "계정잠금 해제에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
     * 회원탈퇴 
     *
     * @Title       : quitMember 
     * @Description : 회원탈퇴.
     * @param MemberVo 객체
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/member/quitMember.do")
    @ResponseBody
    public Map<String, Object> quitMember(MemberVo memberVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        memberVo.setUser(user);
        
        int retVal = 0;
        if(memberService.checkJoinWithOnepassYn(memberVo).equals("Y")) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "디지털 원패스 연동 회원입니다. 연동 해지 후 탈퇴 가능합니다.");
            return resultMap;
        }
        
        retVal = memberService.updateMemberDelYn(memberVo); 
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "탈퇴 처리 하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "탈퇴 처리에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    
    
    
}