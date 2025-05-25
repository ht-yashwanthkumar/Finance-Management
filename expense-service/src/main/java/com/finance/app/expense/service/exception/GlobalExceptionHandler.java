package com.finance.app.expense.service.exception;

import com.finance.app.expense.service.dto.ResponseBody;
import com.finance.app.expense.service.exception.dto.BudgetExceededException;
import com.finance.app.expense.service.exception.dto.ServiceException;
import com.finance.app.expense.service.exception.dto.UserBudgetNotFoundException;
import com.finance.app.expense.service.exception.dto.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseBody<Object>> handleResponseStatus(ServiceException ex) {
        return new ResponseEntity<>(ResponseBody.of(ex.getMessage(), null), ex.getHttpStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseBody<Object>> handleResponseStatus(UserNotFoundException ex) {
        return new ResponseEntity<>(ResponseBody.of(ex.getMessage(), null), ex.getHttpStatus());
    }

    @ExceptionHandler(BudgetExceededException.class)
    public ResponseEntity<ResponseBody<Object>> handleResponseStatus(BudgetExceededException ex) {
        return new ResponseEntity<>(ResponseBody.of(ex.getMessage(), null), ex.getHttpStatus());
    }

    @ExceptionHandler(UserBudgetNotFoundException.class)
    public ResponseEntity<ResponseBody<Object>> handleResponseStatus(UserBudgetNotFoundException ex) {
        return new ResponseEntity<>(ResponseBody.of(ex.getMessage(), null), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBody<Object>> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ResponseBody.of(ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
