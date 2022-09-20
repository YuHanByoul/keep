package com.kbrainc.plum.mng.member.model;

import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 임시비밀번호발급VO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - TempPwdVo.java
 * </pre> 
 *
 * @ClassName : TempPwdVo
 * @Description : 임시비밀번호발급VO 클래스. 
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Data
public class TempPwdVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 사용자아이디 */
    private int userid;
    
    /** 이메일 */
    private String email;
    
    /** 발급방식 */
    private String method;
    
    /** 전송수단 */
    private String transType;
    
    /** 비밀번호 */
    private String pwd;
    
    /** 비밀번호확인 */
    private String chkpwd;
}