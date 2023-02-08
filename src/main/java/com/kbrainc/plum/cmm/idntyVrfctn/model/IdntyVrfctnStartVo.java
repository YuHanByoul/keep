package com.kbrainc.plum.cmm.idntyVrfctn.model;

import lombok.Data;

/**
* 휴대폰본인인증 업체정보 인증결과 VO 클래스.
*
* <pre>
* com.kbrainc.plum.cmm.idntyVrfctn.model
* - IdntyVrfctnStartVo.java
* </pre> 
*
* @ClassName : IdntyVrfctnStartVo
* @Description : 휴대폰본인인증 업체정보 인증결과 VO 클래스
* @author : KBRAINC
* @date : 2023. 2. 7.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
public class IdntyVrfctnStartVo {
    
    /** 본인인증 연동정보(업체정보를 암호화 한 데이타) */
    private String sEncData;
    
    /** 실패메시지 */
    private String sMessage;
}