package com.enigmacamp.enigma_loan_app.controller;

import com.enigmacamp.enigma_loan_app.dto.request.ApprovedLoanTransactionRequest;
import com.enigmacamp.enigma_loan_app.dto.request.NewLoanTransactionRequest;
import com.enigmacamp.enigma_loan_app.dto.response.CommonResponse;
import com.enigmacamp.enigma_loan_app.dto.response.LoanTransactionResponse;
import com.enigmacamp.enigma_loan_app.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/transactions")
public class LoanTransactionController {
    private final LoanTransactionService loanTransactionService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
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

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> getById(@PathVariable String id) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.getById(id);
        CommonResponse<LoanTransactionResponse> response = CommonResponse.<LoanTransactionResponse>builder()
                .message("Successfully get Loan Transaction with ID: " + id + ".")
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> approveByAdmin(
            @PathVariable String id,
            @RequestBody ApprovedLoanTransactionRequest request
    ) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.approvedByAdmin(id, request);
        CommonResponse<LoanTransactionResponse> response = CommonResponse.<LoanTransactionResponse>builder()
                .message("Successfully approve Loan Transaction with ID: " + id + ".")
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> payInstalment(@PathVariable String id) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.payInstalment(id);
        CommonResponse<LoanTransactionResponse> response = CommonResponse.<LoanTransactionResponse>builder()
                .message("Successfully pay instalment.")
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
