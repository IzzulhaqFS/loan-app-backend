package com.enigmacamp.enigma_loan_app.controller;

import com.enigmacamp.enigma_loan_app.dto.request.NewInstalmentTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.request.UpdateInstalmentTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.response.CommonResponse;
import com.enigmacamp.enigma_loan_app.dto.response.InstalmentTypeResponse;
import com.enigmacamp.enigma_loan_app.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/instalment-types")
public class InstalmentTypeController {
    private final InstalmentTypeService instalmentTypeService;

    @PostMapping
    public ResponseEntity<CommonResponse<InstalmentTypeResponse>> create(@RequestBody NewInstalmentTypeRequest request) {
        InstalmentTypeResponse instalmentTypeResponse = instalmentTypeService.create(request);
        CommonResponse<InstalmentTypeResponse> response = CommonResponse.<InstalmentTypeResponse>builder()
                .message("Successfully create new Instalment Type.")
                .data(instalmentTypeResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<InstalmentTypeResponse>> getById(@PathVariable String id) {
        InstalmentTypeResponse instalmentTypeResponse = instalmentTypeService.getById(id);
        CommonResponse<InstalmentTypeResponse> response = CommonResponse.<InstalmentTypeResponse>builder()
                .message("Successfully get Instalment Type with ID: " + id + ".")
                .data(instalmentTypeResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<InstalmentTypeResponse>>> getAll() {
        List<InstalmentTypeResponse> customerResponses = instalmentTypeService.getAll();
        CommonResponse<List<InstalmentTypeResponse>> response = CommonResponse.<List<InstalmentTypeResponse>>builder()
                .message("Successfully get all Customer.")
                .data(customerResponses)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<InstalmentTypeResponse>> update(@RequestBody UpdateInstalmentTypeRequest request) {
        InstalmentTypeResponse customerResponse = instalmentTypeService.update(request);
        CommonResponse<InstalmentTypeResponse> response = CommonResponse.<InstalmentTypeResponse>builder()
                .message("Successfully update Customer with ID: " + request.getId() + ".")
                .data(customerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> delete(@PathVariable String id) {
        instalmentTypeService.delete(id);
        CommonResponse<?> response = CommonResponse.builder()
                .message("Successfully delete Instalment Type with ID: " + id + ".")
                .build();
        return ResponseEntity.ok(response);
    }
}
