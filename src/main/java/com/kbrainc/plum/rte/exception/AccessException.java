package com.kbrainc.plum.rte.exception;

import javax.servlet.ServletException;

/**
 * 
 * Access도중 에러발생 예외 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.exception
 * - AccessException.java
 * </pre> 
 *
 * @ClassName : AccessException
 * @Description : Access도중 에러발생 예외 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
public class AccessException extends ServletException {

    private static final long serialVersionUID = 325977045483385914L;

    /**
     * Desc : Constructor of AccessException.java class
     * 
     * @param message 에러메시지
     */
    public AccessException(String message) {
        super(message);
    }
}