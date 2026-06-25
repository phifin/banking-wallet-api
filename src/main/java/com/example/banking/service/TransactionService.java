package com.example.banking.service;

import com.example.banking.dto.request.DepositRequest;
import com.example.banking.dto.response.TransactionResponse;
import com.example.banking.exception.BusinessException;
import com.example.banking.exception.ErrorCode;
import com.example.banking.model.Account;
import com.example.banking.model.AccountStatus;
import com.example.banking.model.Transaction;
import com.example.banking.model.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class TransactionService {
    private final Map<Long, Transaction> transactions = new LinkedHashMap<>();
    private Long nextId = 1L;
    private final AccountService accountService;

    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
    }

    public TransactionResponse deposit(DepositRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Request body is required");
        }

        if (request.getAccountId() == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Account ID is required");
        }

        if (request.getAmount() == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Amount is required");
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Amount must be greater than zero");
        }

        Account account = accountService.getAccountModelById(request.getAccountId());
        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new BusinessException(ErrorCode.ACCOUNT_NOT_ACTIVE, "Account is not active");
        }

        accountService.increaseBalance(request.getAccountId(), request.getAmount());
        Long id = nextId;
        nextId++;

        Transaction transaction = new Transaction(
                id,
                TransactionType.DEPOSIT,
                null,
                account.getId(),
                request.getAmount(),
                Instant.now()
        );

        transactions.put(id, transaction);

        return toTransactionResponse(transaction);
    }

    private TransactionResponse toTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getType(),
                transaction.getFromAccountId(),
                transaction.getToAccountId(),
                transaction.getAmount(),
                transaction.getCreatedAt()
        );
    }
}
