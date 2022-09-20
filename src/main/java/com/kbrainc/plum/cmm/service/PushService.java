package com.kbrainc.plum.cmm.service;

import java.util.Map;

/**
 * <pre>
 * com.kbrainc.plum.cmm.service - PushService.java
 * </pre>
 *
 * @ClassName : PushService
 * @Description : 푸쉬발송 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 1.0
 * @Company : CopyrightⒸ KBRAINC. All Rights Reserved
 *
 */
public interface PushService {

    /**
    * 단일 푸쉬 발송(비동기).
    *
    * @Title       : send 
    * @Description : 단일 푸쉬 발송(비동기).
    * @param param 푸쉬발송관련 파라미터(title, body, token 필수)
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    void send (Map<String, Object> param) throws Exception;
    
    /**
    * 다중 푸쉬 발송(비동기).
    *
    * @Title       : send 
    * @Description : 다중 푸쉬 발송(비동기).
    * @param param 푸쉬발송관련 파라미터(title, body)
    * @param registrationIds 수신대상자 pushToken 배열
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    void send (Map<String, Object> param, String[] registrationIds) throws Exception;
}
