package com.finance.app.notification.service.exception;

import com.finance.app.notification.service.dto.ResponseBody;
import com.finance.app.notification.service.exception.dto.ResponseStatusException;
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

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseBody<Object>> handleResponseStatus(ResponseStatusException ex) {
        return new ResponseEntity<>(ResponseBody.of(ex.getMessage(), null), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBody<Object>> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ResponseBody.of(ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
