package com.kbrainc.plum.rte.util.mail.model;


import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * 
 * MailDao.
 *
 * <pre>
 * com.kbrainc.plum.rte.util.mail.model
 * - MailDao.java
 * </pre> 
 *
 * @ClassName : MailDao
 * @Description : 메일 발송 관련 DAO
 * @author : KBRAINC
 * @date : 2021. 3. 3.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Component
@Mapper
public interface MailDao {
	
	/**
	 * 
	 * 메일 발송 히스토리 저장. 
	 *
	 * @Title : insertMailHist
	 * @Description : 메일 발송 히스토리 저장.
	 * @param mailVo
	 * @return int
	 */
	public int insertMailHist(MailVo mailVo);
	
}
