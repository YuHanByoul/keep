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
	
	/** 사용자_구분_코드 */
	private String user_se_cd;

	/** 사용자_구분_명 */
	private String user_se_nm;
	
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
	private String file_nm;
	
	/** 파일사이즈 */
	private String file_size;
	
	/** 개인정보취급_동의_여부 */
	private String priplcy_agre_yn;
	
	/** 문의_분류_코드 */
	private String inqry_cl_cd;
	
	/** 문의_분류_명 */
	private String inqry_cl_nm;
	
	/** 등록_일 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date reg_d;
	
	/** 답변_일 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date answr_d;
	
	/** 처리자_이름 */
	private String opetr_nm;
	
	/** 문의일자 시작일 */
	private String searchStartDay;
	
	/** 문의일자 종료일 */
	private String searchEndDay;
	
	/** 답변여부 */
	private String searchAnswrYn;
	
	/** 수정_일시 */
	private Date updt_dt;
	
	/** 수정자아이디 */
	private Integer updtuserid;
	
	/** 등록_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Date reg_dt;
	
	/** 등록자아이디 */
	private Integer reguserid;
	
	public void setUser_se_cd(String cd) {
		this.user_se_cd = cd;
		if("P".equals(cd)) {
			this.setUser_se_nm("개인회원");
		} else if("C".equals(cd)) {
			this.setUser_se_nm("기업회원");
		} else {
			this.setUser_se_nm("미분류 회원");
		}
	}
}