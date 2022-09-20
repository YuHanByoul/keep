package com.kbrainc.plum.mng.scheduling.service;

import java.util.List;

import com.kbrainc.plum.mng.scheduling.model.SchedulingHistVo;
import com.kbrainc.plum.mng.scheduling.model.SchedulingVo;

/**
* 스케줄링 서비스 인터페이스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.service
* - SchedulingService.java
* </pre>
*
* @ClassName   : SchedulingService 
* @Description : 스케줄링 서비스 인터페이스. 
* @author      : KBRAINC
* @date        : 2021. 3. 17.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
public interface SchedulingService {

    /**
    * 스케줄링트리거 목록을 가져온다.
    *
    * @Title       : selectSchedTriggerList 
    * @Description : 스케줄링트리거 목록을 가져온다.
    * @param schedulingVo SchedulingVo객체
    * @return List<SchedulingVo> 스케줄링트리거 목록
    * @throws Exception 예외
    */
    public List<SchedulingVo> selectSchedTriggerList(SchedulingVo schedulingVo) throws Exception;
    
    /**
    * 스케줄이력 목록을 가져온다.
    *
    * @Title       : selectSchedHistList 
    * @Description : 스케줄이력 목록을 가져온다.
    * @param schedulingHistVo SchedulingHistVo객체
    * @return List<SchedulingHistVo> 스케줄링트리거 목록
    * @throws Exception 예외
    */
    public List<SchedulingHistVo> selectSchedHistList(SchedulingHistVo schedulingHistVo) throws Exception;
    
    /**
    * 트리거이름으로 트리거 정보를 가져온다.
    *
    * @Title       : selectTriggerByNm 
    * @Description : 트리거이름으로 트리거 정보를 가져온다.
    * @param schedulingVo ScheculingVo객체
    * @return SchedulingVo 스케줄트리거 정보
    * @throws Exception 예외
    */
    public SchedulingVo selectTriggerByNm(SchedulingVo schedulingVo) throws Exception;
    
    /**
    * 스케줄트리거를 등록한다.
    *
    * @Title       : insertSchedTrigger 
    * @Description : 스케줄트리거를 등록한다.
    * @param schedulingVo SchedulingVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertSchedTrigger(SchedulingVo schedulingVo) throws Exception;
    
    /**
    * 트리거아이디로 트리거 정보를 가져온다.
    *
    * @Title       : selectTriggerInfo
    * @Description : 트리거아이디로 트리거 정보를 가져온다.
    * @param schedulingVo ScheculingVo객체
    * @return SchedulingVo 스케줄트리거 정보
    * @throws Exception 예외
    */
    public SchedulingVo selectTriggerInfo(SchedulingVo schedulingVo) throws Exception;
    
    /**
    * 스케줄트리거를 수정한다.
    *
    * @Title       : updateSchedTrigger 
    * @Description : 스케줄트리거를 수정한다.
    * @param schedulingVo SchedulingVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateSchedTrigger(SchedulingVo schedulingVo) throws Exception;
}