package com.kbrainc.plum.cmm.service;

import java.util.Map;

/**
 * 
 * 문자발송 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - SmsService.java
 * </pre> 
 *
 * @ClassName : SmsService
 * @Description : 문자발송 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
public interface SmsService {

    /**
    * 단일 SMS 발송(동기).
    *
    * @Title       : sendOneSms 
    * @Description : 단일 SMS 발송.
    * @param phone 받는사람 휴대전화번호
    * @param msg 내용
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    Map<String, Object> sendOneSms(String phone, String msg) throws Exception;
    
    /**
    * 다중 SMS 발송(동기).
    *
    * @Title       : sendMultiSms 
    * @Description : 다중 SMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    Map<String, Object> sendMultiSms(String[] phone, String msg) throws Exception;
    
    /**
    * 다중 SMS 발송(예약)(동기).
    *
    * @Title       : sendMultiReserveSms 
    * @Description : 다중 SMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @param reserveDate 예약발송일자
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    public Map<String, Object> sendMultiReserveSms(String[] phone, String msg, String reserveDate) throws Exception;
    
    /**
    * 단일 LMS 발송(동기).
    *
    * @Title       : sendOneLms 
    * @Description : 단일 LMS 발송.
    * @param phone 받는사람 휴대전화번호
    * @param msg 내용
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    Map<String, Object> sendOneLms(String phone, String msg) throws Exception;
    
    /**
    * 다중 LMS 발송(동기).
    *
    * @Title       : sendMultiLms 
    * @Description : 다중 LMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @return Map<String,Object> 발송결과
    * @throws Exception 예외
    */
    Map<String, Object> sendMultiLms(String[] phone, String msg) throws Exception;
    
    /**
    * 단일 SMS 발송(비동기).
    *
    * @Title       : sendOneSmsAsync 
    * @Description : 단일 SMS 발송.
    * @param phone 받는사람 휴대전화번호
    * @param msg 내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    void sendOneSmsAsync(String phone, String msg) throws Exception;
    
    /**
    * 다중 SMS 발송(비동기).
    *
    * @Title       : sendMultiSmsAsync 
    * @Description : 다중 SMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    void sendMultiSmsAsync(String[] phone, String msg) throws Exception;
    
    /**
    * 다중 SMS 발송(예약)(비동기).
    *
    * @Title       : sendMultiReserveSmsAsync 
    * @Description : 다중 SMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @param reserveDate 예약발송일자
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    void sendMultiReserveSmsAsync(String[] phone, String msg, String reserveDate) throws Exception;
    
    /**
    * 단일 LMS 발송(비동기).
    *
    * @Title       : sendOneLmsAsync 
    * @Description : 단일 LMS 발송.
    * @param phone 받는사람 휴대전화번호
    * @param msg 내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    void sendOneLmsAsync(String phone, String msg) throws Exception;
    
    /**
    * 다중 LMS 발송(비동기).
    *
    * @Title       : sendMultiLmsAsync 
    * @Description : 다중 LMS 발송.
    * @param phone 받는사람 휴대전화번호 배열
    * @param msg 내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    void sendMultiLmsAsync(String[] phone, String msg) throws Exception;
}