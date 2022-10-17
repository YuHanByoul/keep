package com.kbrainc.plum.rte.util.mail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * 메일발송 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - MailApiServiceImpl.java
 * </pre> 
 *
 * @ClassName : MailApiServiceImpl
 * @Description : 메일발송 서비스 구현 클래스(API)
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service("MailApiService")
public class MailApiServiceImpl extends PlumAbstractServiceImpl implements MailService {

    @Value("${mail.server.host}")
    private String mailServerHost;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    /**
    * 단일 메일 발송(동기).
    *
    * @Title       : sendOneMail 
    * @Description : 단일 메일 발송.
    * @param mailVo MailVo객체
    * @return Map<String,Object> 메일발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendMail(MailVo mailVo) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("contents", mailVo.getCntnts());
        resultMap.put("text", mailVo.getTitle());
        resultMap.put("to", mailVo.getRcptnEmail());
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(mailServerHost + "/api/mail", request, String.class);
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
        return resMap;
    }
    
    /**
    * 다중 메일 발송(동기).
    *
    * @Title       : sendMultiMail 
    * @Description : 다중 메일 발송.
    * @param to 받는사람 이메일주소와 사용자아이디 리스트
    * @param mailVo MailVo객체
    * @return Map<String,Object> 메일발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendMultiMail(List<MailRcptnVo> to, MailVo mailVo) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("contents", mailVo.getCntnts());
        resultMap.put("text", mailVo.getTitle());
        
        String[] emails = new String[to.size()];
        
        for(int i=0; i < to.size(); i++) {
            emails[i] = new String(to.get(i).getRcptnEmail());
        }
        resultMap.put("to", emails);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(mailServerHost + "/api/mails", request, String.class);
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
        return resMap;
    }
    
    /**
    * 단일 메일 발송(비동기).
    *
    * @Title       : sendOneMailAsync
    * @Description : 단일 메일 발송.
    * @param to 받는사람 이메일주소
    * @param title 메일제목
    * @param contents 메일내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    @Async
    public void sendOneMailAsync(String to, String title, String contents) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("contents", contents);
        resultMap.put("text", title);
        resultMap.put("to", to);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(mailServerHost + "/api/mail", request, String.class);
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
        //return resMap;
    }
    
    /**
    * 다중 메일 발송(비동기).
    *
    * @Title       : sendMultiMailAsync 
    * @Description : 다중 메일 발송.
    * @param to 받는사람 이메일주소 배열
    * @param title 메일제목
    * @param contents 메일내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    @Async
    public void sendMultiMailAsync(String[] to, String title, String contents) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("contents", contents);
        resultMap.put("text", title);
        resultMap.put("to", to);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(mailServerHost + "/api/mails", request, String.class);
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
        //return resMap;
    }
}