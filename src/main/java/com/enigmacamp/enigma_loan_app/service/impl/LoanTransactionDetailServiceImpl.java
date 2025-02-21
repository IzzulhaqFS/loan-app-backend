package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.entity.LoanTransactionDetail;
import com.enigmacamp.enigma_loan_app.repository.LoanTransactionDetailRepository;
import com.enigmacamp.enigma_loan_app.service.LoanTransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTransactionDetailServiceImpl implements LoanTransactionDetailService {
    private final LoanTransactionDetailRepository loanTransactionDetailRepository;

    @Override
    public List<LoanTransactionDetail> createBulk(List<LoanTransactionDetail> loanTransactionDetails) {
        return loanTransactionDetailRepository.saveAllAndFlush(loanTransactionDetails);
    }

    @Override
    public List<LoanTransactionDetail> getByTransaction(String id) {
        return loanTransactionDetailRepository.getByTransaction(id);
    }

    @Override
    public LoanTransactionDetail update(LoanTransactionDetail loanTransactionDetail) {
        return loanTransactionDetailRepository.saveAndFlush(loanTransactionDetail);
    }
}
