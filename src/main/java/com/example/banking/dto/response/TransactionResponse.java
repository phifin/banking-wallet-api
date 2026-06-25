package com.example.banking.dto.response;

import com.example.banking.model.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public class TransactionResponse {
    private Long id;
    private TransactionType type;
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private Instant createdAt;

    public TransactionResponse() {
    }

    public TransactionResponse(Long id, TransactionType type, Long fromAccountId, Long toAccountId, BigDecimal amount, Instant createdAt) {
        this.id = id;
        this.type = type;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
