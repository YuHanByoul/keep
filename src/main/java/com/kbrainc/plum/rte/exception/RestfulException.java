package com.kbrainc.plum.rte.exception;

public class RestfulException extends RuntimeException {

    /** serial UID.  */
    private static final long serialVersionUID = 5154326149894999164L;

    public RestfulException(String msg) {
        super(msg);
    }

    public RestfulException() {
        super();
    }
}
