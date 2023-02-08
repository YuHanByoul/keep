package com.kbrainc.plum.front.member.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
    
    /** 계정 */
    private String acnt;
    
    /** 비밀번호 */
    private String pswd;
    
    /** 이름 */
    private String nm;
    
    /** 휴대폰번호 */
    private String moblphon;
    
    /** 이메일 */
    private String email;
    
    /** 우편번호 */
    private String zip;
    
    /** 주소 */
    private String addr;
    
    /** 상세주소 */
    private String addrDtl;
    
    /** 시군구코드 */
    private String signguCd;
    
    /** 기관아이디 */
    private int instid;
        
    /** CI */
    private String ci;
    
    /** 부모CI */
    private String ciParnts;
    
    /** 이용약관 동의 여부 */
    private String tosAgreYn;
    
    /** 개인정보수집및이용 동의 여부 */
    private String privcyAgreYn;
    
    /** 개인정보 제3자 제공 동의 여부 */
    private String prvcThptyPvsnAgreYn;
    
    /** 어린이 가입 동의 여부 */
    private String childJoinAgreYn;
    
    /** 웹진 신청 동의 여부 */
    private String wbznAplyAgreYn = "N";
    
    /** 상태 코드 */
    private String sttsCd;
    
    /** 수정 일시  */
    private String mdfcnDt;
    
    /** 수정자 아이디  */
    private Integer mdfrid;
    
    /** 등록 일시  */
    private String regDt;
    
    /** 등록자 아이디 */
    private Integer rgtrid;
    
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