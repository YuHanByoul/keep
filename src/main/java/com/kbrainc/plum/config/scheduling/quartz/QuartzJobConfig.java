package com.kbrainc.plum.config.scheduling.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JobOneQuartzConfig {  
    @Bean
    public JobDetail jobOneDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 1);
        jobDataMap.put("jobName", "demoJobOne");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("demoJobOne",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger jobOneTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(jobOneDetail())
                .withIdentity("jobOneTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

@Configuration
class JobTwoQuartzConfig {  
    @Bean
    public JobDetail jobTwoDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 2);
        jobDataMap.put("jobName", "demoJobTwo");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("demoJobTwo",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger jobTwoTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(jobTwoDetail())
                .withIdentity("jobTwoTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

/**
* 스케줄링 배치잡(휴면계정처리) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - UserDrmncyQuartzConfig.java
* </pre>
*
* @ClassName   : UserDrmncyQuartzConfig 
* @Description : 스케줄링 배치잡(휴면계정처리) 설정 클래스. 
* @author      : KBRAINC
* @date        : 2021. 3. 8.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class UserDrmncyQuartzConfig {  
    @Bean
    public JobDetail userDrmncyJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 6);
        jobDataMap.put("jobName", "userDrmncyJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("userDrmncyJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger userDrmncyTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(userDrmncyJobDetail())
                .withIdentity("userDrmncyTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}