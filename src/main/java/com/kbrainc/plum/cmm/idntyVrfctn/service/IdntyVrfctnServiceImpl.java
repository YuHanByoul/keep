package com.kbrainc.plum.cmm.idntyVrfctn.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnFailVo;
import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnStartVo;
import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnSuccessVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 본인인증 관련 요청을 처리하는 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.cmm.idntyVrfctn.service
 * - IdntyVrfctnService.java
 * </pre> 
 *
 * @ClassName : idntyVrfctn
 * @Description : 본인인증 관련 요청을 처리하는 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2023. 2. 7.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class IdntyVrfctnServiceImpl extends PlumAbstractServiceImpl implements IdntyVrfctnService {

    @Value("${niceid.mobilephone.sitecode}")
    private String niceidMobilephoneSiteCode;
    
    @Value("${niceid.mobilephone.sitepassword}")
    private String niceidMobilephoneSitePassword;
    
    /**
    * 휴대폰 본인인증 업체정보 검증.
    *
    * @Title : siteInfoVerification
    * @Description : 휴대폰 본인인증 업체정보 검증
    * @param request 요청객체
    * @param returnUrl 리턴URL
    * @param errorUrl 에러URL
    * @return IdntyVrfctnStartVo 검증결과 정보
    * @throws Exception 예외
    */
    public IdntyVrfctnStartVo siteInfoVerification(HttpServletRequest request, String returnUrlPath, String errorUrlPath) throws Exception {
        IdntyVrfctnStartVo result = new IdntyVrfctnStartVo();
        String schemeHostPort = request.getRequestURL().toString().replaceFirst(request.getRequestURI(), "");
        String sReturnUrl = schemeHostPort + returnUrlPath;
        String sErrorUrl = schemeHostPort + errorUrlPath;
        
        // 본인인증
        NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
        
        String sSiteCode = niceidMobilephoneSiteCode; // NICE로부터 부여받은 사이트 코드
        String sSitePassword = niceidMobilephoneSitePassword; // NICE로부터 부여받은 사이트 패스워드
        
        String sRequestNumber = "REQ0000000001"; // 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
        // 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
        sRequestNumber = niceCheck.getRequestNO(sSiteCode);
        HttpSession session = request.getSession();
        session.setAttribute("REQ_SEQ" , sRequestNumber); // 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
        
        String sAuthType = ""; // 없으면 기본 선택화면, M(휴대폰), X(인증서공통), U(공동인증서), F(금융인증서), S(PASS인증서), C(신용카드)
        String customize = ""; //없으면 기본 웹페이지 / Mobile : 모바일페이지
        
        // CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
        //리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
        // 입력될 plain 데이타를 만든다.
        String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
                "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
                "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
                "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
                "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
                "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;
        
        String sMessage = "";
        String sEncData = "";
        
        int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
        if( iReturn == 0 )
        {
            sEncData = niceCheck.getCipherData();
        }
        else if( iReturn == -1)
        {
            sMessage = "암호화 시스템 에러입니다.";
        }    
        else if( iReturn == -2)
        {
            sMessage = "암호화 처리오류입니다.";
        }    
        else if( iReturn == -3)
        {
            sMessage = "암호화 데이터 오류입니다.";
        }    
        else if( iReturn == -9)
        {
            sMessage = "입력 데이터 오류입니다.";
        }    
        else
        {
            sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
        }
        
        result.setSEncData(sEncData);
        result.setSMessage(sMessage);
        return result;
    }
    
    public String requestReplace (String paramValue, String gubun) {
        String result = paramValue;
        
        if (result != null) {
            
            result = result.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

            result = result.replaceAll("\\*", "");
            result = result.replaceAll("\\?", "");
            result = result.replaceAll("\\[", "");
            result = result.replaceAll("\\{", "");
            result = result.replaceAll("\\(", "");
            result = result.replaceAll("\\)", "");
            result = result.replaceAll("\\^", "");
            result = result.replaceAll("\\$", "");
            result = result.replaceAll("'", "");
            result = result.replaceAll("@", "");
            result = result.replaceAll("%", "");
            result = result.replaceAll(";", "");
            result = result.replaceAll(":", "");
            result = result.replaceAll("-", "");
            result = result.replaceAll("#", "");
            result = result.replaceAll("--", "");
            result = result.replaceAll("-", "");
            result = result.replaceAll(",", "");
            
            if(gubun != "encodeData"){
                result = result.replaceAll("\\+", "");
                result = result.replaceAll("/", "");
                result = result.replaceAll("=", "");
            }
        }
        return result;
    }
    
    /**
    * 본인인증 수행후 성공데이터 복호화.
    *
    * @Title : decodeIdntyVrfctnSuccessData
    * @Description : 본인인증 수행후 성공데이터 복호화
    * @param session 세션객체
    * @param encodeData 본인인증결과(인코딩데이터)
    * @return IdntyVrfctnSuccessVo 본인인증 복호화 데이터
    * @throws Exception 예외
    */
    public IdntyVrfctnSuccessVo decodeIdntyVrfctnSuccessData(HttpSession session, String encodeData) throws Exception {
        IdntyVrfctnSuccessVo result = new IdntyVrfctnSuccessVo();
        NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

        String sEncodeData = requestReplace(encodeData, "encodeData");

        String sSiteCode = niceidMobilephoneSiteCode;              // NICE로부터 부여받은 사이트 코드
        String sSitePassword = niceidMobilephoneSitePassword;          // NICE로부터 부여받은 사이트 패스워드

        //String sCipherTime = ""; // 복호화한 시간
        String sRequestNumber = ""; // 요청 번호
        String sMessage = "";
        String sPlainData = "";
        
        int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

        if( iReturn == 0 )
        {
            sPlainData = niceCheck.getPlainData();
            //sCipherTime = niceCheck.getCipherDateTime();
            
            // 데이타를 추출합니다.
            java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
            
            sRequestNumber  = (String)mapresult.get("REQ_SEQ");
            result.setSRequestNumber(sRequestNumber);
            result.setSResponseNumber((String)mapresult.get("RES_SEQ"));
            result.setSAuthType((String)mapresult.get("AUTH_TYPE"));
            result.setSName((String)mapresult.get("NAME"));
            //result.setSName((String)mapresult.get("UTF8_NAME")); //charset utf8 사용시 주석 해제 후 사용
            result.setSBirthDate((String)mapresult.get("BIRTHDATE"));
            result.setSGender((String)mapresult.get("GENDER"));
            result.setSNationalInfo((String)mapresult.get("NATIONALINFO"));
            result.setSDupInfo((String)mapresult.get("DI"));
            result.setSConnInfo((String)mapresult.get("CI"));
            result.setSMobileNo((String)mapresult.get("MOBILE_NO"));
            result.setSMobileCo((String)mapresult.get("MOBILE_CO"));
            
            String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
            if(!sRequestNumber.equals(session_sRequestNumber))
            {
                sMessage = "세션값 불일치 오류입니다.";
                result.setSResponseNumber("");
                result.setSAuthType("");
            }
        }
        else if( iReturn == -1)
        {
            sMessage = "복호화 시스템 오류입니다.";
        }    
        else if( iReturn == -4)
        {
            sMessage = "복호화 처리 오류입니다.";
        }    
        else if( iReturn == -5)
        {
            sMessage = "복호화 해쉬 오류입니다.";
        }    
        else if( iReturn == -6)
        {
            sMessage = "복호화 데이터 오류입니다.";
        }    
        else if( iReturn == -9)
        {
            sMessage = "입력 데이터 오류입니다.";
        }    
        else if( iReturn == -12)
        {
            sMessage = "사이트 패스워드 오류입니다.";
        }    
        else
        {
            sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
        }
        
        result.setSMessage(sMessage);
        
        return result;
    }
    
    /**
    * 본인인증 수행후 실패데이터 복호화.
    *
    * @Title : decodeIdntyVrfctnFailData
    * @Description : 본인인증 수행후 실패데이터 복호화
    * @param encodeData 본인인증결과(인코딩데이터)
    * @return IdntyVrfctnFailVo 본인인증 복호화 데이터
    * @throws Exception 예외
    */
    public IdntyVrfctnFailVo decodeIdntyVrfctnFailData(String encodeData) throws Exception {
        IdntyVrfctnFailVo result = new IdntyVrfctnFailVo();
        NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

        String sEncodeData = requestReplace(encodeData, "encodeData");

        String sSiteCode = niceidMobilephoneSiteCode; // NICE로부터 부여받은 사이트 코드
        String sSitePassword = niceidMobilephoneSitePassword; // NICE로부터 부여받은 사이트 패스워드

        //String sCipherTime = ""; // 복호화한 시간
        String sMessage = "";
        String sPlainData = "";
        
        int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

        if( iReturn == 0 )
        {
            sPlainData = niceCheck.getPlainData();
            //sCipherTime = niceCheck.getCipherDateTime();
            
            // 데이타를 추출합니다.
            java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
            
            result.setSRequestNumber((String)mapresult.get("REQ_SEQ"));
            result.setSErrorCode((String)mapresult.get("ERR_CODE"));
            result.setSAuthType((String)mapresult.get("AUTH_TYPE"));
        }
        else if( iReturn == -1)
        {
            sMessage = "복호화 시스템 에러입니다.";
        }    
        else if( iReturn == -4)
        {
            sMessage = "복호화 처리오류입니다.";
        }    
        else if( iReturn == -5)
        {
            sMessage = "복호화 해쉬 오류입니다.";
        }    
        else if( iReturn == -6)
        {
            sMessage = "복호화 데이터 오류입니다.";
        }    
        else if( iReturn == -9)
        {
            sMessage = "입력 데이터 오류입니다.";
        }    
        else if( iReturn == -12)
        {
            sMessage = "사이트 패스워드 오류입니다.";
        }    
        else
        {
            sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
        }
        
        result.setSMessage(sMessage);
        
        return result;
    }
}
