package com.kbrainc.plum.rte.util.mail.model;

import java.util.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 메일발송 내역 VO.
 *
 * <pre>
 * com.kbrainc.plum.rte.util.mail.model
 * - MailVo.java
 * </pre> 
 *
 * @ClassName : MailVo
 * @Description : 메일발송에 대한 VO.
 * @author : KBRAINC
 * @date : 2021. 3. 12.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 * 
 */
@Data
@NoArgsConstructor
public class MailVo extends ParentRequestVo {

	private int mailSndngHistId;
	private String sndngEmail; // From
	private String rcptnEmail; // To
	private String title;
	private String cntnts;
	private int sndngUserid;	// 발송자 아이디(발송구분이 시스템과 스케줄 잡이면 null로 입력)
	private String sndngUserNm;
	private String sndngSeCd;	// 발송_구분_코드(U: 사용자, S: 시스템, J: 스케줄 잡)
	private int rcptnUserid;	// 수신자 아이디
	private String rcptnCnfirmYn;
	private String rcptnCnfirmDt;
	private String sndngSttsCd;	// 발송_상태_코드(R: 대기, S: 성공, F: 실패)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	private Date regDt;
	
	/**
	 * 
	 * MailVo생성자. 
	 *
	 * @Title : MaiLVo
	 * @Description : MailVo생성자. 
	 * @param sndngEmail 발송자 이메일
	 * @param rcptnEmail 수신자 이메일
	 * @param title	제목
	 * @param cntnts 내용
	 * @param sndngUserid 보내는사람 ID
	 * @param sndngSeCd 발송 구분 코드(U: 사용자, S: 시스템, J: 스케줄 잡)
	 * @param rcptnUserid 받는사람 ID
	 * @return void
	 */
	public MailVo(
			String sndngEmail, 
			String rcptnEmail, 
			String title, 
			String cntnts, 
			int sndngUserid, 
			String sndngSeCd,
			int rcptnUserid
	) {
		this.sndngEmail = sndngEmail;
		this.rcptnEmail = rcptnEmail;
		this.title = title;
		this.cntnts = cntnts;
		this.sndngUserid = sndngUserid;
		this.sndngSeCd = sndngSeCd;
		this.rcptnUserid = rcptnUserid;		
	}

}
