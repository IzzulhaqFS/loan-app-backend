package com.enigmacamp.enigma_loan_app.controller;

import com.enigmacamp.enigma_loan_app.dto.request.NewLoanTransactionRequest;
import com.enigmacamp.enigma_loan_app.dto.response.CommonResponse;
import com.enigmacamp.enigma_loan_app.dto.response.LoanTransactionResponse;
import com.enigmacamp.enigma_loan_app.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/transactions")
public class LoanTransactionController {
    private final LoanTransactionService loanTransactionService;

    @PostMapping
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> create(
            @RequestBody NewLoanTransactionRequest request
    ) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.requestLoan(request);
        CommonResponse<LoanTransactionResponse> response = CommonResponse.<LoanTransactionResponse>builder()
                .message("Loan Request created.")
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
