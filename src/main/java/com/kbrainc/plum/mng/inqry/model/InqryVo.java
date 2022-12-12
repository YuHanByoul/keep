package com.kbrainc.plum.mng.inqry.model;

import java.util.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 
 * 1:1문의VO 클래스 
 *
 * <pre>
 * com.kbrainc.plum.mng.inqry.model
 * - InqryVo.java
 * </pre> 
 *
 * @ClassName : InqryVo
 * @Description : 1:1문의VO 클래스 
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class InqryVo extends ParentRequestVo {
	
	/** 코드그룹 아이디 */
	private String cdgrpid = "102";
	
	/** 문의아이디 */
	private Integer inqryid;
	
	/** 제목 */
	private String title;
	
	/** 내용 */
	private String cntnts;
	
	/** 사용자아이디 */
	private Integer userid;
	
	/** 사용자_이름 */
	private String nm;

	/** 사용자 아이디 */
	private String acnt;
	
	/** 사용자_구분_코드 */
	private String userSeCd;

	/** 사용자_구분_명 */
	private String userSeNm;
	
	/** 이메일 */
	private String email;
	
	/** 핸드폰번호 */
	private String mobno;
	
	/** 전화번호 */
	private String telno;
	
	/** 파일그룹아이디 */
	private Integer filegrpid;
	
	/** 파일아이디 */
	private Integer fileid;
	
	/** 파일이름 */
	private String fileNm;
	
	/** 파일사이즈 */
	private String fileSize;
	
	/** 개인정보취급_동의_여부 */
	private String priplcyAgreYn;
	
	/** 문의_분류_코드 */
	private String inqryClCd;
	
	/** 문의_분류_명 */
	private String inqryClNm;

	/** 등록_일 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date regD;
	
	/** 답변_일 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date answrD;
	
	/** 처리자_이름 */
	private String opetrNm;
	
	/** 문의일자 시작일 */
	private String searchStartDay;
	
	/** 문의일자 종료일 */
	private String searchEndDay;
	
	/** 답변여부 */
	private String searchAnswrYn;
	
	/** 수정_일시 */
	private Date mdfcnDt;
	
	/** 수정자아이디 */
	private Integer mdfrid;

	/** 기관명 */
	private String instNm;

	/** 등록_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Date regDt;
	
	/** 등록자아이디 */
	private Integer rgtrid;

	/** 답변 상태코드 */
	private String inqrySttsCd;

	public void setUserSeCd(String cd) {
		this.userSeCd = cd;
		if("P".equals(cd)) {
			this.setUserSeNm("개인회원");
		} else if("C".equals(cd)) {
			this.setUserSeNm("기업회원");
		} else {
			this.setUserSeNm("미분류 회원");
		}
	}
	
    public void setRegDt(Date regDt) {
        this.regDt = regDt != null ? (Date) regDt.clone() : null;
    }
    
    public void setmdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    public void setRegD(Date regD) {
        this.regD = regD != null ? (Date) regD.clone() : null;
    }
    public void setAnswrD(Date answrD) {
        this.answrD = answrD != null ? (Date) answrD.clone() : null;
    }

    
    public Date getRegDt() {
        return regDt != null ? (Date) regDt.clone() : null;
    }

    public Date getmdfcnDt() {
        return mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    public Date getRegD() {
        return regD != null ? (Date) regD.clone() : null;
    }
    
    public Date getAnswrD() {
        return answrD != null ? (Date) answrD.clone() : null;
    }


}