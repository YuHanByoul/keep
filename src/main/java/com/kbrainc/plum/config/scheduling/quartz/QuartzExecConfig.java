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
                throw new JobExecutionException(jobName);
            }
        }
        
        // 전문가 섭외자와 대상 전문가 대상 교육 알림 메시지 발송
        if (triggerid == 13 && "exprtBfrMsgJob".equals(jobName)) {
            try {
                batchJobService.exprtEduBfrMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 전문가 섭외자 대상 만족도 평가 안내 메시지 발송
        if (triggerid == 14 && "exprtAftrMsgJob".equals(jobName)) {
            try {
                batchJobService.exprtEduAftrMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 미입금 시설예약 취소처리
        if (triggerid == 15 && "flctRsvCnclJob".equals(jobName)) {
            try {
                batchJobService.flctRsvCancle(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 시설 이용자 대상 만족도 평가 안내 메시지 발송
        if (triggerid == 16 && "flctRsvDgstfnJob".equals(jobName)) {
            try {
                batchJobService.flctRsvDgstfnNtcMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 유아환경교육관, 푸름이 이동환경교실 교육 3일 전 안내 메시지 발송
        if (triggerid == 17 && "enveduBfrMsgJob".equals(jobName)) {
            try {
                batchJobService.infntMvnEnveduBfrMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 유아환경교육관, 푸름이 이동환경교실 교육 3일 후 만족도 평가 요청 안내 메시지 발송
        if (triggerid == 18 && "enveduAftrMsgJob".equals(jobName)) {
            try {
                batchJobService.infntMvnEnveduAftrMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 전문가 섭외자와 대상 전문가 대상 3일전 교육 알림 메시지 발송
        if (triggerid == 19 && "exprt3BfrMsgJob".equals(jobName)) {
            try {
                batchJobService.exprtBfrMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 전문가 섭외자 대상 3일 후 만족도 평가 안내 메시지 발송
        if (triggerid == 20 && "exprt3AftrMsgJob".equals(jobName)) {
            try {
                batchJobService.exprtAftrMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 공동구매 진행 마감일 안내 메시지 발송
        if (triggerid == 21 && "jntpurchsEndJob".equals(jobName)) {
            try {
                batchJobService.jntpurchsEndMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 공동구매 진행 마감일 30일 후 안내 메시지 발송
        if (triggerid == 22 && "jntpurchsEndAftrJob".equals(jobName)) {
            try {
                batchJobService.jntpurchsEndAftrMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
        
        // 교구 대여 반납후 이용후기 안내 메시지 발송
        if (triggerid == 23 && "lndAplyDgstfnJob".equals(jobName)) {
            try {
                batchJobService.lendAplyDgstfnMsgSend(triggerid);
            } catch (SQLException e) { 
                log.error("executeInternal.SQLException.51L");
                throw new JobExecutionException(jobName);
            } catch (Exception e) {
                log.error("executeInternal.Exception.51L");
                throw new JobExecutionException(jobName);
            }
        }
    	
    	log.info("================= batch job end ({}) ================", jobName);
    }
}
