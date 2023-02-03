package com.kbrainc.plum.cmm.service;

import java.util.Map;

/**
 * 
 * NHN 알림톡 발송 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - AlimtalkNhnService.java
 * </pre> 
 *
 * @ClassName : AlimtalkNhnService
 * @Description : 알림톡 발송 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2023. 2. 2.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface AlimtalkNhnService {

    /**
    * 알림톡 발송(일반 메세지)
    *
    * @Title : sendAlimtalk 
    * @Description : 알림톡 발송
    * @param templateCode 템플릿코드
    * @param recipientList 수신자 목록
    * @return Map<String, Object> 발송결과
    * @throws Exception 예외
    */
    Map<String, Object> sendAlimtalk(String templateCode, String recipientList) throws Exception;
    
}