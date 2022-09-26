package com.kbrainc.plum.cmm.service;

import java.util.HashMap;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * 문자발송 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - SmsServiceImpl.java
 * </pre> 
 *
 * @ClassName : SmsServiceImpl
 * @Description : 문자발송 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class SmsServiceImpl extends PlumAbstractServiceImpl implements SmsService {

    @Value("${sms.server.host}")
    private String smsServerHost;
    
    @Value("${sms.callback}")
    private String smsCallback;
    
    private RestTemplate restTemplate = new RestTemplate();
    
    /**
    * 단일 SMS 발송(동기).
    *
    * @Title       : sendOneSms 
    * @Description : 단일 SMS 발송.
    * @param phone 받는사람 휴대전화번호
    * @param msg 내용
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendOneSms(String phone, String msg) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("phone",phone);
        resultMap.put("callback", smsCallback);
        resultMap.put("msg",msg);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(smsServerHost + "/api/sms", request, String.class);
        
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return resMap;
    }
    
    /**
    * 다중 SMS 발송(동기).
    *
    * @Title       : sendMultiSms 
    * @Description : 다중 SMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendMultiSms(String[] phone, String msg) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("phoneList",phone);
        resultMap.put("callback", smsCallback);
        resultMap.put("msg",msg);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(smsServerHost + "/api/sms", request, String.class);
        
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return resMap;
    }
    
    /**
    * 다중 SMS 발송(예약)(동기).
    *
    * @Title       : sendMultiReserveSms 
    * @Description : 다중 SMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @param reserveDate 예약발송일자
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendMultiReserveSms(String[] phone, String msg, String reserveDate) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("phoneList",phone);
        resultMap.put("callback", smsCallback);
        resultMap.put("reserve", "Y");
        resultMap.put("reserve_date", reserveDate);
        resultMap.put("msg",msg);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(smsServerHost + "/api/sms", request, String.class);
        
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return resMap;
    }
    
    /**
    * 단일 LMS 발송(동기).
    *
    * @Title       : sendOneLms
    * @Description : 단일 LMS 발송.
    * @param phone 받는사람 휴대전화번호
    * @param msg 내용
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendOneLms(String phone, String msg) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("phone", phone);
        resultMap.put("callback", smsCallback);
        resultMap.put("msg", msg);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(smsServerHost + "/api/mms", request, String.class);
        
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return resMap;
    }
    
    /**
    * 다중 LMS 발송(동기).
    *
    * @Title       : sendMultiLms
    * @Description : 다중 LMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendMultiLms(String[] phone, String msg) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("phoneList", phone);
        resultMap.put("callback", smsCallback);
        resultMap.put("msg", msg);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(smsServerHost + "/api/mms", request, String.class);
        
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return resMap;
    }
    
    /**
    * 단일 SMS 발송(비동기).
    *
    * @Title       : sendOneSms 
    * @Description : 단일 SMS 발송.
    * @param phone 받는사람 휴대전화번호
    * @param msg 내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    @Async
    public void sendOneSmsAsync(String phone, String msg) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("phone",phone);
        resultMap.put("callback", smsCallback);
        resultMap.put("msg",msg);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(smsServerHost + "/api/sms", request, String.class);
        
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        //return resMap;
    }
    
    /**
    * 다중 SMS 발송(비동기).
    *
    * @Title       : sendMultiSmsAsync 
    * @Description : 다중 SMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    @Async
    public void sendMultiSmsAsync(String[] phone, String msg) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("phoneList",phone);
        resultMap.put("callback", smsCallback);
        resultMap.put("msg",msg);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(smsServerHost + "/api/sms", request, String.class);
        
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        //return resMap;
    }
    
    /**
    * 다중 SMS 발송(예약)(비동기).
    *
    * @Title       : sendMultiReserveSmsAsync 
    * @Description : 다중 SMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @param reserveDate 예약발송일자
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    @Async
    public void sendMultiReserveSmsAsync(String[] phone, String msg, String reserveDate) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("phoneList",phone);
        resultMap.put("callback", smsCallback);
        resultMap.put("reserve", "Y");
        resultMap.put("reserve_date", reserveDate);
        resultMap.put("msg",msg);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(smsServerHost + "/api/sms", request, String.class);
        
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        //return resMap;
    }
    
    /**
    * 단일 LMS 발송(비동기).
    *
    * @Title       : sendOneLmsAsync
    * @Description : 단일 LMS 발송.
    * @param phone 받는사람 휴대전화번호
    * @param msg 내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    @Async
    public void sendOneLmsAsync(String phone, String msg) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("phone", phone);
        resultMap.put("callback", smsCallback);
        resultMap.put("msg", msg);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(smsServerHost + "/api/mms", request, String.class);
        
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        //return resMap;
    }
    
    /**
    * 다중 LMS 발송(비동기).
    *
    * @Title       : sendMultiLmsAsync
    * @Description : 다중 LMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    @Async
    public void sendMultiLmsAsync(String[] phone, String msg) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("phoneList", phone);
        resultMap.put("callback", smsCallback);
        resultMap.put("msg", msg);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(resultMap);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(smsServerHost + "/api/mms", request, String.class);
        
        Map<String, Object> resMap = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        //return resMap;
    }
}