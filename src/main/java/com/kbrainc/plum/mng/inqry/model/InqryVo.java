package com.kbrainc.plum.mng.inqry.model;

import java.util.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.kbrainc.plum.rte.model.UserVo;
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

	/** 로그인 사용자 정보*/
	private UserVo user;

	/*본인 처리 여부*/
	private String assignYn;

	/** 문의아이디 */
	private Integer inqryid;
	
	/** 제목 */
	private String title;
	
	/** 내용 */
	private String cntnts;
	
	/** 작성자명 */
	private String nm;

	/** 작성자 아이디명 */
	private String acnt;

	/** 작성자 아이디 일련번호 */
	private Integer userid;

	/** 파일그룹 아이디 */
	private Integer filegrpid;
	
	/** 파일 아이디 */
	private Integer fileid;
	
	/** 파일 이름 */
	private String fileNm;
	
	/** 파일 사이즈 */
	private String fileSize;

	/** 문의 분류 코드 */
	private String inqryClCd;
	
	/** 문의_분류 명 */
	private String inqryClNm;

	/** 처리자 이름 */
	private String opetrNm;

	/** 기관명 */
	private String instNm;

	/** 등록일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date regDt;
	
	/** 답변 상태 코드 */
	private String inqrySttsCd;

    public void setRegDt(Date regDt) {
        this.regDt = regDt != null ? (Date) regDt.clone() : null;
    }

    public Date getRegDt() {
        return regDt != null ? (Date) regDt.clone() : null;
    }

}