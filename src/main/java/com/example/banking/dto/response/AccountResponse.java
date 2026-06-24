package com.example.banking.dto.response;

import com.example.banking.model.AccountStatus;

import java.math.BigDecimal;
import java.time.Instant;

public class AccountResponse {
    private Long id;
    private Long userId;
    private String accountNumber;
    private BigDecimal balance;
    private AccountStatus status;
    private Instant createdAt;

    public AccountResponse() {
    }

    public AccountResponse(Long id, Long userId, String accountNumber, BigDecimal balance, AccountStatus status, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = status;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
