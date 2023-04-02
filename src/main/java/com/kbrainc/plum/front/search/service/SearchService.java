package com.kbrainc.plum.front.search.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbrainc.plum.front.member.model.MemberVo;

/**
 * 
 * 통합검색 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.front.search.service
 * - SearchService.java
 * </pre> 
 *
 * @ClassName : SearchService
 * @Description : 통합검색 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2023. 3. 31.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface SearchService {

    /**
    * 검색어 자동완성 목록 조회
    *
    * @Title : getSmartmakerInfo
    * @Description : 검색어 자동완성 목록 조회
    * @param keyword 자동완성 키워드
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    public Map<String, Object> getSmartmakerInfo(String keyword) throws Exception;
    
    /**
    * 일간 인기 검색어 조회
    *
    * @Title : getTrndKeywordDay
    * @Description : 일간 인기 검색어 조회
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    public Map<String, Object> getTrndKeywordDay() throws Exception;
    
    /**
    * 주간 인기 검색어 조회
    *
    * @Title : getTrndKeywordWeek
    * @Description : 주간 인기 검색어 조회
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    public Map<String, Object> getTrndKeywordWeek() throws Exception;
    
    /**
    * 월간 인기 검색어 조회
    *
    * @Title : getTrndKeywordMonth
    * @Description : 월간 인기 검색어 조회
    * @return Map<String, Object> 조회결과
    * @throws Exception 예외
    */
    public Map<String, Object> getTrndKeywordMonth() throws Exception;

    
}