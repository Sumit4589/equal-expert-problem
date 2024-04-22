package com.equal.expert.shopping.exception;

public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String msg, Exception ex) {
        super(msg, ex);
    }
}
