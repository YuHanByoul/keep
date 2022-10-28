package com.kbrainc.plum.mng.member.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 사용자VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - MemberVo.java
 * </pre> 
 *
 * @ClassName : MemberVo
 * @Description : 사용자VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.member.model
* - MemberVo.java
* </pre>
*
* @ClassName   : MemberVo 
* @Description : TODO 
* @author      : Zeniel
* @date        : 2021. 3. 11.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class MemberVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    

    /** 사용자 아이디 */
    private int userid;
    
    /** 계정 */
    @Size(max = 20, message = "아이디는 20자를 넘을 수 없습니다.")
    private String acnt;
    
    /** 패스워드 */
    @Size(min = 8, max = 20, message = "비밀번호는 8자이상 20자 이하여야합니다.")
    private String pwd;
    
    /** 사용자_구분 */
    private String userSeCd;
    
    /** 사용자_이름 */
    @NotEmpty(message = "이름을 입력해주십시오.")
    @Size(max = 20, message = "이름은 20자를 넘을 수 없습니다.")
    private String nm;
    
    /** 휴대폰번호 */
    private String mobno;
    
    /** 이메일 */
    @NotEmpty(message = "이메일을 입력해주십시오.")
    @Size(max = 100, message = "이메일은 100자를 넘을 수 없습니다.")
    @Email(message = "이메일 형식이 올바르지않습니다.")
    private String email;
    
    /** 이메일1 */
    @NotEmpty(message = "이메일을 입력해주십시오.")
    @Size(max = 50, message = "이메일은 50자를 넘을 수 없습니다.")
    private String email1; 
    
    /** 이메일2 */
    @NotEmpty(message = "이메일을 입력해주십시오.")
    private String email2;
    
    /** 이용약관_동의_여부 */
    @Pattern(regexp="[YN]")
    private String tosAgreYn;
    
    /** 개인정보수집및이용_동의_여부 */
    @Pattern(regexp="[YN]")
    private String prvcyAgreYn;
    
    /** 개인정보_유효기간 */
    private String prvcyVldty; 
    
    /** 개인정보_유효일자 */
    private Date prvcyVldtyDt;
    
    /** 계정_잠김_여부 */
    @Pattern(regexp="[YN]")
    private String acntLockYn;
    
    /** 계정_잠김_코드 */
    private String acntLockCd;
    
    /** 상태_코드 */
    private String sttsCd;
    
    /** 삭제_여부 */
    @Pattern(regexp="[YN]")
    private String delYn; 
    
    /** 최종접속일 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date loginDt;
    
    /** 휴면계정전환일 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date drmncyDt;
    
    /** 계정삭제예정일 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
    private Date expectedDelDt;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자아이디 */
    private int reguserid;
    
    /** 가입기간 시작일 */
    private String searchStartDay;
    
    /** 가입기간 종료일 */
    private String searchEndDay;
    
    /** 상태(회원,탈퇴회원) */
    private String[] searchDelYn;
    
	/*dtl 컬럼추가	*/
    /** 생년월일 */
    private String brthdy;
    
    /** 성별 */
    private String sex;
    
    /** 주소 */
    @Size(max = 200, message = "주소는 200자를 넘을 수 없습니다.")
    private String addr;
    
    /** 주소_상세 */
    @Size(max = 400, message = "상세주소는 400자를 넘을 수 없습니다.")
    private String addrDtl;
    
    /** 소개 */
    @Size(max = 50, message = "소개는 50자를 넘을 수 없습니다.")
    private String intrcn;
    
    public void setRegDt(Date regDt) {
        this.regDt = regDt != null ? (Date) regDt.clone() : null;
    }
    
    public void setLoginDt(Date loginDt) {
        this.loginDt = loginDt != null ? (Date) loginDt.clone() : null;
    }
    
    public void setDrmncyDt(Date drmncyDt) {
        this.drmncyDt = drmncyDt != null ? (Date) drmncyDt.clone() : null;
    }
    
    public void setPrvcyVldtyDt(Date prvcyVldtyDt) {
        this.prvcyVldtyDt = prvcyVldtyDt != null ? (Date) prvcyVldtyDt.clone() : null;
    }
    
    public void setExpectedDelDt(Date expectedDelDt) {
        this.expectedDelDt = expectedDelDt != null ? (Date) expectedDelDt.clone() : null;
    }
    
    public Date getRegDt() {
        return regDt != null ? (Date) regDt.clone() : null;
    }

    public Date getLoginDt() {
        return loginDt != null ? (Date) loginDt.clone() : null;
    }
    
    public Date getDrmncyDt() {
        return drmncyDt != null ? (Date) drmncyDt.clone() : null;
    }
    public Date getPrvcyVldtyDt() {
        return prvcyVldtyDt != null ? (Date) prvcyVldtyDt.clone() : null;
    }
    public Date getExpectedDelDt() {
        return expectedDelDt != null ? (Date) expectedDelDt.clone() : null;
    }

    
}