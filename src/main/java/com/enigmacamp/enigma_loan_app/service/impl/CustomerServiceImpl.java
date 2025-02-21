package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.dto.request.NewCustomerRequest;
import com.enigmacamp.enigma_loan_app.dto.request.UpdateCustomerRequest;
import com.enigmacamp.enigma_loan_app.dto.response.CustomerResponse;
import com.enigmacamp.enigma_loan_app.entity.Customer;
import com.enigmacamp.enigma_loan_app.repository.CustomerRepository;
import com.enigmacamp.enigma_loan_app.service.CustomerService;
import com.enigmacamp.enigma_loan_app.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(NewCustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(DateUtil.parseDate(request.getDateOfBirth(), "yyyy-MM-dd"))
                .phone(request.getPhone())
                .status(request.getStatus())
                .build();
        customerRepository.saveAndFlush(customer);
        return getCustomerResponse(customer);
    }

    @Override
    public CustomerResponse getById(String id) {
        Customer customer = getCustomer(id);
        return getCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerServiceImpl::getCustomerResponse).toList();
    }

    @Override
    public CustomerResponse update(UpdateCustomerRequest request) {
        Customer customer = getCustomer(request.getId());

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setDateOfBirth(DateUtil.parseDate(request.getDateOfBirth(), "yyyy-MM-dd"));
        customer.setPhone(request.getPhone());
        customer.setStatus(request.getStatus());

        customerRepository.saveAndFlush(customer);

        return getCustomerResponse(customer);
    }

    @Override
    public void delete(String id) {
        Customer customer = getCustomer(id);
        customerRepository.delete(customer);
    }

    private Customer getCustomer(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found."));
    }

    private static CustomerResponse getCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getDateOfBirth())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .build();
    }
}
