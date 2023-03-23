package com.kbrainc.plum.cmm.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbrainc.plum.front.member.model.MemberVo;

/**
 * 
 * API 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - ApiService.java
 * </pre> 
 *
 * @ClassName : ApiService
 * @Description : API 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2023. 3. 13.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface ApiService {

    /**
    * 실시간 대기정보 조회
    *
    * @Title : getCtprvnRltmMesureDnsty 
    * @Description : 실시간 대기정보 조회
    * @param sidoName 시도 명
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    Map<String, Object> getCtprvnRltmMesureDnsty(String sidoName) throws Exception;
    
    /**
    * 오늘/내일/모레 예보 조회
    *
    * @Title : getCtprvnRltmMesureDnsty 
    * @Description : 오늘/내일/모레 예보 조회
    * @param searchDate 조회 날짜
    * @param informCode 통보코드(PM10: 미세먼지, PM25: 초미세먼지, O3: 오존)
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    Map<String, Object> getMinuDustFrcstDspth(String searchDate, String informCode) throws Exception;
    
    /**
    * TM 기준좌표 조회
    *
    * @Title : getTMStdrCrdnt 
    * @Description : TM 기준좌표 조회
    * @param umdName 읍면동명
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    Map<String, Object> getTMStdrCrdnt(String umdName) throws Exception;
    
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
    Map<String, Object> getNearbyMsrstnList(String tmX, String tmY) throws Exception;
    
    /**
    * 우리동네 대기정보 조회
    *
    * @Title : getMsrstnAcctoRltmMesureDnsty 
    * @Description : 우리동네 대기정보 조회
    * @param stationName 측정소명
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    Map<String, Object> getMsrstnAcctoRltmMesureDnsty(String stationName) throws Exception;
    
    /**
    * 전국 수문 관측소 제원 정보 목록 조회
    *
    * @Title : getWlobsList 
    * @Description : 전국 수문 관측소 제원 정보 목록 조회
    * @param hydroType 수문자료 종류(waterlevel: 수위, dam: 댐, bo: 보)
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    Map<String, Object> getWlobsList(String hydroType) throws Exception;
    
    /**
    * 수문 관측 정보 목록 조회
    *
    * @Title : getObsrvnList 
    * @Description : 수문 관측 정보 목록 조회
    * @param hydroType 수문자료 종류(waterlevel: 수위, dam: 댐, bo: 보)
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    Map<String, Object> getObsrvnList(String hydroType) throws Exception;
    
    /**
    * 농업기상 관측지점 정보 조회
    *
    * @Title : getObsrSpotList 
    * @Description : 농업기상 관측지점 정보 조회
    * @param doSeCode 도 구분코드
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    Map<String, Object> getObsrSpotList(String doSeCode) throws Exception;
    
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
    public Map<String, Object> getWeatherTimeList(String dateTime, String obsrSpotCode) throws Exception;
    
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
    public Map<String, Object> getWeatherMonDayList(String searchYear, String searchMonth, String obsrSpotCode) throws Exception;
    
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
    public Map<String, Object> getChargerInfo(String zcode, String zscode) throws Exception;
    
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
    public int insertEnvfld(MemberVo memberVo) throws Exception;
    
}