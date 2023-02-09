package com.kbrainc.plum.cmm.service;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.JSONParser;
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
 * NHN 알림톡 발송 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - AlimtalkNhnServiceImpl.java
 * </pre> 
 *
 * @ClassName : AlimtalkNhnServiceImpl
 * @Description : 알림톡 발송 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2023. 2. 2.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class AlimtalkNhnServiceImpl extends PlumAbstractServiceImpl implements AlimtalkNhnService {

    @Value("${alimtalk.host}")
    private String host;
    
    @Value("${alimtalk.senderKey}")
    private String senderKey;
    
    @Value("${alimtalk.appKey}")
    private String appKey;
    
    @Value("${alimtalk.secretKey}")
    private String secretKey;
    
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    /**
    * 알림톡 발송(일반메세지)
    *
    * @Title : sendAlimtalk 
    * @Description : 알림톡 발송
    * @param templateCode 템플릿코드
    * @param recipientList 수신자 목록
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> sendAlimtalk(String templateCode, String recipientList) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(recipientList);
        
        Map<String, Object> params = new HashMap<>();
        params.put("senderKey", senderKey);
        params.put("templateCode", templateCode);
        params.put("recipientList", obj);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Secret-Key", secretKey);
        HttpEntity<String> request = new HttpEntity<String>(jsonRes, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(host + "/alimtalk/v2.2/appkeys/" + appKey + "/messages", request, String.class);
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
}