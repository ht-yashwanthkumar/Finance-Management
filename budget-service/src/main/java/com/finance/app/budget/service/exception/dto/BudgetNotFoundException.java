package com.finance.app.budget.service.exception.dto;

import org.springframework.http.HttpStatus;

public class BudgetNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private HttpStatus httpStatus;

    public BudgetNotFoundException() {
    }

    public BudgetNotFoundException(HttpStatus httpStatus, String msg) {
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