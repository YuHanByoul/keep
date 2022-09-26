package com.kbrainc.plum.rte.util.mail.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;

/**
 * 
 * 메일발송 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.rte.util.mail.service
 * - MailService.java
 * </pre> 
 *
 * @ClassName : MailService
 * @Description : 메일발송 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface MailService {

    /**
     * 
     * 단일 메일 발송(동기). 
     *
     * @Title : sendOneMail
     * @Description : 단일 메일 발송(동기).
     * @param mailVo
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
    Map<String, Object> sendMail(MailVo mailVo) throws Exception;
    
    /**
    * 다중 메일 발송(동기).
    *
    * @Title       : sendMultiMail 
    * @Description : 다중 메일 발송.
    * @param to 받는사람 이메일주소와 사용자아이디 리스트
    * @param mailVo
    * @throws Exception
    * @return Map<String,Object>
    */
    Map<String, Object> sendMultiMail(List<MailRcptnVo> to, MailVo mailVo) throws Exception;
    
    /**
    * 단일 메일 발송(비동기).
    *
    * @Title       : sendOneMailAsync
    * @Description : 단일 메일 발송.
    * @param to 받는사람 이메일주소
    * @param title 메일제목
    * @param contents 메일내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    void sendOneMailAsync(String to, String title, String contents) throws Exception;
    
    /**
    * 다중 메일 발송(비동기).
    *
    * @Title       : sendMultiMailAsync 
    * @Description : 다중 메일 발송.
    * @param to 받는사람 이메일주소 배열
    * @param title 메일제목
    * @param contents 메일내용
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    void sendMultiMailAsync(String[] to, String title, String contents) throws Exception;
}
