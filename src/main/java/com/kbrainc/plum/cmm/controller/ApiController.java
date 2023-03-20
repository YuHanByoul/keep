package com.kbrainc.plum.cmm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbrainc.plum.cmm.service.ApiServiceImpl;

/**
 * 
 * API Controller
 *
 * <pre>
 * com.kbrainc.plum.cmm.controller
 * - ApiController.java
 * </pre>
 *
 * @ClassName : ApiController
 * @Description : API Controller
 * @author : KBRAINC
 * @date : 2023. 03. 13.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class ApiController {

    @Autowired
    private ApiServiceImpl apiService;
    
    /**
    * 실시간 대기정보 조회
    *
    * @Title : getCtprvnRltmMesureDnsty
    * @Description : 실시간 대기정보 조회
    * @param sidoName 시도 명
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    @RequestMapping(value = {"/front/api/getCtprvnRltmMesureDnsty.do"})
    @ResponseBody
    public Map<String, Object> getCtprvnRltmMesureDnsty(@RequestParam("sidoName") String sidoName) throws Exception {
        Map<String, Object> result = apiService.getCtprvnRltmMesureDnsty(sidoName);
        
        return result;
    }
    
    /**
    * 오늘/내일/모레 예보 조회
    *
    * @Title : getMinuDustFrcstDspth
    * @Description : 오늘/내일/모레 예보 조회
    * @param searchDate 조회 날짜
    * @param informCode 통보코드(PM10: 미세먼지, PM25: 초미세먼지, O3: 오존)
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    @RequestMapping(value = {"/front/api/getMinuDustFrcstDspth.do"})
    @ResponseBody
    public Map<String, Object> getMinuDustFrcstDspth(@RequestParam("searchDate") String searchDate, @RequestParam("informCode") String informCode) throws Exception {
        Map<String, Object> result = apiService.getMinuDustFrcstDspth(searchDate, informCode);
        
        return result;
    }
    
    /**
    * TM 기준좌표 조회
    *
    * @Title : getTMStdrCrdnt
    * @Description : TM 기준좌표 조회
    * @param umdName 읍면동명
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    @RequestMapping(value = {"/front/api/getTMStdrCrdnt.do"})
    @ResponseBody
    public Map<String, Object> getTMStdrCrdnt(@RequestParam("umdName") String umdName) throws Exception {
        Map<String, Object> result = apiService.getTMStdrCrdnt(umdName);
        
        return result;
    }
    
    /**
    * 근접측정소 목록 조회
    *
    * @Title : getNearbyMsrstnList
    * @Description : 근접측정소 목록 조회
    * @param tmX TM측정방식 X 좌표
    * @param tmY TM측정방식 Y 좌표
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    @RequestMapping(value = {"/front/api/getNearbyMsrstnList.do"})
    @ResponseBody
    public Map<String, Object> getNearbyMsrstnList(@RequestParam("tmX") String tmX, @RequestParam("tmY") String tmY) throws Exception {
        Map<String, Object> result = apiService.getNearbyMsrstnList(tmX, tmY);
        
        return result;
    }
    
    /**
    * 우리동네 대기정보 조회
    *
    * @Title : getMsrstnAcctoRltmMesureDnsty
    * @Description : 우리동네 대기정보 조회
    * @param stationName 측정소명
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    @RequestMapping(value = {"/front/api/getMsrstnAcctoRltmMesureDnsty.do"})
    @ResponseBody
    public Map<String, Object> getMsrstnAcctoRltmMesureDnsty(@RequestParam("stationName") String stationName) throws Exception {
        Map<String, Object> result = apiService.getMsrstnAcctoRltmMesureDnsty(stationName);
        
        return result;
    }
    
    /**
    * 전국 수문 관측소 제원 정보, 측정 정보 목록 조회
    *
    * @Title : getWlobsList
    * @Description : 전국 수문 관측소 제원 정보, 측정 정보 목록 조회
    * @param hydroType 수문자료 종류(waterlevel: 수위, dam: 댐, bo: 보)
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    @RequestMapping(value = {"/front/api/getWlobsList.do"})
    @ResponseBody
    public Map<String, Object> getWlobsList(@RequestParam("hydroType") String hydroType) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("wlobsList", apiService.getWlobsList(hydroType));
        result.put("obsrvnList", apiService.getObsrvnList(hydroType));
        
        return result;
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
    @RequestMapping(value = {"/front/api/getObsrSpotList.do"})
    @ResponseBody
    public Map<String, Object> getObsrSpotList(@RequestParam("doSeCode") String doSeCode) throws Exception {
        Map<String, Object> result = apiService.getObsrSpotList(doSeCode);
        
        return result;
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
    @RequestMapping(value = {"/front/api/getWeatherTimeList.do"})
    @ResponseBody
    public Map<String, Object> getWeatherTimeList(@RequestParam("dateTime") String dateTime, @RequestParam("obsrSpotCode") String obsrSpotCode) throws Exception {
        Map<String, Object> result = apiService.getWeatherTimeList(dateTime, obsrSpotCode);
        
        return result;
    }
    
    /**
    * 농업기상 관측 정보 조회(월)
    *
    * @Title : getWeatherMonDayList 
    * @Description : 농업기상 관측 정보 조회(월)
    * @param searchYear 관측년도
    * @param searchMonth 관측월
    * @param obsrSpotCode 관측지점코드
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @RequestMapping(value = {"/front/api/getWeatherMonDayList.do"})
    @ResponseBody
    public Map<String, Object> getWeatherMonDayList(@RequestParam("searchYear") String searchYear, @RequestParam("searchMonth") String searchMonth, @RequestParam("obsrSpotCode") String obsrSpotCode) throws Exception {
        Map<String, Object> result = apiService.getWeatherMonDayList(searchYear, searchMonth, obsrSpotCode);
        
        return result;
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
    @RequestMapping(value = {"/front/api/getChargerInfo.do"})
    @ResponseBody
    public Map<String, Object> getChargerInfo(@RequestParam("zcode") String zcode, @RequestParam("zscode") String zscode) throws Exception {
        Map<String, Object> result = apiService.getChargerInfo(zcode, zscode);
        
        return result;
    }
    
}
