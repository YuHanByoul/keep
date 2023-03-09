package com.kbrainc.plum.cmm.service;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.mail.model.MailDao;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

import lombok.extern.slf4j.Slf4j;

/**
* 
* NHN 메일 발송 서비스.
*
* <pre>
* com.kbrainc.plum.cmm.service
* - MailNhnServiceImpl.java
* </pre> 
*
* @ClassName : MailNhnServiceImpl
* @Description : NHN 메일 발송 서비스
* @author : KBRAINC
* @date : 2023. 3. 9.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Service("MailNhnService")
@Slf4j
public class MailNhnServiceImpl extends PlumAbstractServiceImpl implements MailService {

	private static final String MAIL_STATUS_SUCCESS = "S"; // 발송 성공
	private static final String MAIL_STATUS_FAIL = "F";	// 발송 실패
	private static final String MAIL_ERROR_UNKNOWN = "9999"; // 알수 없는 오류
	
	@Autowired
	MailDao mailDao;

    @Value("${email.host}")
    private String host;
    
    @Value("${email.appKey}")
    private String appKey;
    
    @Value("${email.secretKey}")
    private String secretKey;
    
    @Value("${email.senderAddress}")
    String senderAddress;
    
    @Value("${email.senderName}")
    String senderName;
        
    private final RestTemplate restTemplate = new RestTemplate();
        
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
    	
        List<Map<String, Object>> receiverList = new ArrayList<>();
        Map<String, Object> receiverInfo = new HashMap<>();
        receiverInfo.put("receiveMailAddr", mailVo.getRcptnEmail());
        receiverInfo.put("receiveType", "MRT0"); // 받는사람
        receiverList.add(receiverInfo);
        
        Map<String, Object> params = new HashMap<>();
        
        if (CommonUtil.isEmpty(mailVo.getSndngEmail())) {
            params.put("senderAddress", senderAddress);
        } else {
            params.put("senderAddress", mailVo.getSndngEmail());
        }
        
        if (CommonUtil.isEmpty(mailVo.getSndngUserNm())) {
            params.put("senderName", senderName);
        } else {
            params.put("senderName", mailVo.getSndngUserNm());
        }
        
        params.put("title", mailVo.getTitle());
        params.put("body", mailVo.getCntnts());
        params.put("receiverList", receiverList);
        params.put("userId", mailVo.getSndngSeCd());
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Secret-Key", secretKey);
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(host + "/email/v2.0/appKeys/" + appKey + "/sender/mail", request, String.class);
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
            Boolean isSuccessful = (Boolean)((Map)response.get("header")).get("isSuccessful");
            
            if (isSuccessful) {
                mailVo.setSndngSttsCd(MAIL_STATUS_SUCCESS);
                response.put("result", Constant.REST_API_RESULT_SUCCESS);
                response.put("resultCode", mailVo.getSndngSttsCd());
                response.put("resultMsg", null);
            } else {
                mailVo.setSndngSttsCd(MAIL_STATUS_FAIL);
                response.put("result", Constant.REST_API_RESULT_FAIL);
                response.put("resultCode", mailVo.getSndngSttsCd());
                response.put("resultMsg", null);
            }

        } catch (Exception e) {
        	mailVo.setSndngSttsCd(MAIL_ERROR_UNKNOWN);
        	response.put("result", Constant.REST_API_RESULT_FAIL);
        	response.put("resultCode", mailVo.getSndngSttsCd());
        	response.put("resultMsg", "메일발송오류");

            return response; 
        } finally {
            mailDao.insertEmlSndngDsctn(mailVo);
        }
        
        return response;     
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
