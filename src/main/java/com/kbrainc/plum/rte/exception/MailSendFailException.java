package com.kbrainc.plum.rte.exception;

/**
 * 
 * 메일 발송 실패시 발생시킬 예외 정의.
 *
 * <pre>
 * com.kbrainc.plum.rte.exception
 * - MailSendFailException.java
 * </pre> 
 *
 * @ClassName : MailSendFailException
 * @Description : 메일 발송 실패시 발생시킬 예외 정의
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class MailSendFailException extends RuntimeException {

    private static final long serialVersionUID = 1623998126072378003L;

    public MailSendFailException() {
        super();
    }

    public MailSendFailException(String message) {
        super(message);
    }
}