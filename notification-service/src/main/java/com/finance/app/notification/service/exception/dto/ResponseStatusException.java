package com.finance.app.notification.service.exception.dto;

import org.springframework.http.HttpStatus;

public class ResponseStatusException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private HttpStatus httpStatus;

    public ResponseStatusException() {
    }

    public ResponseStatusException(HttpStatus httpStatus, String msg) {
        super(msg);
        this.message = msg;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}