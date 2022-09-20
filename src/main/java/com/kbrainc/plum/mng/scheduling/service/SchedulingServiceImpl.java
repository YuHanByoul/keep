package com.kbrainc.plum.mng.scheduling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.scheduling.model.SchedulingDao;
import com.kbrainc.plum.mng.scheduling.model.SchedulingHistVo;
import com.kbrainc.plum.mng.scheduling.model.SchedulingVo;

/**
* 스케줄링 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.service
* - SchedulingServiceImpl.java
* </pre>
*
* @ClassName   : SchedulingServiceImpl 
* @Description : 스케줄링 서비스 구현 클래스.
* @author      : KBRAINC
* @date        : 2021. 3. 17.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    private SchedulingDao schedulingDao;
    
    /**
    * 스케줄링트리거 목록을 가져온다.
    *
    * @Title       : selectSchedTriggerList 
    * @Description : 스케줄링트리거 목록을 가져온다.
    * @param schedulingVo SchedulingVo객체
    * @return List<SchedulingVo> 스케줄링트리거 목록
    * @throws Exception 예외
    */
    public List<SchedulingVo> selectSchedTriggerList(SchedulingVo schedulingVo) throws Exception {
        return schedulingDao.selectSchedTriggerList(schedulingVo);
    }
    
    /**
    * 스케줄이력 목록을 가져온다.
    *
    * @Title       : selectSchedHistList 
    * @Description : 스케줄이력 목록을 가져온다.
    * @param schedulingHistVo SchedulingHistVo객체
    * @return List<SchedulingHistVo> 스케줄링트리거 목록
    * @throws Exception 예외
    */
    public List<SchedulingHistVo> selectSchedHistList(SchedulingHistVo schedulingHistVo) throws Exception {
        return schedulingDao.selectSchedHistList(schedulingHistVo);
    }
    
    /**
    * 트리거이름으로 트리거 정보를 가져온다.
    *
    * @Title       : selectTriggerByNm 
    * @Description : 트리거이름으로 트리거 정보를 가져온다.
    * @param schedulingVo ScheculingVo객체
    * @return SchedulingVo 스케줄트리거 정보
    * @throws Exception 예외
    */
    public SchedulingVo selectTriggerByNm(SchedulingVo schedulingVo) throws Exception {
        return schedulingDao.selectTriggerByNm(schedulingVo);
    }
    
    /**
    * 스케줄트리거를 등록한다.
    *
    * @Title       : insertSchedTrigger 
    * @Description : 스케줄트리거를 등록한다.
    * @param schedulingVo SchedulingVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertSchedTrigger(SchedulingVo schedulingVo) throws Exception {
        return schedulingDao.insertSchedTrigger(schedulingVo);
    }
    
    /**
    * 트리거아이디로 트리거 정보를 가져온다.
    *
    * @Title       : selectTriggerInfo
    * @Description : 트리거아이디로 트리거 정보를 가져온다.
    * @param schedulingVo ScheculingVo객체
    * @return SchedulingVo 스케줄트리거 정보
    * @throws Exception 예외
    */
    public SchedulingVo selectTriggerInfo(SchedulingVo schedulingVo) throws Exception {
        return schedulingDao.selectTriggerInfo(schedulingVo);
    }
    
    /**
    * 스케줄트리거를 수정한다.
    *
    * @Title       : updateSchedTrigger 
    * @Description : 스케줄트리거를 수정한다.
    * @param schedulingVo SchedulingVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateSchedTrigger(SchedulingVo schedulingVo) throws Exception {
        return schedulingDao.updateSchedTrigger(schedulingVo);
    }
}