package com.kbrainc.plum.cmm.scheduling.service;

import java.util.List;

import com.kbrainc.plum.cmm.scheduling.model.SchedulingTriggerVo;

/**
* 스케줄링트리거 서비스 인터페이스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.service
* - SchedulingTriggerService.java
* </pre>
*
* @ClassName   : SchedulingTriggerService 
* @Description : 스케줄링트리거 서비스 인터페이스. 
* @author      : KBRAINC
* @date        : 2021. 2. 23.
* @Version     :  
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
public interface SchedulingTriggerService {

    /**
    * 스케줄링트리거 목록을 조회한다.
    *
    * @Title       : selectSchedulingTriggerList 
    * @Description : 스케줄링트리거 목록을 조회한다.
    * @return List<SchedulingTriggerVo> 스케줄링트리거정보 목록
    * @throws Exception 예외
    */
    public List<SchedulingTriggerVo> selectSchedulingTriggerList() throws Exception;
}