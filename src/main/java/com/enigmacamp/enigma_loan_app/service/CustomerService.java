package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.dto.request.UpdateCustomerRequest;
import com.enigmacamp.enigma_loan_app.dto.response.CustomerResponse;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerResponse getById(String id);
    Page<CustomerResponse> getAll();
    CustomerResponse update(UpdateCustomerRequest request);
    void delete(String id);
}
