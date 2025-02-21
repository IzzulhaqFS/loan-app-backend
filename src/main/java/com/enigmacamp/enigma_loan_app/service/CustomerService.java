package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.dto.request.NewCustomerRequest;
import com.enigmacamp.enigma_loan_app.dto.request.UpdateCustomerRequest;
import com.enigmacamp.enigma_loan_app.dto.response.CustomerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(NewCustomerRequest request);
    CustomerResponse getById(String id);
    List<CustomerResponse> getAll();
    CustomerResponse update(UpdateCustomerRequest request);
    void delete(String id);
}
