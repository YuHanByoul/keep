package com.kbrainc.plum.rte.exception;

/**
 * 
 * RuntimeException 랩핑 exception 
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
public class CustomRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 325977045483385914L;

    /**
     * Desc : Constructor of AccessException.java class
     * 
     * @param message 에러메시지
     */
    public CustomRuntimeException(String message) {
        super(message);
    }
    
    public CustomRuntimeException(Throwable throwable) {
    	super(throwable);
    }
    
    public CustomRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}