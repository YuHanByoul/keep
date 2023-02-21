package com.kbrainc.plum.front.member.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Data;

/**
 * 회원정보 계정과 비밀번호만을 멤버로 가지는 Vo 클래스(유효성검증전용)
 *
 * <pre>
 * com.kbrainc.plum.front.member.model - MemberAcntPswdVo.java
 * </pre>
 *
 * @ClassName : MemberAcntPswdVo
 * @Description : 회원정보 계정과 비밀번호만을 멤버로 가지는 Vo 클래스(유효성검증전용)
 * @author : KBRAINC
 * @date : 2023. 2. 20.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class MemberAcntPswdVo extends ParentRequestVo {

    /** 계정 */
    @NotEmpty(message = "아이디를 입력 해주십시오.")
    @Size(min = 6, max = 10, message = "아이디 형식이 올바르지 않습니다.")
    private String acnt;

    /** 비밀번호 */
    @NotEmpty(message = "비밀번호를 입력 해주십시오.")
    @Size(min = 9, max = 12, message = "비밀번호 조건이 일치하지 않습니다.")
    private String pswd;
}