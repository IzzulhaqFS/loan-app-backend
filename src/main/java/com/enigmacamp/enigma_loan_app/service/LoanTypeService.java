package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.dto.request.NewLoanTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.request.UpdateLoanTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.response.LoanTypeResponse;

import java.util.List;

public interface LoanTypeService {
    LoanTypeResponse create(NewLoanTypeRequest request);
    LoanTypeResponse getById(String id);
    List<LoanTypeResponse> getAll();
    LoanTypeResponse update(UpdateLoanTypeRequest request);
    void delete(String id);
}
