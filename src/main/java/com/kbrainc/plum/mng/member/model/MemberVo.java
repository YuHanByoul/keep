package com.kbrainc.plum.mng.member.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

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
* @author      : KBRAINC
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
    private String pswd;
    
    /** 사용자_구분 */
    private String userSeCd;
    
    /** 사용자_이름 */
    @NotEmpty(message = "이름을 입력해주십시오.")
    @Size(max = 20, message = "이름은 20자를 넘을 수 없습니다.")
    private String nm;
    
    /** 닉네임 */
    @Size(max = 20, message = "닉네임은 20자를 넘을 수 없습니다.")
    private String ncnm;
    
    /** 휴대폰번호 */
    @Size(max = 40, message = "휴대폰번호는 40자를 넘을 수 없습니다.")
    private String moblphon;
    
    /** 전화번호 */
    @Size(max = 40, message = "전화번호는 40자를 넘을 수 없습니다.")
    private String telno;
    
    /** 이메일 */
    //@NotEmpty(message = "이메일을 입력해주십시오.")
    @Size(max = 100, message = "이메일은 200자를 넘을 수 없습니다.")
    @Email(message = "이메일 형식이 올바르지않습니다.")
    private String eml;
    
    /** 이메일1 */
    @Size(max = 50, message = "이메일은 50자를 넘을 수 없습니다.")
    private String email1; 
    
    /** 이메일2 */
    private String email2;

    /** 생년월일 */
    @Size(max = 10, message = "생년월일은 40자를 넘을 수 없습니다.")
    private String brdt;
    
    /** 성별 */
    private String gndr;
    
    /** 우편번호 */
    @Size(max = 10, message = "우편번호는 10자를 넘을 수 없습니다.")
    private String zip;
    
    /** 주소 */
    @Size(max = 200, message = "주소는 200자를 넘을 수 없습니다.")
    private String addr;
    
    /** 주소_상세 */
    @Size(max = 400, message = "상세주소는 400자를 넘을 수 없습니다.")
    private String addrDtl;
    
    /** 기관 아이디 */
    private Integer instid;
    
    /** 기관 역할 코드 */
    @Size(max = 20, message = "기관코드는 20자를 넘을 수 없습니다.")
    private String instpicRoleCd;
    
    /** 기관 역할 코드 명*/
    private String instpicRoleCdNm;
    
    /** 이용약관_동의_여부 */
    @Pattern(regexp="[YN]")
    private String tosAgreYn;
    
    /** 개인정보수집및이용_동의_여부 */
    @Pattern(regexp="[YN]")
    private String privcyAgreYn;
    
    /** 마케팅_이메일_동의_여부 */
    @Pattern(regexp="[YN]")
    private String marktEmlAgreYn;
    
    /** 마케팅_이메일_동의_여부 */
    @Pattern(regexp="[YN]")
    private String marktSmsAgreYn;
    
    /** 개인정보_유효기간 */
    private String prvcyVldty; 
    
    /** 개인정보_유효일자 */
    private Date prvcyVldtyDt;
    
    /** 계정_잠김_여부 */
    @Pattern(regexp="[YN]")
    private String acntLockYn;
    
    /** 계정_잠김_코드 */
    private String acntLockCd;
    
    /** 임시_비밀번호_여부 */
    @Pattern(regexp="[YN]")
    private String tmprPswdYn;
    
    /** 비밀번호_수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date pswdMdfcnDt;
    
    /** 상태_코드 */
    private String sttsCd;
    
    /** 삭제_여부 */
    @Pattern(regexp="[YN]")
    private String delYn;
    
    /**  블랙리스트_여부 */
    @Pattern(regexp="[YN]")
    private String blcklstYn;
    
    /** 최종접속일 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date lgnDt;
    
    /** 접속 실패 횟수  */
    private Integer lgnFailCnt;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;
    
    /** 회원 역할 아이디 */
    private Integer roleid;
    
    /** 역할 아이디모음  */
    private String roleids;
    
    /** 역할 코드  */
    private String rolecd;
    
    /** 관심분야 코드  */
    private String itrstfldCd;
    
    /** 관심분야 코드 저장용 */
    private String [] itrstfldCds;
    
    /** 환경분야 코드  */
    private String envfldCd;
    
    /** 환경분야 코드  저장용*/
    private String [] envfldCds;
    
    /** 간편로그인 코드  */
    private String esylgnCd;
    
    /** 간편로그인 코드  저장용*/
    private String [] esylgnCds;
    
    /** 간편로그인 코드  저장용*/
    private String [] userids;
    
    /** 회원 검색용 파라메터 추가 *****/
    /** 상태코드 검색용 */
    private String searchSttsCd;
    
    /** 회원 유형 검색용 */
    private String searchRoleCd;
    
    /** 회원 검색 시작일자(가입일 기준) */
    private String searchStartDay;
    
    /** 회원 검색 종료일자(가입일 기준) */
    private String searchEndDay;
    
    public void setInstpicRoleCd(String instpicRoleCd) throws Exception{
        this.instpicRoleCd = instpicRoleCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.instpicRoleCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.instpicRoleCd);
                this.instpicRoleCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
             }catch(Exception e) {
                //e.printStackTrace();
                return ;
             }
        }
    }
    
    
}