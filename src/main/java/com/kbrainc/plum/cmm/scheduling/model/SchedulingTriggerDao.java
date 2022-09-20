package com.kbrainc.plum.cmm.scheduling.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 스케줄링트리거dao 맵퍼 인터페이스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.model
* - SchedulingTriggerDao.java
* </pre>
*
* @ClassName   : SchedulingTriggerDao 
* @Description : 스케줄링트리거dao 맵퍼 인터페이스. 
* @author      : ZENIEL
* @date        : 2021. 2. 23.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Mapper
public interface SchedulingTriggerDao {
    
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