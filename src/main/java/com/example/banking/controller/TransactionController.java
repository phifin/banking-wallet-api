package com.example.banking.controller;

import com.example.banking.dto.request.DepositRequest;
import com.example.banking.dto.response.ApiResponse;
import com.example.banking.dto.response.TransactionResponse;
import com.example.banking.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ApiResponse<TransactionResponse> deposit(@RequestBody DepositRequest request) {
        TransactionResponse transactionResponse = transactionService.deposit(request);

        return new ApiResponse<>(
                true,
                "Deposit completed successfully",
                transactionResponse
        );
    }
}
