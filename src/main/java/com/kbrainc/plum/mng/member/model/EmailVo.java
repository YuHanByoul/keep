package com.kbrainc.plum.mng.member.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;

import lombok.Data;

/**
 * 
 * 이메일발송VO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - EmailVo.java
 * </pre> 
 *
 * @ClassName : EmailVo
 * @Description : 이메일발송VO 클래스. 
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Data
public class EmailVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 이메일주소배열 */
    @NotEmpty(message = "발송대상을 선택 해주십시오.")
    private List<MailRcptnVo> emails;
    
    /** 이메일제목 */
    @NotEmpty(message = "이메일제목을 입력해주십시오.")
    @Size(max = 500, message = "이메일제목은 500자를 넘을 수 없습니다.")
    private String emailTitle;
    
    /** 이메일내용 */
    @NotEmpty(message = "이메일내용을 입력해주십시오.")
    @Size(max = 4000, message = "이메일내용은 4000자를 넘을 수 없습니다.")
    private String emailContent;
}