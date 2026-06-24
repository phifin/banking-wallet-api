package com.example.banking.dto.request;

public class CreateAccountRequest {
    private Long userId;

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
