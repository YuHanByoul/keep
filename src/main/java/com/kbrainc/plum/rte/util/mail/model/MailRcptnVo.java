package com.kbrainc.plum.rte.util.mail.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 메일수신자정보 VO.
 *
 * <pre>
 * com.kbrainc.plum.rte.util.mail.model
 * - MailRcptnVo.java
 * </pre> 
 *
 * @ClassName : MailRcptnVo
 * @Description : 메일수신자정보 VO.
 * @author : KBRAINC
 * @date : 2021. 3. 12.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 * 
 */
@Data
@NoArgsConstructor
public class MailRcptnVo {

	private String rcptnEmail; // To
	private Integer rcptnUserid;   // 수신자 아이디
	
	public MailRcptnVo(String rcptnEmail, Integer rcptnUserid) {
	    this.rcptnEmail = rcptnEmail;
	    this.rcptnUserid = rcptnUserid;
	}
}
