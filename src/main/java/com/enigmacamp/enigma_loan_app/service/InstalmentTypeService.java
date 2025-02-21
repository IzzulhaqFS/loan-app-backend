package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.dto.request.NewInstalmentTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.request.UpdateInstalmentTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.response.InstalmentTypeResponse;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentTypeResponse create(NewInstalmentTypeRequest request);
    InstalmentTypeResponse getById(String id);
    List<InstalmentTypeResponse> getAll();
    InstalmentTypeResponse update(UpdateInstalmentTypeRequest request);
    void delete(String id);
}
