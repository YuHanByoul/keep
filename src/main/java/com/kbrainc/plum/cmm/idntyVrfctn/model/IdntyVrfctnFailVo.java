package com.kbrainc.plum.cmm.idntyVrfctn.model;

import lombok.Data;

/**
* 휴대폰본인인증 fail VO 클래스.
*
* <pre>
* com.kbrainc.plum.cmm.idntyVrfctn.model
* - IdntyVrfctnFailVo.java
* </pre> 
*
* @ClassName : IdntyVrfctnFailVo
* @Description : 휴대폰본인인증 fail VO 클래스
* @author : KBRAINC
* @date : 2023. 2. 7.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
public class IdntyVrfctnFailVo {
    
    /** 요청 번호 */
    private String sRequestNumber;
    
    /** 에러 코드 */
    private String sErrorCode;
    
    /** 인증 수단 */
    private String sAuthType;
    
    /** 실패메시지 */
    private String sMessage;
}