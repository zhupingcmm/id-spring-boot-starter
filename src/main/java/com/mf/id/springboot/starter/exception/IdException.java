package com.mf.id.springboot.starter.exception;

public class IdException extends RuntimeException{

    public IdException(String message) {
        super(message);
    }

    public IdException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
