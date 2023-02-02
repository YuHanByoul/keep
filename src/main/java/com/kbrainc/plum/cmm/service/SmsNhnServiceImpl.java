package com.kbrainc.plum.cmm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * NHN SMS 발송 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - SmsNhnServiceImpl.java
 * </pre> 
 *
 * @ClassName : SmsNhnServiceImpl
 * @Description : 문자발송 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class SmsNhnServiceImpl extends PlumAbstractServiceImpl implements SmsNhnService {

    @Value("${sms.host}")
    private String host;
    
    @Value("${sms.appKey}")
    private String appKey;
    
    @Value("${sms.secretKey}")
    private String secretKey;
    
    @Value("${sms.sendNo}")
    private String sendNo;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    /**
    * 단문 SMS 발송
    *
    * @Title : sendSms 
    * @Description : 단문 SMS 발송
    * @param msg 내용
    * @param phoneList 수신자번호 목록
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendSms(String msg, String[] phoneList) throws Exception {
        List<Map<String, Object>> recipientList = new ArrayList<>();
        for(int i = 0; i < phoneList.length; i++) {
            Map<String, Object> recipientInfo = new HashMap<>();
            recipientInfo.put("recipientNo", phoneList[i]);
            recipientList.add(recipientInfo);
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("body", msg);
        params.put("sendNo", sendNo);
        params.put("recipientList", recipientList);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Secret-Key", secretKey);
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(host + "/sms/v3.0/appKeys/" + appKey + "/sender/sms", request, String.class);
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    /**
    * 장문 MMS 발송
    *
    * @Title : sendMms 
    * @Description : 장문 MMS 발송
    * @param title 제목
    * @param msg 내용
    * @param phoneList 수신자번호 목록
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendMms(String title, String msg, String[] phoneList) throws Exception {
        List<Map<String, Object>> recipientList = new ArrayList<>();
        for(int i = 0; i < phoneList.length; i++) {
            Map<String, Object> recipientInfo = new HashMap<>();
            recipientInfo.put("recipientNo", phoneList[i]);
            recipientList.add(recipientInfo);
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        params.put("body", msg);
        params.put("sendNo", sendNo);
        params.put("recipientList", recipientList);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Secret-Key", secretKey);
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(host + "/sms/v3.0/appKeys/" + appKey + "/sender/mms", request, String.class);
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    /**
    * 인증용(긴급) SMS 발송
    *
    * @Title : sendAuth 
    * @Description : 인증용(긴급) MMS 발송
    * @param msg 내용
    * @param phoneList 수신자번호 목록
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendAuth(String msg, String[] phoneList) throws Exception {
        List<Map<String, Object>> recipientList = new ArrayList<>();
        for(int i = 0; i < phoneList.length; i++) {
            Map<String, Object> recipientInfo = new HashMap<>();
            recipientInfo.put("recipientNo", phoneList[i]);
            recipientList.add(recipientInfo);
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("body", msg);
        params.put("sendNo", sendNo);
        params.put("recipientList", recipientList);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Secret-Key", secretKey);
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(host + "/sms/v3.0/appKeys/" + appKey + "/sender/auth/sms", request, String.class);
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
}