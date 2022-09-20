package com.kbrainc.plum.config.trace;

import org.egovframe.rte.fdl.cmmn.trace.LeaveaTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceConfig {

    @Bean(name="leaveaTrace")
    public LeaveaTrace leaveaTrace() {
    	LeaveaTrace leaveaTrace = new LeaveaTrace();
    	return leaveaTrace;
    }
    
//  @Bean(name="leaveaTrace")
//  public LeaveaTrace leaveaTrace(DefaultTraceHandleManager traceHandlerService) {
//  	LeaveaTrace leaveaTrace = new LeaveaTrace();
//  	leaveaTrace.setTraceHandlerServices(new TraceHandlerService[] {traceHandlerService});
//  	return leaveaTrace;
//  }
    
    
}