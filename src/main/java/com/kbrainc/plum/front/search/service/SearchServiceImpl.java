package com.kbrainc.plum.front.search.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 
* 통합검색 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.front.search.service
* - SearchServiceImpl.java
* </pre> 
*
* @ClassName : SearchServiceImpl
* @Description : 통합검색 서비스 구현 클래스.
* @author : KBRAINC
* @date : 2023. 3. 31.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Service
public class SearchServiceImpl extends PlumAbstractServiceImpl implements SearchService {
    
    /** 검색엔진 서버URL */
    //@Value("${search.serverUrl}")
    private String searchServerUrl = "http://100.100.24.109:5001"; // 프로퍼티 처리해야함.
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    /**
    * 검색어 자동완성 목록 조회
    *
    * @Title : getSmartmakerInfo
    * @Description : 검색어 자동완성 목록 조회
    * @param keyword 자동완성 키워드
    * @return Map<String,Object> 조회결과
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> getSmartmakerInfo(String keyword) throws Exception {
        String path = String.format("%s/extensions/QuerySetExtensionExample?keyword=%s&gtype=smartmaker&nget=10", searchServerUrl, keyword);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(path, null, String.class); 
        Map<String, Object> response = new ObjectMapper().readValue(responseEntityStr.getBody(), Map.class);
           
        return response;
    }
    
    
}