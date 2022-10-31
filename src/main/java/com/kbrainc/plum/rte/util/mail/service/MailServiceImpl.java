package com.kbrainc.plum.rte.util.mail.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.mail.model.MailDao;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 자체 메일 발송 서비스.
 *
 * <pre>
 * com.kbrainc.plum.rte.util.mail.service
 * - MailServiceImpl.java
 * </pre> 
 *
 * @ClassName : MailServiceImpl
 * @Description : 자체 메일 발송 서비스
 * @author : KBRAINC
 * @date : 2021. 3. 3.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service("MailService")
@Slf4j
public class MailServiceImpl extends PlumAbstractServiceImpl implements MailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
	
	private static final String MAIL_STATUS_SUCCESS = "S"; // 발송 성공
	/*private static final String MAIL_STATUS_READY = "R"; // 발송 성공*/
	private static final String MAIL_STATUS_FAIL = "F";	// 발송 실패
	private static final String MAIL_ERROR_NOT_EXIST_ADDRESS = "9001"; // 주소를 입력하지 않을 경우
	private static final String MAIL_ERROR_CONTENTS = "9002"; // 메시지에 문제가 있을 경우
	private static final String MAIL_ERROR_UNKNOWN = "9999"; // 알수 없는 오류

	@Autowired
	MailAuthentication mailAuth;
	
	@Autowired
	MailDao mailDao;
	
    @Value("${mail.fromName}")
    String fromName;
    @Value("${mail.username}")
    String mailId;
    @Value("${mail.password}") 
    String mailPassword;
    @Value("${mail.smtp.starttls.enable}")
    String  mailStarttls;
    @Value("${mail.smtp.host}")
    String  mailHost;
    @Value("${mail.smtp.port}")
    String  mailPort;
    @Value("${mail.smtp.auth}")
    String  isMailAuth;
    @Value("${mail.charSet}")
    String  mailCharSet;
     
    /**
     * 
     * 메일 발송. 
     *
     * @Title : MailSending
     * @Description : 메일을 보낸다.
     * @param mailVo
     * @return
     * @throws Exception
     * @return Map<String,String>
     */
    @Override
    public Map<String, Object> sendMail(MailVo mailVo) throws Exception{
    	
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", mailStarttls );    
        props.put("mail.smtp.host", mailHost);      // smtp 서버 호스트
        props.put("mail.smtp.auth", isMailAuth);
        props.put("mail.smtp.port", mailPort);      // gmail 포트
        
        if (CommonUtil.isEmpty(mailVo.getSndngEmail())) {
            mailVo.setSndngEmail(mailId);
        }
        
        mailAuth.setPropeties(mailId, mailPassword);
        
        //session 생성 및  MimeMessage생성
        Session session = Session.getInstance(props, mailAuth);
        MimeMessage msg = new MimeMessage(session);
        InternetAddress from = new InternetAddress() ;
         
    	msg.setHeader("content-Type", "text/html");
        // 편지보낸시간 설정
        msg.setSentDate(new Date());
        
        // 송신자 설정
        //InternetAddress from = new InternetAddress() ;
        from = new InternetAddress(new String(fromName.getBytes(mailCharSet), "8859_1"));

        // 발신자 설정
        msg.setFrom(from);
        
        // 수신자 설정
        InternetAddress to = new InternetAddress(mailVo.getRcptnEmail());
        msg.setRecipient(Message.RecipientType.TO, to);
        
        // 제목 설정
        msg.setSubject(mailVo.getTitle(), mailCharSet);
        
        //내용 html로 설정
        msg.setContent(mailVo.getCntnts(), "text/html; charset=UTF-8");
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        try{
        	            
            // 메일 송신
            Transport.send(msg);
            
            mailVo.setSndngSttsCd(MAIL_STATUS_SUCCESS);
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("resultCode", mailVo.getSndngSttsCd());
            resultMap.put("resultMsg", null);

        }catch (AddressException e) {  //예외처리 주소를 입력하지 않을 경우
            log.error("sendMail.AddressException.150L");
            
            mailVo.setSndngSttsCd(MAIL_ERROR_NOT_EXIST_ADDRESS);
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("resultCode", mailVo.getSndngSttsCd());
            resultMap.put("resultMsg", "메일주소누락");
            
            return resultMap; 
        }catch (MessagingException e) { //메시지에 이상이 있을 경우
            log.error("sendMail.MessagingException.159L");
            
            mailVo.setSndngSttsCd(MAIL_ERROR_CONTENTS);
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("resultCode", mailVo.getSndngSttsCd());
            resultMap.put("resultMsg", "메시지이상");

            return resultMap; 
        }
        catch (Exception e) {
        	log.error("sendMail.Exception.166L");
        	
        	mailVo.setSndngSttsCd(MAIL_ERROR_UNKNOWN);
        	resultMap.put("result", Constant.REST_API_RESULT_FAIL);
        	resultMap.put("resultCode", mailVo.getSndngSttsCd());
        	resultMap.put("resultMsg", "메일발송오류"); //

            return resultMap; 
        }finally{
            //LOGGER.info("**************************************************");
            LOGGER.info(  resultMap.get("resultCode") + "/" + mailId + "/" + mailVo.getRcptnEmail() + "/" + mailVo.getTitle() );
            //LOGGER.info("**************************************************");

            // history에 기록한다.
            mailDao.insertMailHist(mailVo);
            
        }
        
        return resultMap;         
    }

	@Override
	public Map<String, Object> sendMultiMail(List<MailRcptnVo> mailToArray, MailVo mailVo) throws Exception {
		
		Boolean isAllSuccess = true;
		List<MailVo> resMailList = new ArrayList<MailVo>();
		
		if(mailToArray != null && mailToArray.size() > 0){
		    MailRcptnVo mailRcptnVo = null;
			for(int i = 0 ; i < mailToArray.size() ; i++){
			    mailRcptnVo = mailToArray.get(i);
				mailVo.setRcptnEmail(mailRcptnVo.getRcptnEmail());
				mailVo.setRcptnUserid(mailRcptnVo.getRcptnUserid());
				
				Map<String, Object> res = sendMail(mailVo);
				
				// 한 건이라도 오류가 있다면.
				if(!res.get("resultCode").equals(MAIL_STATUS_SUCCESS)) {
					isAllSuccess = false;
				}
				
				mailVo.setSndngSttsCd(res.get("resultCode").toString());
				resMailList.add(mailVo);
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(isAllSuccess) {
			mailVo.setSndngSttsCd(MAIL_STATUS_SUCCESS);	
		} else {
			mailVo.setSndngSttsCd(MAIL_STATUS_FAIL);
			resultMap.put("resultMsg", "일부 성공하지 못한 메일이 있습니다. [resultDetail]을 확인 하세요.");
		}
        
        resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
        resultMap.put("resultCode", mailVo.getSndngSttsCd());
        resultMap.put("resultDetail", resMailList);
        
		
		return resultMap;
	}

	@Override
	public void sendOneMailAsync(String to, String title, String contents) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMultiMailAsync(String[] to, String title, String contents) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
