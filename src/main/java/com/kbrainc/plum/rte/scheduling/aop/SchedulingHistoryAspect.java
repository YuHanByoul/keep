package com.kbrainc.plum.rte.scheduling.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;

import javax.sql.DataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.rte.exception.CustomRuntimeException;
import com.kbrainc.plum.rte.scheduling.annotation.Triggerid;

/**
* 스케줄링 이력을 남기기위한 AOP설정.
*
* <pre>
* com.kbrainc.plum.rte.scheduling.aop
* - SchedulingHistoryAspect.java
* </pre>
*
* @ClassName   : SchedulingHistoryAspect 
* @Description : 스케줄링 이력을 남기기위한 AOP설정. 
* @author      : KBRAINC
* @date        : 2021. 3. 5.
* @Version     :  
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Aspect
@Configuration
public class SchedulingHistoryAspect {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

	@Around("@annotation(com.kbrainc.plum.rte.scheduling.annotation.SchedulingHistory)")
    public Object aroundTargetMethod(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] args = thisJoinPoint.getArgs();
        
    	int triggerid = findTriggerid(parameters, args);
    	Transactional transactional = method.getDeclaredAnnotation(Transactional.class); // target메소드에서 트랜젝션처리를 위해 @Transaction을 사용한다는 전제

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        
        try {
        	KeyHolder keyHolder = new GeneratedKeyHolder();
        	paramSource.addValue("triggerid", triggerid);
        	paramSource.addValue("now", new Date());
            this.namedParameterJdbcTemplate.update("INSERT INTO TB_CMM_SCHED_HIST(TRIGGERID, JOB_STRT_DT) VALUES(:triggerid, :now)", paramSource, keyHolder);
            
            if (transactional != null) {
            	this.namedParameterJdbcTemplate.update("COMMIT", paramSource);
            }
            
            Number key = keyHolder.getKey();
            if ( key != null) {
                paramSource.addValue("sched_hist_id", key.intValue());
            }
            
            Object result = null;
            paramSource.addValue("now", new Date());
            result = thisJoinPoint.proceed();            
            this.namedParameterJdbcTemplate.update("UPDATE TB_CMM_SCHED_HIST SET JOB_END_DT = :now, STTS_CD = 'S' WHERE SCHED_HIST_ID = :sched_hist_id", paramSource);

            return result;
        } catch (CustomRuntimeException throwable) {
        	if (transactional != null) {
        		this.namedParameterJdbcTemplate.update("ROLLBACK", paramSource);
            }
        	paramSource.addValue("now", new Date());
        	this.namedParameterJdbcTemplate.update("UPDATE TB_CMM_SCHED_HIST SET JOB_END_DT = :now, STTS_CD = 'F' WHERE SCHED_HIST_ID = :sched_hist_id", paramSource);
        	if (transactional != null) {
        		this.namedParameterJdbcTemplate.update("COMMIT", paramSource);
        	}
        	throw new CustomRuntimeException(throwable);
        }
    }
	
	private int findTriggerid(Parameter[] parameters, Object[] args) {
        for (int i = 0; i < parameters.length; i++) {
            Triggerid triggerid = parameters[i].getDeclaredAnnotation(Triggerid.class);
            if (triggerid != null) {
                return (int) args[i];
            }
        }
        throw new CustomRuntimeException("can't find Triggerid");
    }
}
