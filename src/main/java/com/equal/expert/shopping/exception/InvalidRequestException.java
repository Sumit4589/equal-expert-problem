package com.equal.expert.shopping.exception;

import java.net.URISyntaxException;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String msg, Exception ex) {
        super(msg, ex);
    }
}
