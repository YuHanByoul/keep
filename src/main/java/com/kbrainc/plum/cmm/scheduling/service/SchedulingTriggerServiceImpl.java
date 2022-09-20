package com.kbrainc.plum.cmm.scheduling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.cmm.scheduling.model.SchedulingTriggerDao;
import com.kbrainc.plum.cmm.scheduling.model.SchedulingTriggerVo;

/**
* 스케줄링트리거 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.service
* - SchedulingTriggerServiceImpl.java
* </pre>
*
* @ClassName   : SchedulingTriggerServiceImpl 
* @Description : 스케줄링트리거 서비스 구현 클래스.
* @author      : KBRAINC
* @date        : 2021. 2. 23.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Service
public class SchedulingTriggerServiceImpl implements SchedulingTriggerService {

    @Autowired
    private SchedulingTriggerDao schedulingTriggerDao;
    
    /**
    * 스케줄링트리거 목록을 조회한다.
    *
    * @Title       : selectSchedulingTriggerList 
    * @Description : 스케줄링트리거 목록을 조회한다.
    * @return List<SchedulingTriggerVo> 스케줄링트리거정보 목록
    * @throws Exception 예외
    */
    @Override
    public List<SchedulingTriggerVo> selectSchedulingTriggerList() throws Exception {
        return schedulingTriggerDao.selectSchedulingTriggerList();
    }
}