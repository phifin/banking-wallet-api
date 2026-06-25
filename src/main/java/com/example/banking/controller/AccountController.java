package com.example.banking.controller;

import com.example.banking.dto.request.CreateAccountRequest;
import com.example.banking.dto.response.AccountResponse;
import com.example.banking.dto.response.ApiResponse;
import com.example.banking.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ApiResponse<AccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
        AccountResponse newAccount = accountService.createAccount(request);

        return new ApiResponse<>(
                true,
                "Account created successfully",
                newAccount
        );
    }

    @GetMapping
    public ApiResponse<List<AccountResponse>> getAccounts(@RequestParam(required = false, name = "userId") Long userId) {
        List<AccountResponse> accountsList;
        if (userId == null) {
            accountsList = accountService.getAccounts();
        } else {
            accountsList = accountService.getAccountsByUserId(userId);
        }
        return new ApiResponse<>(
                true,
                "Accounts retrieved successfully",
                accountsList
        );
    }

    @GetMapping("/{accountId}")
    public ApiResponse<AccountResponse> getAccountById(@PathVariable("accountId") Long id) {
        AccountResponse account = accountService.getAccountById(id);

        return new ApiResponse<>(
                true,
                "Account retrieved successfully",
                account
        );
    }
}
