package com.finance.app.budget.service.exception.dto;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        LOGGER.debug("Entered into decode method");
        LOGGER.debug("methodKey::{} and response::{}", methodKey, response);
        return switch (response.status()) {
            case 503 -> new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, "Service temporarily unavailable");
            case 404 -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found");
            //
            default -> new Exception("Generic error");
        };
    }
}
