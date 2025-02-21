package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.constant.EInstalmentType;
import com.enigmacamp.enigma_loan_app.dto.request.NewInstalmentTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.request.UpdateInstalmentTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.response.InstalmentTypeResponse;
import com.enigmacamp.enigma_loan_app.entity.InstalmentType;
import com.enigmacamp.enigma_loan_app.repository.InstalmentTypeRepository;
import com.enigmacamp.enigma_loan_app.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
    private final InstalmentTypeRepository instalmentTypeRepository;

    @Override
    public InstalmentTypeResponse create(NewInstalmentTypeRequest request) {
        String instalmentTypeRequest = request.getInstalmentType();
        EInstalmentType eInstalmentType = getEInstalmentType(instalmentTypeRequest);

        InstalmentType instalmentType = InstalmentType.builder()
                .instalmentType(eInstalmentType)
                .build();

        instalmentTypeRepository.saveAndFlush(instalmentType);
        return getInstalmentTypeResponse(instalmentType);
    }

    @Override
    public InstalmentTypeResponse getById(String id) {
        InstalmentType instalmentType = getInstalmentType(id);
        return getInstalmentTypeResponse(instalmentType);
    }

    @Override
    public List<InstalmentTypeResponse> getAll() {
        List<InstalmentType> instalmentTypes = instalmentTypeRepository.findAll();
        return instalmentTypes.stream().map(InstalmentTypeServiceImpl::getInstalmentTypeResponse).toList();
    }

    @Override
    public InstalmentTypeResponse update(UpdateInstalmentTypeRequest request) {
        String instalmentTypeRequest = request.getInstalmentType();
        EInstalmentType eInstalmentType = getEInstalmentType(instalmentTypeRequest);
        InstalmentType instalmentType = getInstalmentType(request.getId());

        instalmentType.setInstalmentType(eInstalmentType);

        instalmentTypeRepository.saveAndFlush(instalmentType);

        return getInstalmentTypeResponse(instalmentType);
    }

    @Override
    public void delete(String id) {
        InstalmentType instalmentType = getInstalmentType(id);
        instalmentTypeRepository.delete(instalmentType);
    }

    private InstalmentType getInstalmentType(String id) {
        return instalmentTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instalment type not found."));
    }

    private static InstalmentTypeResponse getInstalmentTypeResponse(InstalmentType instalmentType) {
        return InstalmentTypeResponse.builder()
                .id(instalmentType.getId())
                .instalmentType(instalmentType.getInstalmentType().name())
                .build();
    }

    private static EInstalmentType getEInstalmentType(String request) {
        if (request != null && !request.isEmpty()) {
            if (request.equals(EInstalmentType.ONE_MONTH.name())) {
                return EInstalmentType.ONE_MONTH;
            } else if (request.equals(EInstalmentType.THREE_MONTHS.name())) {
                return EInstalmentType.THREE_MONTHS;
            } else if (request.equals(EInstalmentType.SIXTH_MONTHS.name())) {
                return EInstalmentType.SIXTH_MONTHS;
            } else if (request.equals(EInstalmentType.NINE_MONTHS.name())) {
                return EInstalmentType.NINE_MONTHS;
            } else if (request.equals(EInstalmentType.TWELVE_MONTHS.name())) {
                return EInstalmentType.TWELVE_MONTHS;
            } else {
                throw new RuntimeException("Invalid instalment type.");
            }
        } else {
            throw new RuntimeException("Request is null or empty");
        }
    }
}
