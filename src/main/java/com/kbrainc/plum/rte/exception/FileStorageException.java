package com.kbrainc.plum.rte.exception;

public class FileStorageException extends RuntimeException {

    private static final long serialVersionUID = 2950677808302639988L;

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}