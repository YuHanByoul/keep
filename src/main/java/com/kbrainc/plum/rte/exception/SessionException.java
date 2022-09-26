package com.kbrainc.plum.rte.exception;

import javax.servlet.ServletException;

/**
 * 
 * Session이 유효하지 않은경우 에외 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.exception
 * - SessionException.java
 * </pre> 
 *
 * @ClassName : SessionException
 * @Description : Session이 유효하지 않은경우 에외 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class SessionException extends ServletException {

    private static final long serialVersionUID = 5846685548898210983L;

    /**
     * Desc : Constructor of SessionException.java class
     * 
     * @param message 에러메시지
     */
    public SessionException(String message) {
        super(message);
    }
}