package com.example.banking.service;

import com.example.banking.dto.request.CreateAccountRequest;
import com.example.banking.dto.response.AccountResponse;
import com.example.banking.exception.BusinessException;
import com.example.banking.exception.ErrorCode;
import com.example.banking.model.Account;
import com.example.banking.model.AccountStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    private final Map<Long, Account> accounts = new LinkedHashMap<>();
    private Long nextId = 1L;
    private final UserService userService;

    public AccountService(UserService userService) {
        this.userService = userService;
    }

    public AccountResponse createAccount(CreateAccountRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Request body is required");
        }

        if (request.getUserId() == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "User ID is required");
        }

        if (!userService.existsById(request.getUserId())) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "User not found");
        }

        Long newId = nextId;
        nextId++;
        String newAccountNumber = "100000000" + newId;
        BigDecimal defaultAccountBalance = BigDecimal.ZERO;
        AccountStatus defaultAccountStatus = AccountStatus.ACTIVE;
        Instant createdAt = Instant.now();

        Account newAccount = new Account(
                newId,
                request.getUserId(),
                newAccountNumber,
                defaultAccountBalance,
                defaultAccountStatus,
                createdAt
        );

        accounts.put(newId, newAccount);

        return toAccountResponse(newAccount);
    }

    public AccountResponse getAccountById(Long id) {
        Account account = getAccountModelById(id);

        return toAccountResponse(account);
    }

    public List<AccountResponse> getAccounts() {
        List<AccountResponse> accountsResponse = new ArrayList<>();
        for (Account account : accounts.values()) {
            accountsResponse.add(toAccountResponse(account));
        }

        return accountsResponse;
    }

    public List<AccountResponse> getAccountsByUserId(Long userId) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "User ID is required");
        }

        if (!userService.existsById(userId)) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "User not found");
        }

        List<AccountResponse> result = new ArrayList<>();

        for (Account account : accounts.values()) {
            if (account.getUserId().equals(userId)) {
                result.add(toAccountResponse(account));
            }
        }

        return result;
    }

    public Account getAccountModelById(Long id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Account ID is required");
        }

        Account account = accounts.get(id);

        if (account == null) {
            throw new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND, "Account not found");
        }

        return account;
    }

    public void increaseBalance(Long accountId, BigDecimal amount) {
        Account account = getAccountModelById(accountId);

        BigDecimal newBalance = account.getBalance().add(amount);

        account.setBalance(newBalance);
    }

    public void decreaseBalance(Long accountId, BigDecimal amount) {
        Account account = getAccountModelById(accountId);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new BusinessException(ErrorCode.INSUFFICIENT_BALANCE, "Insufficient balance for withdrawal");
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);

        account.setBalance(newBalance);
    }

    private AccountResponse toAccountResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getUserId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getStatus(),
                account.getCreatedAt()
        );
    }
}
