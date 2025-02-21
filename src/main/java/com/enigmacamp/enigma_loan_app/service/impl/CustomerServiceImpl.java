package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.dto.request.UpdateCustomerRequest;
import com.enigmacamp.enigma_loan_app.dto.response.CustomerResponse;
import com.enigmacamp.enigma_loan_app.repository.CustomerRepository;
import com.enigmacamp.enigma_loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse getById(String id) {
        return null;
    }

    @Override
    public Page<CustomerResponse> getAll() {
        return null;
    }

    @Override
    public CustomerResponse update(UpdateCustomerRequest request) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
