package com.kbrainc.plum.front.enveduFlct.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.enveduFlct.model.EnveduFcltVo;

/**
 * 내 주변 환경교육시설 Service
 *
 * <pre>
 * com.kbrainc.plum.front.enveduFlct.Service
 * - EnveduFlctService.java
 * </pre>
 *
 * @author : KBRAINC
 * @ClassName : EnveduFlctService
 * @Description : 내 주변 환경교육시설 Service
 * @date : 2023. 03. 07.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

public interface EnveduFlctService {
    
    /**
     * 내 주변 환경교육 시설 리스트를 반환한다.
     *
     * @Title       : nearbyEnveduFlct
     * @Description : 내 주변 환경교육 시설 리스트를 반환한다.
     * @return List<Map<String, Object>>
     * @throws Exception 예외
     */
    public List<Map<String, Object>> nearbyEnveduFlct(Map map) throws Exception;
    
    /**
     * 내 주변 환경교육 시설 리스트 갯수를 반환한다.
     *
     * @Title       : nearbyEnveduFlct
     * @Description : 내 주변 환경교육 시설 리스트 갯수를 반환한다.
     * @return int
     * @throws Exception 예외
     */
    public int nearbyEnveduFlctCount(Map map) throws Exception;
    
    /**
    * 환경교육시설현황 목록 조회
    *
    * @Title : selectEnveduFcltList
    * @Description : 환경교육시설현황 목록 조회
    * @throws Exception
    * @return List<EnveduFcltVo>
    */
    public List<EnveduFcltVo> selectEnveduFcltList() throws Exception;

}
