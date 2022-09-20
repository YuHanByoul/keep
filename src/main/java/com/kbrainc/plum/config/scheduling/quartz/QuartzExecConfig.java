package com.kbrainc.plum.config.scheduling.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.kbrainc.plum.cmm.scheduling.service.BatchJobService;

import lombok.extern.slf4j.Slf4j;

/**
* quartz(스케줄링)에 의해 실행되는 공통 실행기.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - QuartzExecConfig.java
* </pre>
*
* @ClassName   : QuartzExecConfig 
* @Description : quartz(스케줄링)에 의해 실행되는 공통 실행기. 
* @author      : Zeniel
* @date        : 2021. 2. 23.
* @Version     : 1.0 
* @Company     : CopyrightⒸ ZENIEL. All Rights Reserved
*/
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzExecConfig extends QuartzJobBean {
	
	@Autowired
	private BatchJobService batchJobService;
	
	private int triggerid;
	private String jobName;

	public void setTriggerid(int triggerid) {
		this.triggerid = triggerid;
	}
 
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
 
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    	log.info("================ batch job start ({}) ===============", jobName);
    	
    	// 휴면계정처리
    	if (triggerid == 6 && "userDrmncyJob".equals(jobName)) {
    		try {
				batchJobService.callSpUserDrmncy(triggerid);
			} catch (Exception e) {
				throw new JobExecutionException(jobName);
			}
    	}
    	
    	log.info("================= batch job end ({}) ================", jobName);
    }
}
