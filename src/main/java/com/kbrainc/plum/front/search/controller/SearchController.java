package com.kbrainc.plum.front.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.search.model.SearchVo;
import com.kbrainc.plum.front.search.service.SearchService;

/**
 * 
 * 통합검색 컨트롤러.
 *
 * <pre>
 * com.kbrainc.plum.front.search.controller - SearchController.java
 * </pre>
 *
 * @ClassName : SearchController
 * @Description : 통합검색 컨트롤러
 * @author : KBRAINC
 * @date : 2023. 3. 30.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class SearchController { 

    @Autowired
    SearchService searchService;
        
    /** 한글초성 */
    private String [] INITIAL_STRING = {
            "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", 
            "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", 
            "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", 
            "ㅋ", "ㅌ", "ㅍ", "ㅎ" };
    
    /**
    * 한글문자열을 초성으로 변환해서 리턴한다.
    *
    * @Title : getInitialString
    * @Description : 한글문자열을 초성으로 변환해서 리턴한다.
    * @param stringContent 입력문자열
    * @return String 초성변환 문자열
    */
    public String getInitialString(String stringContent) {
        String initialString = "";
        Optional<String> stringContentOp = Optional.ofNullable(stringContent);
        if (stringContentOp.isPresent()) {//전달 문자열 존재
            for (int i = 0; i < stringContentOp.get().length(); i++) {
                String stringLocationInfo = String.valueOf(stringContentOp.get().charAt(i)); //현재 위치의 한글자 추출
                if (stringLocationInfo.matches(".*[가-힣]+.*")) {// 한글일 경우
                    if (stringLocationInfo.charAt(0) >= 0xAC00) { // 음절 시작코드(10진값, 문자) : 0xAC00 (44032 ,'가' )
                        int unicode = stringLocationInfo.charAt(0) - 0xAC00;
                        int index = ((unicode - (unicode % 28))/28)/21;
                        initialString += INITIAL_STRING[index]; 
                    }
                } else { //한글이 아닐경우
                    initialString += stringLocationInfo; //변환없이 그대로 저장
                }
            }
        }
        
        return initialString;
    }
    
    /**
    * 통합검색
    *
    * @Title : search
    * @Description : 통합검색
    * @return String
    */
    @GetMapping("/search.html")
    public String search(SearchVo search) {
        return "front/search/search";
    }
    
    /**
    * 검색어 자동완성 목록 조회.
    *
    * @Title : autocomplete
    * @Description : 검색어 자동완성 목록 조회
    * @param keyword 검색어
    * @return List 자동완성 목록
    */
    @RequestMapping(value = "/search/autocomplete", method = RequestMethod.GET)
    @ResponseBody
    public List autocomplete(@RequestParam(name="keyword", required=true) String keyword) throws Exception {
        Map<String, Object> smartmakerInfo = searchService.getSmartmakerInfo(keyword);
        List<Map<String, String>> smartWordList = (ArrayList<Map<String, String>>) (((ArrayList<Map<String, Object>>) ((Map<String, Object>) smartmakerInfo.get("resultSet")).get("result")).get(0).get("resultDocuments"));
        
        for (Map<String, String> smartWordMap : smartWordList) {
            smartWordMap.put("CHOSUNG", getInitialString(smartWordMap.get("KEYWORD")));
        }
        
        return smartWordList;
    }
    
    /**
    * 인기 검색어 조회.
    *
    * @Title : autocomplete
    * @Description : 인기 검색어 조회
    * @return List 인기검색어 일간/주간/월간 목록
    */
    @RequestMapping(value = "/search/trndKeyword", method = RequestMethod.GET)
    @ResponseBody
    public Map trndKeyword() throws Exception {
        Map<String, Object> trndMap = new HashMap<String, Object>(); 
        Map<String, Object> keywordDay = searchService.getTrndKeywordDay();
        Map<String, Object> keywordWeek = searchService.getTrndKeywordWeek();
        Map<String, Object> keywordMonth = searchService.getTrndKeywordMonth();
        
        List<Map<String, String>> keywordDayList = (ArrayList<Map<String, String>>) (((ArrayList<Map<String, Object>>) ((Map<String, Object>) keywordDay.get("resultSet")).get("result")).get(0).get("resultDocuments"));
        List<Map<String, String>> keywordWeekList = (ArrayList<Map<String, String>>) (((ArrayList<Map<String, Object>>) ((Map<String, Object>) keywordWeek.get("resultSet")).get("result")).get(0).get("resultDocuments"));
        List<Map<String, String>> keywordMonthList = (ArrayList<Map<String, String>>) (((ArrayList<Map<String, Object>>) ((Map<String, Object>) keywordMonth.get("resultSet")).get("result")).get(0).get("resultDocuments"));
        
        trndMap.put("DAY", keywordDayList);
        trndMap.put("WEEK", keywordWeekList);
        trndMap.put("MONTH", keywordMonthList);
        
        return trndMap;
    }
    
    
}