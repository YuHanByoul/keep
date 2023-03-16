package com.kbrainc.plum.config.scheduling.quartz;

import java.sql.SQLException;

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
    		    batchJobService.userDrmncyProcess(triggerid);
			} catch (SQLException e) {
			    log.error("executeInternal.SQLException.51L");
				throw new JobExecutionException(jobName);
			} catch (Exception e) {
			    log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
    	}
    	
    	// 유아환경교육 안내 메시지 발송
        if (triggerid == 7 && "infntEduMsgJob".equals(jobName)) {
            try {
                batchJobService.infntEnveduMsgNoticeMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 푸름이 이동 환경교육 안내 메시지 발송
        if (triggerid == 8 && "mvnEduMsgJob".equals(jobName)) {
            try {
                batchJobService.mvnEnveduMsgNoticeMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 휴면계정 전환 안내 메일 발송
        if (triggerid == 9 && "drmncyNtcMailJob".equals(jobName)) {
            try {
                batchJobService.userDrmncyNtcMailSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 3개월 이상된 알림메시지 삭제
        if (triggerid == 10 && "oldNtcMsgJob".equals(jobName)) {
            try {
                batchJobService.deleteOldNtcMsg(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 푸름이 이동환경교실 만족도 조사 안내 메시지 발송
        if (triggerid == 11 && "mvnDgstfnJob".equals(jobName)) {
            try {
                batchJobService.mvnEnveduDgstfnMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
     // 유아환경교육 만족도 조사 안내 메시지 발송
        if (triggerid == 12 && "infntDgstfnJob".equals(jobName)) {
            try {
                batchJobService.infntEnveduDgstfnMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                e.printStackTrace();
                //throw new JobExecutionException(jobName);
            }
        }
    	
    	log.info("================= batch job end ({}) ================", jobName);
    }
}
