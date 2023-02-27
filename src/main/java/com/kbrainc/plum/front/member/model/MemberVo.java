package com.kbrainc.plum.front.member.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.cmm.esylgn.model.EsylgnVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 회원정보VO 클래스.
 *
 * <pre>
 * com.kbrainc.plum.front.member.model - MemberVo.java
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
    
    /** 회원가입 유형 */
    @NotEmpty(message = "회원가입 유형을 선택해주세요.")
    @Pattern(regexp="[PCI]", message = "회원가입 유형을 선택해주세요.")
    private String type;

    /** 이름 */
    @NotEmpty(message = "이름을 입력 해주십시오.")
    @Size(max = 20, message = "이름은 20자를 초과할 수 없습니다.")
    private String nm;

    /** 계정 */
    private String acnt;

    /** 비밀번호 */
    private String pswd;
    
    /** 휴대폰번호 */
    @Size(max = 12, message = "휴대전화는 12자를 초과할 수 없습니다.")
    private String moblphon;

    /** 이메일 */
    @NotEmpty(message = "이메일을 입력 해주십시오.")
    @Size(max = 101, message = "이메일은 101자를 초과할 수 없습니다.")
    @Email(message = "이메일 형식이 올바르지않습니다.")
    private String eml;

    /** 이메일1 */
    @NotEmpty(message = "이메일을 입력 해주십시오.")
    @Size(max = 50, message = "이메일 아이디는 50자를 초과할 수 없습니다.")
    private String eml1;

    /** 이메일2 */
    @NotEmpty(message = "이메일을 입력 해주십시오.")
    @Size(max = 50, message = "이메일 도메인은 50자를 초과할 수 없습니다.")
    private String eml2;

    /** 우편번호 */
    @NotEmpty(message = "우편번호를 입력 해주십시오.")
    @Size(max = 10, message = "우편번호는 10자를 초과할 수 없습니다.")
    private String zip;

    /** 주소 */
    @NotEmpty(message = "주소를 입력 해주십시오.")
    @Size(max = 200, message = "주소는 200자를 초과할 수 없습니다.")
    private String addr;

    /** 상세주소 */
    @NotEmpty(message = "상세주소를 입력 해주십시오.")
    @Size(max = 400, message = "상세주소는 400자를 초과할 수 없습니다.")
    private String addrDtl;

    /** 시군구코드 */
    @NotEmpty(message = "시군구코드를 입력 해주십시오.")
    @Size(min = 5, max = 5, message = "시군구코드 형식이 올바르지 않습니다.")
    private String signguCd;

    /** 기관아이디 */
    private Integer instid = null;

    /** 기관 역할 코드 */
    private String instpicRoleCd = null;

    /** CI */
    private String ci;

    /** 부모CI */
    private String ciParnts;

    /** 이용약관 동의 여부 */
    @NotEmpty(message = "필수항목을 동의해야 회원가입이 가능합니다.")
    @Pattern(regexp = "[Y]", message = "필수항목을 동의해야 회원가입이 가능합니다.")
    private String tosAgreYn;

    /** 개인정보수집및이용 동의 여부 */
    @NotEmpty(message = "필수항목을 동의해야 회원가입이 가능합니다.")
    @Pattern(regexp = "[Y]", message = "필수항목을 동의해야 회원가입이 가능합니다.")
    private String privcyAgreYn;

    /** 개인정보 제3자 제공 동의 여부 */
    @NotEmpty(message = "필수항목을 동의해야 회원가입이 가능합니다.")
    @Pattern(regexp = "[Y]", message = "필수항목을 동의해야 회원가입이 가능합니다.")
    private String prvcThptyPvsnAgreYn;

    /** 어린이 가입 동의 여부 */
    private String childJoinAgreYn;

    /** 웹진 신청 동의 여부 */
    @Pattern(regexp = "[YN]", message = "올바른 값을 입력해주세요.")
    private String wbznAplyAgreYn = "N";

    /** 관심분야 코드 저장용 */
    private String[] itrstfldCds;

    /** 환경분야 코드 저장용 */
    private String[] envfldCds;
    
    /** 개인정보 유효기간 */
    private int prvcVldty;
    
    /** 탈퇴사유 */
    @Size(max = 1000, message = "탈퇴사유는 1000자를 초과할 수 없습니다.")
    private String secsnRsn ;

    /** 수정 일시 */
    private String mdfcnDt;

    /** 수정자 아이디 */
    private Integer mdfrid;

    /** 등록 일시 */
    private String regDt;

    /** 등록자 아이디 */
    private Integer rgtrid;

    /** 본인인증 결과 인코딩데이터(법정대리인) */
    private String pencodeData;
    
    /** 본인인증 결과 인코딩데이터 */
    private String encodeData;
    
    /** 디지털원패스 사용자정보 인코딩데이터 */
    private String onepassEncodeData;

    /** 간편로그인Vo 객체 */
    private EsylgnVo esylgnVo;
    
    /** 파라미터로 받고 로그인 버튼 클릭시 파라미터로 넘길 URL */
    private String returnUrl;
    
    /** 로그인사용자정보 */
    public void setUser(UserVo user) {
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }

    public UserVo getUser() {
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return clone;
    }

    public void setEml2(String eml2) {
        this.eml2 = eml2;
        this.eml = this.eml1 + "@" + this.eml2;
    }
    
    public void setEml(String eml) {
        if (eml != null) {
            String[] emls = eml.split("@");
            this.eml1 = emls[0];
            this.eml2 = emls[1];
        }
    }
}