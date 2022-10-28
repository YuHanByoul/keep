package com.kbrainc.plum.front.member.model;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 회원정보VO 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.model
* - MemberVo.java
* </pre> 
*
* @ClassName : MemberVo
* @Description : 회원정보Vo 클래스
* @author : KBRAINC
* @date : 2021. 4. 12.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
@Alias("front.MemberVo")
public class MemberVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 사용자아이디 */
    private Integer userid;
    
    /** 계정(사번) */
    private String acnt;
    
    /** 비밀번호 */
    private String pwd;
    
    /** 이름 */
    private String nm;
    
    /** 휴대폰 */
    private String mobNo;
    
    /** 이메일 */
    private String email;
    
    /** 약관 동의 여부*/ 
    private String tosAgreYn;
    
    /** 상태 코드 */
    private String sttsCd;
    
    /** 사용자 구분 코드 */
    private String userSeCd;
    
    /** 수정 일시  */
    private String updtDt;
    
    /** 수정자 아이디  */
    private Integer updtuserid;
    
    /** 등록 일시  */
    private String regDt;
    
    /** 등록자 아이디 */
    private Integer reguserid;
    
    /** 역할(권한) */
    private Integer roleid;
    
    /** 로그인사용자정보 */
    public void setUser(UserVo user){
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    public UserVo getUser(){
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }   

    
}