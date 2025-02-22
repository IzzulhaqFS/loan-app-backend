package com.enigmacamp.enigma_loan_app.controller;

import com.enigmacamp.enigma_loan_app.dto.request.NewLoanTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.request.UpdateLoanTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.response.CommonResponse;
import com.enigmacamp.enigma_loan_app.dto.response.LoanTypeResponse;
import com.enigmacamp.enigma_loan_app.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/loan-types")
public class LoanTypeController {
    private final LoanTypeService loanTypeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<CommonResponse<LoanTypeResponse>> create(@RequestBody NewLoanTypeRequest request) {
        LoanTypeResponse loanTypeResponse = loanTypeService.create(request);
        CommonResponse<LoanTypeResponse> response = CommonResponse.<LoanTypeResponse>builder()
                .message("Loan Type created.")
                .data(loanTypeResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanTypeResponse>> getById(@PathVariable String id) {
        LoanTypeResponse loanTypeResponse = loanTypeService.getById(id);
        CommonResponse<LoanTypeResponse> response = CommonResponse.<LoanTypeResponse>builder()
                .message("Successfully get Loan Type with ID: " + id + ".")
                .data(loanTypeResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<LoanTypeResponse>>> getAll() {
        List<LoanTypeResponse> loanTypeResponses = loanTypeService.getAll();
        CommonResponse<List<LoanTypeResponse>> response = CommonResponse.<List<LoanTypeResponse>>builder()
                .message("Successfully get all Loan Type")
                .data(loanTypeResponses)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<CommonResponse<LoanTypeResponse>> update(@RequestBody UpdateLoanTypeRequest request) {
        LoanTypeResponse loanTypeResponse = loanTypeService.update(request);
        CommonResponse<LoanTypeResponse> response = CommonResponse.<LoanTypeResponse>builder()
                .message("Successfully update Loan Type with ID: " + request.getId() + ".")
                .data(loanTypeResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> delete(@PathVariable String id) {
        loanTypeService.delete(id);
        CommonResponse<LoanTypeResponse> response = CommonResponse.<LoanTypeResponse>builder()
                .message("Successfully delete Loan Type with ID: " + id + ".")
                .build();
        return ResponseEntity.ok(response);
    }
}
