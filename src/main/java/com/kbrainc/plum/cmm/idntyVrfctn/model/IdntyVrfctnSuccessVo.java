package com.kbrainc.plum.cmm.idntyVrfctn.model;

import lombok.Data;

/**
* 휴대폰본인인증 success VO 클래스.
*
* <pre>
* com.kbrainc.plum.cmm.idntyVrfctn.model
* - IdntyVrfctnSuccessVo.java
* </pre> 
*
* @ClassName : IdntyVrfctnSuccessVo
* @Description : 휴대폰본인인증 success VO 클래스
* @author : KBRAINC
* @date : 2023. 2. 7.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
public class IdntyVrfctnSuccessVo {
    
    /** 요청 번호 */
    private String sRequestNumber;
    
    /** 인증 고유번호 */
    private String sResponseNumber;
    
    /** 인증 수단 */
    private String sAuthType;
    
    /** 성명 */
    private String sName;
    
    /** 생년월일(YYYYMMDD) */
    private String sBirthDate;
    
    /** 성별 */
    private String sGender;
    
    /** 내/외국인정보 (개발가이드 참조) */
    private String sNationalInfo;
    
    /** 중복가입 확인값 (DI_64 byte) */
    private String sDupInfo;
    
    /** 연계정보 확인값 (CI_88 byte) */
    private String sConnInfo;
    
    /** 휴대폰번호 */
    private String sMobileNo;
    
    /** 통신사 */
    private String sMobileCo;
    
    /** 실패메시지 */
    private String sMessage; 
}