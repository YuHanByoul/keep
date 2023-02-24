package com.kbrainc.plum.front.member.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 회원 계정/비밀번호 찾기VO 클래스.
 *
 * <pre>
 * com.kbrainc.plum.front.member.model - MemberAcntPswdFindVo.java
 * </pre>
 *
 * @ClassName : MemberAcntPswdFindVo
 * @Description : 회원 계정/비밀번호 찾기VO 클래스
 * @author : KBRAINC
 * @date : 2023. 2. 22.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class MemberAcntPswdFindVo {

    /** 사용자아이디 */
    private Integer userid;
    
    /** 계정 */
    @NotEmpty(message = "회원 아이디를 입력 해주십시오.")
    @Size(max = 50, message = "회원 아이디는 50자를 초과할 수 없습니다.")
    private String acnt;

    /** 비밀번호 */
    @NotEmpty(message = "변경 비밀번호를 입력 해주십시오.")
    @Size(min = 9, max = 12, message = "변경 비밀번호 조건이 일치하지 않습니다.")
    private String pswd;
    
    /** 비밀번호 확인 */
    private String pswdConfirm;

    /** 등록 일시 */
    private Date regDt;
    
    /** 본인인증 결과 인코딩데이터 */
    private String encodeData;
    
    /** CI */
    private String ci;
    
    /** 파라미터로 받고 로그인 버튼 클릭시 파라미터로 넘길 URL */
    private String returnUrl;
    
    /** 화면에 띄울 alert메시지 */
    private String alertMsg;
}