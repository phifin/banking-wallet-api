package com.example.banking.exception;

import com.example.banking.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception){
        HttpStatus status =  switch (exception.getErrorCode()){
            case INVALID_REQUEST -> HttpStatus.BAD_REQUEST;
            case DUPLICATE_EMAIL -> HttpStatus.CONFLICT;
            case USER_NOT_FOUND -> HttpStatus.NOT_FOUND;
        };

        ErrorResponse errorResponse = new ErrorResponse(false, exception.getErrorCode(), exception.getMessage());

        return ResponseEntity.status(status).body(errorResponse);
    }

}
