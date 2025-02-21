package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.dto.request.NewLoanTransactionRequest;
import com.enigmacamp.enigma_loan_app.dto.response.LoanTransactionResponse;

public interface LoanTransactionService {
    LoanTransactionResponse requestLoan(NewLoanTransactionRequest request);
}
