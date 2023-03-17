package com.kbrainc.plum.config.scheduling.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

/**
* 스케줄링 배치잡(유아환경교육 안내 메시지 발송) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - InfntEduNtcMsgQuartzConfig.java
* </pre>
*
* @ClassName   : InfntEduNtcMsgQuartzConfig 
* @Description : 스케줄링 배치잡(유아환경교육 안내 메시지 발송) 설정 클래스. 
* @author      : KBRAINC
* @date        : 2023. 3. 13.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class InfntEduNtcMsgQuartzConfig {  
    @Bean
    public JobDetail infntEduMsgJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 7);
        jobDataMap.put("jobName", "infntEduMsgJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("infntEduMsgJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger infntEduMsgTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(infntEduMsgJobDetail())
                .withIdentity("infntEduMsgTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

/**
* 스케줄링 배치잡(푸름이 이동 환경교육 안내 메시지 발송) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - MvnEduNtcMsgQuartzConfig.java
* </pre>
*
* @ClassName   : MvnEduNtcMsgQuartzConfig 
* @Description : 스케줄링 배치잡(푸름이 이동 환경교육 안내 메시지 발송) 설정 클래스. 
* @author      : KBRAINC
* @date        : 2023. 3. 13.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class MvnEduNtcMsgQuartzConfig {  
    @Bean
    public JobDetail mvnEduMsgJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 8);
        jobDataMap.put("jobName", "mvnEduMsgJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("mvnEduMsgJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger mvnEduMsgTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(mvnEduMsgJobDetail())
                .withIdentity("mvnEduMsgTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

/**
* 스케줄링 배치잡(휴면계정 전환 사전 안내) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - UserDrmncyNtcMailSendQuartzConfig.java
* </pre>
*
* @ClassName   : UserDrmncyNtcMailSendQuartzConfig 
* @Description : 스케줄링 배치잡(휴면계정 전환 사전 안내) 설정 클래스.
* @author      : KBRAINC
* @date        : 2023. 3. 13.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class UserDrmncyNtcMailSendQuartzConfig {  
    @Bean
    public JobDetail drmncyNtcMailJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 9);
        jobDataMap.put("jobName", "drmncyNtcMailJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("drmncyNtcMailJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger drmncyNtcMailTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(drmncyNtcMailJobDetail())
                .withIdentity("drmncyNtcMailTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

/**
* 스케줄링 배치잡(3개월 이상된 알림메시지 삭제) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - OldNoticeMessageQuartzConfig.java
* </pre>
*
* @ClassName   : OldNoticeMessageQuartzConfig 
* @Description : 스케줄링 배치잡(3개월 이상된 알림메시지 삭제) 설정 클래스.
* @author      : KBRAINC
* @date        : 2023. 3. 13.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class OldNoticeMessageQuartzConfig {  
    @Bean
    public JobDetail oldNtcMsgJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 10);
        jobDataMap.put("jobName", "oldNtcMsgJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("oldNtcMsgJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger oldNtcMsgTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(oldNtcMsgJobDetail())
                .withIdentity("oldNtcMsgTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

/**
* 스케줄링 배치잡(푸름이 이동환경교실 만족도 조사 안내 메시지 발송) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - MvnDgstfnMsgQuartzConfig.java
* </pre>
*
* @ClassName   : MvnDgstfnMsgQuartzConfig 
* @Description : 스케줄링 배치잡(푸름이 이동환경교실 만족도 조사 안내 메시지 발송) 설정 클래스.
* @author      : KBRAINC
* @date        : 2023. 3. 13.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class MvnDgstfnMsgQuartzConfig {  
    @Bean
    public JobDetail mvnDgstfnJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 11);
        jobDataMap.put("jobName", "mvnDgstfnJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("mvnDgstfnJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger mvnDgstfnTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(mvnDgstfnJobDetail())
                .withIdentity("mvnDgstfnTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

/**
* 스케줄링 배치잡(유아환경교육 만족도 조사 안내 메시지 발송) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - InfntDgstfnMsgQuartzConfig.java
* </pre>
*
* @ClassName   : InfntDgstfnMsgQuartzConfig 
* @Description : 스케줄링 배치잡(유아환경교육 만족도 조사 안내 메시지 발송) 설정 클래스.
* @author      : KBRAINC
* @date        : 2023. 3. 13.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class InfntDgstfnMsgQuartzConfig {  
    @Bean
    public JobDetail infntDgstfnJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 12);
        jobDataMap.put("jobName", "infntDgstfnJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("infntDgstfnJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger infntDgstfnTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(infntDgstfnJobDetail())
                .withIdentity("infntDgstfnTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

/**
* 스케줄링 배치잡(전문가 섭외자와 대상 전문가 대상 교육 알림 메시지 발송) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - ExprtEduBfrSendMsgQuartzConfig.java
* </pre>
*
* @ClassName   : ExprtEduBfrSendMsgQuartzConfig 
* @Description : 스케줄링 배치잡(전문가 섭외자와 대상 전문가 대상 교육 알림 메시지 발송) 설정 클래스.
* @author      : KBRAINC
* @date        : 2023. 3. 13.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class ExprtEduBfrSendMsgQuartzConfig {  
    @Bean
    public JobDetail exprtBfrMsgJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 13);
        jobDataMap.put("jobName", "exprtBfrMsgJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("exprtBfrMsgJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger exprtBfrMsgTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(exprtBfrMsgJobDetail())
                .withIdentity("exprtBfrMsgTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

/**
* 스케줄링 배치잡(전문가 섭외자 대상 만족도 평가 안내 메시지 발송) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - ExprtEduAftrSendMsgQuartzConfig.java
* </pre>
*
* @ClassName   : ExprtEduAftrSendMsgQuartzConfig 
* @Description : 스케줄링 배치잡(전문가 섭외자 대상 만족도 평가 안내 메시지 발송) 설정 클래스.
* @author      : KBRAINC
* @date        : 2023. 3. 13.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class ExprtEduAftrSendMsgQuartzConfig {  
    @Bean
    public JobDetail exprtAftrMsgJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 14);
        jobDataMap.put("jobName", "exprtAftrMsgJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("exprtAftrMsgJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger exprtAftrMsgTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(exprtAftrMsgJobDetail())
                .withIdentity("exprtAftrMsgTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

/**
* 스케줄링 배치잡(미입금 시설예약 취소처리) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - FlctReservCancleQuartzConfig.java
* </pre>
*
* @ClassName   : FlctReservCancleQuartzConfig 
* @Description : 스케줄링 배치잡(미입금 시설예약 취소처리) 설정 클래스.
* @author      : KBRAINC
* @date        : 2023. 3. 13.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class FlctReservCancleQuartzConfig {  
    @Bean
    public JobDetail flctRsvCnclJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 15);
        jobDataMap.put("jobName", "flctRsvCnclJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("flctRsvCnclJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger flctRsvCnclTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(flctRsvCnclJobDetail())
                .withIdentity("flctRsvCnclTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

/**
* 스케줄링 배치잡(시설 이용자 대상 만족도 평가 안내 메시지 발송) 설정 클래스.
*
* <pre>
* com.kbrainc.plum.config.scheduling.quartz
* - FlctReservDgstfnNtcMsgSendQuartzConfig.java
* </pre>
*
* @ClassName   : FlctReservDgstfnNtcMsgSendQuartzConfig 
* @Description : 스케줄링 배치잡(시설 이용자 대상 만족도 평가 안내 메시지 발송) 설정 클래스.
* @author      : KBRAINC
* @date        : 2023. 3. 13.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Configuration
class FlctReservDgstfnNtcMsgSendQuartzConfig {  
    @Bean
    public JobDetail flctRsvDgstfnJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerid", 16);
        jobDataMap.put("jobName", "flctRsvDgstfnJob");
         
        return JobBuilder.newJob(QuartzExecConfig.class)
                .withIdentity("flctRsvDgstfnJob",null)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }
     
    @Bean
    public Trigger flctRsvDgstfnTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("59 59 23 31 12 ? 2119"); // 초기설정이며 서버 구동시 DB에서 불러온 값으로 재설정 된다.
 
        return TriggerBuilder
                .newTrigger()
                .forJob(flctRsvDgstfnJobDetail())
                .withIdentity("flctRsvDgstfnTrigger",null)
                .withSchedule(scheduleBuilder)
                .build();
    }
}