package com.kbrainc.plum.mng.member.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * sms발송VO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - SmsVo.java
 * </pre> 
 *
 * @ClassName : SmsVo
 * @Description : sms발송VO 클래스. 
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Data
public class SmsVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 핸드폰번호배열 */
    @NotEmpty(message = "발송대상을 선택 해주십시오.")
    private String[] mobnos;
    
    /** SMS내용 */
    @NotEmpty(message = "SMS내용을 입력해주십시오.")
    private String smsText;
    
    /** SMS발송타입 */
    private String smsSendType; // 1: 즉시, 2: 예약발송
    
    private String reserveDate; // yyyy-mm-dd hh:mm:ss 포맷
}