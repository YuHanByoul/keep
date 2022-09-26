package com.kbrainc.plum.mng.member.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 개인상세VO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - MemberDtlVo.java
 * </pre> 
 *
 * @ClassName : MemberDtlVo
 * @Description : 개인상세VO 클래스. 
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class MemberDtlVo {

    /** 로그인사용자정보 */
    private UserVo user;

    /** 사용자 아이디 */
    private int userid;  
    
    /** 생년월일 */
    private String brthdy;  
    
    /** 생년월일_년 */
    private String brthdyYear; 
    
    /** 생년월일_월 */
    private String brthdyMonth; 
    
    /** 생년월일_일 */
    private String brthdyDay; 
    
    /** 생년월일_양음력 */
    private String moon_yn;
    
    /** 성별 */
    private String sex;
    
    /** 우편번호 */
    private String zip;
    
    /** 주소 */
    @Size(max = 200, message = "주소는 200자를 넘을 수 없습니다.")
    private String addr;
    
    /** 주소_상세 */
    @Size(max = 400, message = "상세주소는 400자를 넘을 수 없습니다.")
    private String addr_dtl;
    
    /** 지역_코드 */
    private String area_cd;
    
    /** 마케팅_이메일_동의_여부 */
    @Pattern(regexp="[YN]")
    private String markt_email_agre_yn;
    
    /** 마케팅_SMS_동의_여부 */
    @Pattern(regexp="[YN]")
    private String markt_sms_agre_yn;
    
    /** 소개 */
    @Size(max = 50, message = "소개는 50자를 넘을 수 없습니다.")
    private String intrcn;
    
    /** 프로필사진_파일그룹아이디 */
    private int pphoto_filegrpid;
    
    /** 프로필사진_파일아이디 */
    private int pphoto_fileid;
    
    /** 프로필사진_파일_식별_키 */
    private String pphoto_file_idntfc_key;
        
    /** 수정_일시 */
    private String updt_dt;
    
    /** 수정자아이디 */
    private int updtuserid;
    
    /** 등록_일시 */
    private String reg_dt;
    
    /** 등록자아이디 */
    private int reguserid;
}