package com.enigmacamp.enigma_loan_app.controller;

import com.enigmacamp.enigma_loan_app.dto.request.NewCustomerRequest;
import com.enigmacamp.enigma_loan_app.dto.request.UpdateCustomerRequest;
import com.enigmacamp.enigma_loan_app.dto.response.CommonResponse;
import com.enigmacamp.enigma_loan_app.dto.response.CustomerResponse;
import com.enigmacamp.enigma_loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse<CustomerResponse>> create(@RequestBody NewCustomerRequest request) {
        CustomerResponse customerResponse = customerService.create(request);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .message("Customer created.")
                .data(customerResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> getById(@PathVariable String id) {
        CustomerResponse customerResponse = customerService.getById(id);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .message("Successfully get Customer with ID: " + id + ".")
                .data(customerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAll() {
        List<CustomerResponse> customerResponses = customerService.getAll();
        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .message("Successfully get all Customer.")
                .data(customerResponses)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<CustomerResponse>> update(@RequestBody UpdateCustomerRequest request) {
        CustomerResponse customerResponse = customerService.update(request);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .message("Successfully update Customer with ID: " + request.getId() + ".")
                .data(customerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> delete(@PathVariable String id) {
        customerService.delete(id);
        CommonResponse<?> response = CommonResponse.builder()
                .message("Successfully delete Customer with ID: " + id + ".")
                .build();
        return ResponseEntity.ok(response);
    }
}
