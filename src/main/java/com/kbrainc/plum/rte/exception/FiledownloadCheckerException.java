package com.kbrainc.plum.rte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class FiledownloadCheckerException extends RuntimeException {

    private static final long serialVersionUID = -763168558528365048L;

    public FiledownloadCheckerException(String message) {
        super(message);
    }

    public FiledownloadCheckerException(String message, Throwable cause) {
        super(message, cause);
    }
}