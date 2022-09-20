package com.kbrainc.plum.cmm.log.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAopController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAopController.class);
    

    /**
     * 
     * @GetMappoing 어노테이션이 설정된 곳에 적용함. 
     *
     * @Title : getMapping
     * @Description : 
     * @return void
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void RequestMapping() {
        
    }
    
    @Before("RequestMapping()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        
        LOGGER.info("======================== ASPECT : {} Before Logging ========================", methodName);
    }
    
    @AfterReturning(pointcut = "RequestMapping()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        LOGGER.info("======================== ASPECT : {} AfterReturning Logging ========================", methodName);
    }
    
    @Around("RequestMapping()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        LOGGER.info("======================== ASPECT : {} Around Logging Start========================", methodName);
        try {
            Object result = joinPoint.proceed();
            LOGGER.info("======================== ASPECT : {} Around Logging End========================", methodName);
            return result;
        }catch (Exception e) {
            LOGGER.error("======================== ASPECT : {} Around Exception========================", methodName);
            LOGGER.error(e.toString());
            return null;
        }
    }
}
