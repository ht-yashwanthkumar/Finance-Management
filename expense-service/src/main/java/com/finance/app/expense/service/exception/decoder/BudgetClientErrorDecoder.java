package com.finance.app.expense.service.exception.decoder;

import com.finance.app.expense.service.exception.dto.ServiceException;
import com.finance.app.expense.service.exception.dto.UserBudgetNotFoundException;
import com.finance.app.expense.service.exception.dto.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class BudgetClientErrorDecoder implements ErrorDecoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetClientErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        LOGGER.debug("Entered into decode method");
        LOGGER.debug("methodKey::{} and response::{}", methodKey, response);
        return switch (response.status()) {
            case 404 -> new UserBudgetNotFoundException(HttpStatus.NOT_FOUND, "Budget not found for the user");
            case 503 -> new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, "Service temporarily unavailable");
            //
            //
            default -> new Exception("Generic error");
        };
    }
}
