package com.kbrainc.plum.mng.rsvtAply.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.mng.rsvtAply.model.FclAplyRsvtdeVo;
import com.kbrainc.plum.mng.spce.model.SpceVo;

/**
 * 
 * 예약신청 관리 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.mng.rsvtAply.service
 * - RsvtAplyService.java
 * </pre> 
 *
 * @ClassName : RsvtAplyService
 * @Description : 예약신청 관리 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2023. 1. 27.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface RsvtAplyService {
    
    /**
     * 공간 리스트 호출 
     *
     * @Title       : selectFclRsvtdeList 
     * @Description : 공간 리스트 호출
     * @param Map<String,Object> 객체
     * @return List<Map<String,Object>> 기관정보 목록
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectSpcetList(SpceVo spceVo) throws Exception;
    /**
     * 예약신청 일자  리스트 호출 
     *
     * @Title       : selectRsvtApplyList 
     * @Description : 예약신청 일자  리스트 호출
     * @param Map<String,Object> 객체
     * @return List<FclAplyRsvtdeVo>
     * @throws Exception 예외
     */
    public List<FclAplyRsvtdeVo> selectRsvtAplyList(FclAplyRsvtdeVo fclAplyRsvtdeVo) throws Exception;
    
}