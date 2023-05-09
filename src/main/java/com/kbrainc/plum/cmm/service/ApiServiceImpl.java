package com.kbrainc.plum.cmm.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.myInfo.model.MyInfoDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ApiServiceImpl extends PlumAbstractServiceImpl implements ApiService {
    
    @Value("${data.go.kr.enc.serviceKey}")
    private String dataEncServiceKey;

    @Value("${data.go.kr.dec.serviceKey}")
    private String dataDecServiceKey;
    
    @Value("${hrfco.go.kr.serviceKey}")
    private String hrfcoServiceKey;
    
    @Resource(name = "front.myInfoDao")
    private MyInfoDao myInfoDao;
    
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
        String path = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=" + dataEncServiceKey + "&returnType=json&ver=1.3";
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
        
        String path = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMinuDustFrcstDspth?serviceKey=" + dataDecServiceKey + "&returnType=json&ver=1.1";
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
        String path = "http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getTMStdrCrdnt?serviceKey=" + dataDecServiceKey + "&returnType=json";
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
        String path = "http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getNearbyMsrstnList?serviceKey=" + dataDecServiceKey + "&returnType=json";
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
        String path = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey=" + dataDecServiceKey + "&returnType=json&ver=1.3";
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
        urlBuilder.append("?serviceKey=");
        urlBuilder.append(dataEncServiceKey);
        urlBuilder.append("&Page_Size=100");
        urlBuilder.append("&Page_No=1");
        urlBuilder.append("&Do_Se_Code=");
        urlBuilder.append(doSeCode);
        
        HttpURLConnection conn = null;
        BufferedReader rd = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlBuilder.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line = rd.readLine();
            while(line != null) {
                sb.append(line);
            }
        } catch(MalformedURLException e) {
            log.error("getObsrSpotList.MalformedURLException");
        } catch(IOException e) {
            log.error("getObsrSpotList.IOException");
        } finally {
            if(rd != null) {
                rd.close();
            }
            if(conn != null) {
                conn.disconnect();
            }
        }
        
        JSONObject jsonObject = XML.toJSONObject(sb.toString());
        Map<String, Object> response = new ObjectMapper().readValue(jsonObject.toString(), Map.class);
        
        return response;
    }
    
    /**
    * 농업기상 관측 정보 조회(시간)
    *
    * @Title : getWeatherTimeList 
    * @Description : 농업기상 관측 정보 조회(시간)
    * @param dateTime 관측년월일
    * @param obsrSpotCode 관측지점코드
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getWeatherTimeList(String dateTime, String obsrSpotCode) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriWeather/WeatherObsrInfo/GnrlWeather/getWeatherTimeList");
        urlBuilder.append("?serviceKey=");
        urlBuilder.append(dataEncServiceKey);
        urlBuilder.append("&Page_Size=100");
        urlBuilder.append("&Page_No=1");
        urlBuilder.append("&date_Time=");
        urlBuilder.append(dateTime);
        urlBuilder.append("&obsr_Spot_Code=");
        urlBuilder.append(obsrSpotCode);
        
        HttpURLConnection conn = null;
        BufferedReader rd = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlBuilder.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line = null;
            while((line = rd.readLine()) != null) {
                sb.append(line);
            }
        } catch(MalformedURLException e) {
            log.error("getObsrSpotList.MalformedURLException");
        } catch(IOException e) {
            log.error("getObsrSpotList.IOException");
        } finally {
            if(rd != null) {
                rd.close();
            }
            if(conn != null) {
                conn.disconnect();
            }
        }
        
        JSONObject jsonObject = XML.toJSONObject(sb.toString());
        Map<String, Object> response = new ObjectMapper().readValue(jsonObject.toString(), Map.class);
        
        return response;
    }
    
    /**
    * 농업기상 관측 정보 조회(일)
    *
    * @Title : getWeatherMonDayList 
    * @Description : 농업기상 관측 정보 조회(일)
    * @param searchYear 관측년도
    * @param searchMonth 관측월
    * @param obsrSpotCode 관측지점코드
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getWeatherMonDayList(String searchYear, String searchMonth, String obsrSpotCode) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriWeather/WeatherObsrInfo/GnrlWeather/getWeatherMonDayList");
        urlBuilder.append("?serviceKey=");
        urlBuilder.append(dataEncServiceKey);
        urlBuilder.append("&Page_Size=100");
        urlBuilder.append("&Page_No=1");
        urlBuilder.append("&search_Year=");
        urlBuilder.append(searchYear);
        urlBuilder.append("&search_Month=");
        urlBuilder.append(searchMonth);
        urlBuilder.append("&obsr_Spot_Code=");
        urlBuilder.append(obsrSpotCode);
        HttpURLConnection conn = null;
        BufferedReader rd = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlBuilder.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line = null;
            while((line = rd.readLine()) != null) {
                sb.append(line);
            }
        } catch(MalformedURLException e) {
            log.error("getWeatherMonDayList.MalformedURLException");
        } catch(IOException e) {
            log.error("getWeatherMonDayList.IOException");
        } finally {
            if(rd != null) {
                rd.close();
            }
            if(conn != null) {
                conn.disconnect();
            }
        }
        
        JSONObject jsonObject = XML.toJSONObject(sb.toString());
        Map<String, Object> response = new ObjectMapper().readValue(jsonObject.toString(), Map.class);
        
        return response;
    }
    
    /**
    * 전기자동차 충전소 정보 목록 조회
    *
    * @Title : getChargerInfo 
    * @Description : 전기자동차 충전소 정보 목록 조회
    * @param zcode 지역구분 코드
    * @param zscode 지역구분 상세 코드
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getChargerInfo(String zcode, String zscode) throws Exception {
        String path = "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo?serviceKey=" + dataEncServiceKey + "&dataType=JSON&numOfRows=10000&pageNo=1";
        path += "&zcode=" + zcode + "&zscode=" + zscode;
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(path, null, String.class); 
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    /**
    *
    * 맞춤 환경정보 저장
    *
    * @Title : insertEnvfld
    * @Description : 
    * @param memberVo MemberVo객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int insertEnvfld(MemberVo memberVo) throws Exception {
        int retVal = 0;
        int userid = memberVo.getUserid();
        
        retVal += myInfoDao.deleteEnvfld(memberVo);
        retVal += myInfoDao.insertEnvfld(memberVo);
        
        return retVal;
    }
    
}