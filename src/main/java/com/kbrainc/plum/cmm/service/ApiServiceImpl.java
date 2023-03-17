package com.kbrainc.plum.cmm.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
/**
 * 
 * API 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - ApiServiceImpl.java
 * </pre> 
 *
 * @ClassName : ApiServiceImpl
 * @Description : API 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2023. 3. 13.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class ApiServiceImpl extends PlumAbstractServiceImpl implements ApiService {

    @Value("${data.go.kr.serviceKey}")
    private String dataServiceKey;
    
    @Value("${hrfco.go.kr.serviceKey}")
    private String hrfcoServiceKey;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    /**
    * 실시간 대기정보 조회
    *
    * @Title : getCtprvnRltmMesureDnsty 
    * @Description : 실시간 대기정보 조회
    * @param sidoName 시도 명
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getCtprvnRltmMesureDnsty(String sidoName) throws Exception {
        String path = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=" + dataServiceKey + "&returnType=json&ver=1.3";
        path += "&numOfRows=100&pageNo=1&sidoName=" + sidoName;
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(path, null, String.class); 
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    /**
    * 오늘/내일/모레 예보 조회
    *
    * @Title : getMinuDustFrcstDspth 
    * @Description : 오늘/내일/모레 예보 조회
    * @param searchDate 조회 날짜
    * @param informCode 통보코드(PM10: 미세먼지, PM25: 초미세먼지, O3: 오존)
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getMinuDustFrcstDspth(String searchDate, String informCode) throws Exception {
        
        String path = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMinuDustFrcstDspth?serviceKey=" + dataServiceKey + "&returnType=json&ver=1.1";
        path += "&numOfRows=100&pageNo=1&searchDate=" + searchDate + "&informCode=" + informCode;
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(path, null, String.class); 
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    /**
    * TM 기준좌표 조회
    *
    * @Title : getTMStdrCrdnt 
    * @Description : TM 기준좌표 조회
    * @param umdName 읍면동명
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getTMStdrCrdnt(String umdName) throws Exception {
        String path = "http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getTMStdrCrdnt?serviceKey=" + dataServiceKey + "&returnType=json";
        path += "&numOfRows=100&pageNo=1&umdName=" + umdName;
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(path, null, String.class); 
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    /**
    * 근접측정소 목록 조회
    *
    * @Title : getNearbyMsrstnList 
    * @Description : 근접측정소 목록 조회
    * @param tmX TM측정방식 X 좌표
    * @param tmY TM측정방식 Y 좌표
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getNearbyMsrstnList(String tmX, String tmY) throws Exception {
        String path = "http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getNearbyMsrstnList?serviceKey=" + dataServiceKey + "&returnType=json";
        path += "&tmX=" + tmX + "&tmY=" + tmY;
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(path, null, String.class); 
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    /**
    * 우리동네 대기정보 조회
    *
    * @Title : getMsrstnAcctoRltmMesureDnsty 
    * @Description : 우리동네 대기정보 조회
    * @param stationName 측정소명
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getMsrstnAcctoRltmMesureDnsty(String stationName) throws Exception {
        String path = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey=" + dataServiceKey + "&returnType=json&ver=1.3";
        path += "&numOfRows=1&pageNo=1&dataTerm=DAILY&stationName=" + stationName;
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(path, null, String.class); 
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    /**
    * 전국 수문 관측소 제원 정보 목록 조회
    *
    * @Title : getWlobsList 
    * @Description : 전국 수문 관측소 제원 정보 목록 조회
    * @param hydroType 수문자료 종류(waterlevel: 수위, dam: 댐, bo: 보)
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getWlobsList(String hydroType) throws Exception {
        String path = "https://api.hrfco.go.kr/" + hrfcoServiceKey + "/" + hydroType + "/info.json";
        ResponseEntity<String> responseEntityStr = restTemplate.getForEntity(path, String.class); 
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    /**
    * 수문 관측 정보 목록 조회
    *
    * @Title : getObsrvnList 
    * @Description : 수문 관측 정보 목록 조회
    * @param hydroType 수문자료 종류(waterlevel: 수위, dam: 댐, bo: 보)
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getObsrvnList(String hydroType) throws Exception {
        String path = "https://api.hrfco.go.kr/" + hrfcoServiceKey + "/" + hydroType + "/list/10M.json";
        ResponseEntity<String> responseEntityStr = restTemplate.getForEntity(path, String.class); 
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    /**
    * 농업기상 관측지점 정보 조회
    *
    * @Title : getObsrSpotList 
    * @Description : 농업기상 관측지점 정보 조회
    * @param doSeCode 도 구분코드
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getObsrSpotList(String doSeCode) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriWeather/getObsrSpotList");
        urlBuilder.append("?serviceKey=eZApLnsE5ylVWll22RkEFJSxstx8R6XAD7GJvfg%2Blv%2B6JvcvjdX338EKLSAC2X8swUYR8Djpg3ntEdgwiLx%2B5A%3D%3D");
        urlBuilder.append("&Page_Size=100");
        urlBuilder.append("&Page_No=1");
        urlBuilder.append("&Do_Se_Code=" + doSeCode);
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        JSONObject jsonObject = XML.toJSONObject(sb.toString());
        Map<String, Object> response = new ObjectMapper().readValue(jsonObject.toString(), Map.class);
        
        return response;
    }
    
}