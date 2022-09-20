package com.kbrainc.plum.rte.exception;

/**
 * 
 * 필수 파라미터가 존재하지 않을 때 발생시킬 예외 정의...
 *
 * <pre>
 * com.kbrainc.plum.rte.exception
 * - ParameterNotFoundException.java
 * </pre> 
 *
 * @ClassName : ParameterNotFoundException
 * @Description : 필수 파라미터가 존재하지 않을 때 발생시킬 예외 정의..
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
public class ParameterNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1623998126072378003L;

    public ParameterNotFoundException() {
        super();
    }

    public ParameterNotFoundException(String message) {
        super(message);
    }
}