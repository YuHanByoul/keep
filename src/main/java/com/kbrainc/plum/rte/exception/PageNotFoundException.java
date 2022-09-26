package com.kbrainc.plum.rte.exception;

import javax.servlet.ServletException;

/**
 * 
 * 페이지를 찾을수없을때 예외 클래스
 *
 * <pre>
 * com.kbrainc.plum.rte.exception
 * - PageNotFoundException.java
 * </pre> 
 *
 * @ClassName : PageNotFoundException
 * @Description : 페이지를 찾을수없을때 예외 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class PageNotFoundException extends ServletException {

    private static final long serialVersionUID = 8650203043556051982L;

    /**
     * Desc : Constructor of PageNotFoundException.java class
     * 
     * @param message 에러메시지
     */
    public PageNotFoundException(String message) {
        super(message);
    }
}