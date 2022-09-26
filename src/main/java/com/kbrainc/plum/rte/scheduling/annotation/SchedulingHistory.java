package com.kbrainc.plum.rte.scheduling.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 스케줄링 시작/종료 이력을 남기기 위한 어노테이션.
 *
 * <pre>
 * com.kbrainc.plum.rte.scheduling.annotation
 * - SchedulingHistory.java
 * </pre> 
 *
 * @ClassName : SchedulingHistory
 * @Description : 스케줄링 시작/종료 이력을 남기기 위한 어노테이션.
 * @author : KBRAINC
 * @date : 2021. 3. 5.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SchedulingHistory {

}