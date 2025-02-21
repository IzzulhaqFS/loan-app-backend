package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.entity.LoanTransactionDetail;

import java.util.List;

public interface LoanTransactionDetailService {
    List<LoanTransactionDetail> createBulk(List<LoanTransactionDetail> loanTransactionDetails);
    List<LoanTransactionDetail> getByTransaction(String id);
    LoanTransactionDetail update(LoanTransactionDetail loanTransactionDetail);
}
