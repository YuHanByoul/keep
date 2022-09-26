package com.kbrainc.plum.mng.scheduling.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.scheduling.model.SchedulingHistVo;
import com.kbrainc.plum.mng.scheduling.model.SchedulingVo;
import com.kbrainc.plum.mng.scheduling.service.SchedulingService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 
* 스케쥴링 관리 컨트롤러
*
* <pre>
* com.kbrainc.plum.mng.scheduling.controller
* - SchedulingController.java
* </pre> 
*
* @ClassName : SchedulingController
* @Description : 스케쥴링 관리 컨트롤러
* @author : KBRAINC
* @date : 2021. 3. 17.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Controller
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;
    
    @Autowired 
    private SchedulerFactoryBean schedulerFactory;
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Value("${spring.quartz.auto-startup}")
    private boolean enabled;
    
    /**
    * 스케줄링 관리 화면.
    *
    * @Title       : schedulingList 
    * @Description : 스케줄링 관리 화면
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/scheduling/schedulingList.html")
    public String schedulingList() throws Exception {
        return "mng/scheduling/schedulingList";
    }
    
    /**
    * 스케즐트리거 목록 조회.
    *
    * @Title       : selectSchedTriggerList 
    * @Description : 스케줄트리거 목록 조회.
    * @param schedulingVo SchedulingVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/scheduling/selectSchedTriggerList.do")
    @ResponseBody
    public Map<String, Object> selectSchedTriggerList(SchedulingVo schedulingVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SchedulingVo> result = schedulingService.selectSchedTriggerList(schedulingVo);
        
        boolean isActive = false;
        for (SchedulingVo item : result) {
            isActive = enabled ? schedulerFactory.getScheduler().checkExists(new TriggerKey(item.getNm())) : false; // 트리거의 활성여부 조회
            if (isActive) {
                item.setActive_yn("Y"); 
            } else {
                item.setActive_yn("N");
            }
        }
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 스케줄링 트리거 등록화면 이동.
    *
    * @Title       : schedulingForm 
    * @Description : 스케줄링 트리거 등록화면 이동.
    * @return String 이동화면경오
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/scheduling/schedulingForm.html")
    public String schedulingForm() throws Exception {
        return "mng/scheduling/schedulingForm.html";
    }
    
    /**
    * 스케줄링 트리거 수정화면 이동.
    *
    * @Title       : schedulingUpdate 
    * @Description : 스케줄링 트리거 수정화면 이동.
    * @param schedulingVo SchedulingVo객체
    * @param model 모델객체
    * @return String 이동화면경오
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/scheduling/schedulingUpdate.html")
    public String schedulingUpdate(SchedulingVo schedulingVo, Model model) throws Exception {
        SchedulingVo schedulingInfo = schedulingService.selectTriggerInfo(schedulingVo); // 트리거 정보 조회
        
        boolean isActive = enabled ? schedulerFactory.getScheduler().checkExists(new TriggerKey(schedulingInfo.getNm())) : false; // 트리거의 활성여부 조회
        
        if (isActive) {
            schedulingInfo.setActive_yn("Y"); 
        } else {
            schedulingInfo.setActive_yn("N");
        }
        model.addAttribute("schedInfo", schedulingInfo);
        
        return "mng/scheduling/schedulingUpdate.html";
    }
    
    /**
    * 스케즐이력 목록 조회.
    *
    * @Title       : selectSchedHistList 
    * @Description : 스케즐이력 목록 조회.
    * @param schedulingHistVo SchedulingHistVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/scheduling/selectSchedHistList.do")
    @ResponseBody
    public Map<String, Object> selectSchedHistList(SchedulingHistVo schedulingHistVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SchedulingHistVo> result = schedulingService.selectSchedHistList(schedulingHistVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 스케줄트리거 등록.
    *
    * @Title       : insertSchedTrigger 
    * @Description : 스케줄트리거 등록.
    * @param schedulingVo SchedulingVo객체
    * @param bindingResult schedulingVo 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/scheduling/insertSchedTrigger.do")
    @ResponseBody
    public Map<String, Object> insertSchedTrigger(@Valid SchedulingVo schedulingVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            resultMap.put("msg", fieldError.getDefaultMessage());
            return resultMap;
        }
        
        schedulingVo.setUser(user);

        if (schedulingService.selectTriggerByNm(schedulingVo) != null) { // 중복된 트리거명인지 확인
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "중복된 트리거명이 존재하여 등록에 실패하였습니다.");
            return resultMap;
        }
        
        try {
            new CronExpression(schedulingVo.getCron_expression());
        } catch (ParseException e) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "크론표현식이 잘못되어 등록에 실패하였습니다.");
            return resultMap;
        }
        
        int retVal = 0;
        retVal = schedulingService.insertSchedTrigger(schedulingVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다.");
        }

        return resultMap;
    }
    
    /**
    * 스케줄트리거 수정.
    *
    * @Title       : UpdateSchedTrigger 
    * @Description : 스케줄트리거 수정.
    * @param schedulingVo SchedulingVo객체
    * @param bindingResult schedulingVo 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/scheduling/updateSchedTrigger.do")
    @ResponseBody
    public Map<String, Object> updateSchedTrigger(@Valid SchedulingVo schedulingVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            resultMap.put("msg", fieldError.getDefaultMessage());
            return resultMap;
        }
        
        try {
            new CronExpression(schedulingVo.getCron_expression());
        } catch (ParseException e) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "크론표현식이 잘못되어 등록에 실패하였습니다.");
            return resultMap;
        }
        
        schedulingVo.setUser(user);

        SchedulingVo schedulingInfo = schedulingService.selectTriggerInfo(schedulingVo); // 트리거 정보 조회
        
        Scheduler scheduler = schedulerFactory.getScheduler();
        boolean isActive = enabled ? scheduler.checkExists(new TriggerKey(schedulingInfo.getNm())) : false; // 트리거의 활성여부 조회
        String triggerNmParam = schedulingVo.getNm();
        
        if (!isActive && !schedulingInfo.getNm().equals(schedulingVo.getNm())) { // 활성화상태가 아니면서 트리거명 수정했을떄
            if (schedulingService.selectTriggerByNm(schedulingVo) != null) { // 중복된 트리거명인지 확인
                resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                resultMap.put("msg", "중복된 트리거명이 존재하여 수정에 실패하였습니다.");
                return resultMap;
            }
        } else {
            schedulingVo.setNm(null);
        }
        
        if (isActive) {
            boolean isChangedUseN = "Y".equals(schedulingInfo.getUse_yn()) && "N".equals(schedulingVo.getUse_yn()); // 사용에서 미사용으로 바뀐경우
            boolean isChangedCronExp = !schedulingInfo.getCron_expression().equals(schedulingVo.getCron_expression()); // 크론표현식이 바뀐경우
            if (isChangedUseN || isChangedCronExp) { 
                scheduler.unscheduleJob(new TriggerKey(schedulingInfo.getNm())); // 트리거삭제
            }
            if (isChangedCronExp) {
                Trigger trigger = (Trigger)applicationContext.getBean(schedulingInfo.getNm());
                TriggerBuilder tb = trigger.getTriggerBuilder();
                trigger = tb.withSchedule(CronScheduleBuilder.cronSchedule(schedulingVo.getCron_expression()).withMisfireHandlingInstructionDoNothing()).build(); // 트리거 재생성(크론시간 반영)
                scheduler.scheduleJob(trigger);
            }
        } else {
            boolean isChangedUseY = "N".equals(schedulingInfo.getUse_yn()) && "Y".equals(schedulingVo.getUse_yn()); // 미사용에서 사용으로 바뀐경우
            if (isChangedUseY) {
                Trigger trigger = null;
                try {
                    trigger = (Trigger)applicationContext.getBean(triggerNmParam);
                    
                    if (enabled) {
                        TriggerBuilder tb = trigger.getTriggerBuilder();
                        trigger = tb.withSchedule(CronScheduleBuilder.cronSchedule(schedulingVo.getCron_expression()).withMisfireHandlingInstructionDoNothing()).build(); // 트리거 재생성(크론시간 반영)
                        scheduler.scheduleJob(trigger);
                    }
                } catch (Exception e) {
                    if (e instanceof NoSuchBeanDefinitionException) {
                        resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                        resultMap.put("msg", "트리거가 존재하지않아 수정에 실패하였습니다.");
                        return resultMap;
                    }
                }
            }
        }
        
        int retVal = 0;
        retVal = schedulingService.updateSchedTrigger(schedulingVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다.");
        }

        return resultMap;
    } 
    
    /**
    * 스케줄트리거 수동실행.
    *
    * @Title       : execSchedTrigger 
    * @Description : 스케줄트리거 수동실행.
    * @param schedulingVo SchedulingVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/scheduling/execSchedTrigger.do")
    @ResponseBody
    public Map<String, Object> execSchedTrigger(SchedulingVo schedulingVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        SchedulingVo schedulingInfo = schedulingService.selectTriggerInfo(schedulingVo); // 트리거 정보 조회
        Scheduler scheduler = schedulerFactory.getScheduler();
        boolean isActive = enabled ? scheduler.checkExists(new TriggerKey(schedulingInfo.getNm())) : false; // 트리거의 활성여부 조회
        boolean retVal = false;
        
        if (isActive) {
            try {
                scheduler.triggerJob(scheduler.getTrigger(new TriggerKey(schedulingInfo.getNm())).getJobKey());
                retVal = true;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        
        if (retVal) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "실행에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "실행에 실패하였습니다.");
        }

        return resultMap;
    }
}