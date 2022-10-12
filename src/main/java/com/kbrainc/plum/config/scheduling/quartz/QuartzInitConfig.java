package com.kbrainc.plum.config.scheduling.quartz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.kbrainc.plum.cmm.scheduling.model.SchedulingTriggerVo;
import com.kbrainc.plum.cmm.scheduling.service.SchedulingTriggerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class QuartzInitConfig {
	@Autowired
    ApplicationContext applicationContext;

    @Autowired 
    SchedulerFactoryBean schedulerFactory;
    
    @Autowired
    SchedulingTriggerService schedulingTriggerService;
    
    @Value("${spring.quartz.auto-startup}")
    private boolean enabled;
    
    
    /**
    * 초기선행작업을 수행한다.
    *
    * @Title       : init 
    * @Description : 초기선행작업을 수행한다.
    * @return void
    * @throws Exception 예외
    */
    @PostConstruct
    public void init() throws Exception {
        Scheduler scheduler = schedulerFactory.getScheduler();
        
    	if (enabled) {
	        // 전체 트리거 삭제
	        scheduler.unscheduleJobs(new ArrayList<>(scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup())));
	
	        List<SchedulingTriggerVo> schedulingTriggers = schedulingTriggerService.selectSchedulingTriggerList(); // 스케줄링트리거 목록 조회
	
	        for (SchedulingTriggerVo schedulingTrigger : schedulingTriggers) {
	            // 사용여부 Y인 것만 다시등록
	            if ("Y".equals(schedulingTrigger.getUseYn())) {
	                try {
    	                Trigger trigger = (Trigger)applicationContext.getBean(schedulingTrigger.getNm());
    	                TriggerBuilder tb = trigger.getTriggerBuilder();
    	                trigger = tb.withSchedule(CronScheduleBuilder.cronSchedule(schedulingTrigger.getCronExpression()).withMisfireHandlingInstructionDoNothing()).build(); // 트리거 재생성(크론시간 반영)
    	                scheduler.scheduleJob(trigger); // 트리거 스케쥴링 추가
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
    	} else {
    	    try {
        	    // 전체 트리거 삭제
                scheduler.unscheduleJobs(new ArrayList<>(scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup())));
        	    scheduler.shutdown();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	}
    }
    
    /**
    * 서버종료 시그널 발생시 schedulerFactoryBean의 destroy를 선행하도록 한다.
    *
    * @Title       : gracefulShutdownHookForQuartz 
    * @Description : 서버종료 시그널 발생시 schedulerFactoryBean의 destroy를 선행하도록 한다.
    * @param schedulerFactoryBean schedulerFactoryBean객체
    * @return SmartLifecycle SmartLifecycle객체
    */
    @Bean 
    public SmartLifecycle gracefulShutdownHookForQuartz(SchedulerFactoryBean schedulerFactoryBean) { 
        return new SmartLifecycle() { 
            private boolean isRunning = false; 

            @Override 
            public boolean isAutoStartup() { 
                return true; 
            } 
            
            @Override 
            public void start() { 
                log.info("Quartz Graceful Shutdown Hook started."); 
                isRunning = true; 
            }
            
            @Override 
            public void stop(Runnable callback) { 
                stop(); 
                log.info("Spring container is shutting down."); 
                callback.run(); 
            }
            
            @Override
            public void stop() { 
                isRunning = false; 
                try { 
                    log.info("Quartz Graceful Shutdown... "); 
                    schedulerFactoryBean.destroy(); 
                } catch (SchedulerException e) { 
                    try { 
                        log.info("Error shutting down Quartz: " + e.getMessage(), e); 
                        schedulerFactoryBean.getScheduler().shutdown(false); 
                    } catch (SchedulerException ex) { 
                        log.error("Unable to shutdown the Quartz scheduler.", ex); 
                    } 
                } 
            } 
            
            @Override 
            public boolean isRunning() { 
                return isRunning; 
            } 
            
            @Override 
            public int getPhase() { 
                return Integer.MAX_VALUE; 
            } 
        }; 
    }
}
