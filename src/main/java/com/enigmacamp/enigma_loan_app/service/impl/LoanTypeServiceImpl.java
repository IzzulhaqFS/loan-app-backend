package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.dto.request.NewLoanTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.request.UpdateLoanTypeRequest;
import com.enigmacamp.enigma_loan_app.dto.response.LoanTypeResponse;
import com.enigmacamp.enigma_loan_app.entity.LoanType;
import com.enigmacamp.enigma_loan_app.repository.LoanTypeRepository;
import com.enigmacamp.enigma_loan_app.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;

    @Override
    public LoanTypeResponse create(NewLoanTypeRequest request) {
        LoanType loanType = LoanType.builder()
                .type(request.getType())
                .maxLoan(request.getMaxLoan())
                .build();
        loanTypeRepository.saveAndFlush(loanType);
        return getLoanTypeResponse(loanType);
    }

    @Override
    public LoanTypeResponse getById(String id) {
        LoanType loanType = getLoanType(id);
        return getLoanTypeResponse(loanType);
    }

    @Override
    public List<LoanTypeResponse> getAll() {
        List<LoanType> loanTypes = loanTypeRepository.findAll();
        return loanTypes.stream().map(LoanTypeServiceImpl::getLoanTypeResponse).toList();
    }

    @Override
    public LoanTypeResponse update(UpdateLoanTypeRequest request) {
        LoanType loanType = getLoanType(request.getId());

        loanType.setType(request.getType());
        loanType.setMaxLoan(request.getMaxLoan());

        loanTypeRepository.saveAndFlush(loanType);

        return getLoanTypeResponse(loanType);
    }

    @Override
    public void delete(String id) {
        LoanType loanType = getLoanType(id);
        loanTypeRepository.delete(loanType);
    }

    private LoanType getLoanType(String id) {
        return loanTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan Type not found."));
    }

    private static LoanTypeResponse getLoanTypeResponse(LoanType loanType) {
        return LoanTypeResponse.builder()
                .id(loanType.getId())
                .type(loanType.getType())
                .maxLoan(loanType.getMaxLoan())
                .build();
    }
}
