package com.kbrainc.plum.rte.exception;

import javax.servlet.ServletException;

/**
 * 
 * 접근권한 에러발생 예외 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.exception
 * - AuthException.java
 * </pre> 
 *
 * @ClassName : AuthException
 * @Description : 접근권한 에러발생 예외 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class AuthException extends ServletException {

    private static final long serialVersionUID = 7639919041888220486L;

    /**
     * Desc : Constructor of AuthException.java class
     * 
     * @param message 에러메시지
     */
    public AuthException(String message) {
        super(message);
    }
}