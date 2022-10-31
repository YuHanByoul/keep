package com.kbrainc.plum.rte.exception;

import javax.servlet.ServletException;

/**
 * 
 * Exception 랩핑 exception 
 *
 * <pre>
 * com.kbrainc.plum.rte.CustomRuntimeException
 * - AccessException.java
 * </pre> 
 *
 * @ClassName : CustomRuntimeException
 * @Description : RuntimeException 랩핑 예외 클래스(raw RuntimeException 사용 하지 않기 윈한 custom Exception)
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class CustomException extends Exception {

    private static final long serialVersionUID = 325977045483385914L;

    /**
     * Desc : Constructor of AccessException.java class
     * 
     * @param message 에러메시지
     */
    public CustomException(String message) {
        super(message);
    }
    
    public CustomException(Throwable throwable) {
    	super(throwable);
    }
    
    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }
}