package com.kbrainc.plum.cmm.service;

import java.util.Map;

/**
 * 
 * NHN 문자 발송 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - SmsNhnService.java
 * </pre> 
 *
 * @ClassName : SmsNhnService
 * @Description : 문자 발송 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2023. 2. 1.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface SmsNhnService {

    /**
    * 단문 SMS 발송
    *
    * @Title : sendSms 
    * @Description : 단문 SMS 발송
    * @param msg 내용
    * @param phoneList 수신자번호 목록
    * @return Map<String, Object> 발송결과
    * @throws Exception 예외
    */
    Map<String, Object> sendSms(String msg, String[] phoneList) throws Exception;
    
    /**
     * 장문 MMS 발송
     *
     * @Title : sendMms 
     * @Description : 장문 MMS 발송
     * @param title 제목
     * @param msg 내용
     * @param phoneList 수신자번호 목록
     * @return Map<String, Object> 발송결과
     * @throws Exception 예외
     */
     Map<String, Object> sendMms(String title, String msg, String[] phoneList) throws Exception;
     
     /**
      * 인증용(긴급) SMS 발송
      *
      * @Title : sendAuth 
      * @Description : 인증용(긴급) SMS 발송
      * @param msg 내용
      * @param phoneList 수신자번호 목록
      * @return Map<String, Object> 발송결과
      * @throws Exception 예외
      */
      Map<String, Object> sendAuth(String msg, String[] phoneList) throws Exception;
    
}