package com.example.banking.dto.response;

import com.example.banking.exception.ErrorCode;

public class ErrorResponse {
    private boolean success;
    private ErrorCode errorCode;
    private String message;

    public ErrorResponse(){

    }
    public ErrorResponse(boolean success, ErrorCode errorCode, String message) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
