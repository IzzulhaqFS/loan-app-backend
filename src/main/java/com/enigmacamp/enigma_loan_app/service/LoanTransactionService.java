package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.dto.request.ApprovedLoanTransactionRequest;
import com.enigmacamp.enigma_loan_app.dto.request.NewLoanTransactionRequest;
import com.enigmacamp.enigma_loan_app.dto.response.LoanTransactionResponse;

public interface LoanTransactionService {
    LoanTransactionResponse requestLoan(NewLoanTransactionRequest request);
    LoanTransactionResponse getById(String id);
    LoanTransactionResponse approvedByAdmin(String id, ApprovedLoanTransactionRequest request);
    LoanTransactionResponse payInstalment(String id);
}
