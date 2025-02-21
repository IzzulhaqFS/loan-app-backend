package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.constant.ApprovalStatus;
import com.enigmacamp.enigma_loan_app.constant.EInstalmentType;
import com.enigmacamp.enigma_loan_app.constant.LoanStatus;
import com.enigmacamp.enigma_loan_app.dto.request.ApprovedLoanTransactionRequest;
import com.enigmacamp.enigma_loan_app.dto.request.NewLoanTransactionRequest;
import com.enigmacamp.enigma_loan_app.dto.response.*;
import com.enigmacamp.enigma_loan_app.entity.*;
import com.enigmacamp.enigma_loan_app.repository.LoanTransactionRepository;
import com.enigmacamp.enigma_loan_app.service.*;
import com.enigmacamp.enigma_loan_app.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class LoanTransactionServiceImpl implements LoanTransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanTypeService loanTypeService;
    private final InstalmentTypeService instalmentTypeService;
    private final CustomerService customerService;
    private final LoanTransactionDetailService loanTransactionDetailService;

    @Override
    public LoanTransactionResponse requestLoan(NewLoanTransactionRequest request) {
        LoanTypeResponse loanTypeResponse = loanTypeService.getById(request.getLoanType());
        LoanType loanType = LoanType.builder()
                .id(loanTypeResponse.getId())
                .type(loanTypeResponse.getType())
                .maxLoan(loanTypeResponse.getMaxLoan())
                .build();

        InstalmentTypeResponse instalmentTypeResponse = instalmentTypeService.getById(request.getInstalmentType());
        InstalmentType instalmentType = InstalmentType.builder()
                .id(instalmentTypeResponse.getId())
                .instalmentType(getEInstalmentType(instalmentTypeResponse.getInstalmentType()))
                .build();

        CustomerResponse customerResponse = customerService.getById(request.getCustomer());
        Customer customer = Customer.builder()
                .id(customerResponse.getId())
                .firstName(customerResponse.getFirstName())
                .lastName(customerResponse.getLastName())
                .dateOfBirth(customerResponse.getDateOfBirth())
                .phone(customerResponse.getPhone())
                .status(customerResponse.getStatus())
                .build();

        LoanTransaction loanTransaction = LoanTransaction.builder()
                .loanType(loanType)
                .instalmentType(instalmentType)
                .customer(customer)
                .nominal(request.getNominal())
                .createdAt(new Date())
                .build();

        loanTransactionRepository.saveAndFlush(loanTransaction);

        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .loanTypeId(loanTransaction.getLoanType().getId())
                .instalmentTypeId(loanTransaction.getInstalmentType().getId())
                .customerId(loanTransaction.getCustomer().getId())
                .nominal(loanTransaction.getNominal())
                .createdAt(loanTransaction.getCreatedAt())
                .build();
    }

    @Override
    public LoanTransactionResponse getById(String id) {
        LoanTransaction loanTransaction = getLoanTransaction(id);
        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .loanTypeId(loanTransaction.getLoanType().getId())
                .instalmentTypeId(loanTransaction.getInstalmentType().getId())
                .customerId(loanTransaction.getCustomer().getId())
                .nominal(loanTransaction.getNominal())
                .createdAt(loanTransaction.getCreatedAt())
                .build();
    }

    @Override
    public LoanTransactionResponse approvedByAdmin(String id, ApprovedLoanTransactionRequest request) {
        LoanTransaction loanTransaction = getLoanTransaction(id);

        Double interest = loanTransaction.getNominal() * request.getInterestRate();

        loanTransaction.setApprovedBy("admin1@gmail.com");
        loanTransaction.setApprovedAt(new Date());
        loanTransaction.setApprovalStatus(ApprovalStatus.APPROVED);
        loanTransaction.setUpdatedAt(new Date());

        loanTransactionRepository.saveAndFlush(loanTransaction);

        LoanTransactionDetail loanTransactionDetail = LoanTransactionDetail.builder()
                .transactionDate(new Date())
                .nominal(loanTransaction.getNominal() + interest)
                .loanTransaction(loanTransaction)
                .loanStatus(LoanStatus.UNPAID)
                .createdAt(new Date())
                .build();

        loanTransactionDetailService.createBulk(List.of(loanTransactionDetail));

        loanTransaction.setLoanTransactionDetails(List.of(loanTransactionDetail));

        List<LoanTransactionDetailResponse> detailResponses = Stream.of(loanTransactionDetail).map(detail -> LoanTransactionDetailResponse.builder()
                .id(detail.getId())
                .transactionDate(detail.getTransactionDate())
                .loanStatus(detail.getLoanStatus().name())
                .nominal(detail.getNominal())
                .createdAt(detail.getCreatedAt())
                .build()).toList();

        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .loanTypeId(loanTransaction.getLoanType().getId())
                .instalmentTypeId(loanTransaction.getInstalmentType().getId())
                .customerId(loanTransaction.getCustomer().getId())
                .nominal(loanTransaction.getNominal())
                .approvedAt(loanTransaction.getApprovedAt())
                .approvedBy(loanTransaction.getApprovedBy())
                .approvalStatus(loanTransaction.getApprovalStatus().name())
                .createdAt(loanTransaction.getCreatedAt())
                .updatedAt(loanTransaction.getUpdatedAt())
                .transactionDetailResponses(detailResponses)
                .build();
    }

    @Override
    public LoanTransactionResponse payInstalment(String id) {
        LoanTransaction loanTransaction = getLoanTransaction(id);
        List<LoanTransactionDetail> loanTransactionDetails = loanTransactionDetailService.getByTransaction(loanTransaction.getId());
        LoanTransactionDetail loanTransactionDetail = loanTransactionDetails.get(loanTransactionDetails.size() - 1);

        loanTransactionDetail.setLoanStatus(LoanStatus.PAID);
        loanTransactionDetail.setTransactionDate(new Date());
        loanTransactionDetail.setUpdatedAt(new Date());

        loanTransactionDetailService.update(loanTransactionDetail);

        List<LoanTransactionDetailResponse> detailResponses = Stream.of(loanTransactionDetail).map(detail -> LoanTransactionDetailResponse.builder()
                .id(detail.getId())
                .transactionDate(detail.getTransactionDate())
                .loanStatus(detail.getLoanStatus().name())
                .nominal(detail.getNominal())
                .createdAt(detail.getCreatedAt())
                .updatedAt(detail.getUpdatedAt())
                .build()).toList();

        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .loanTypeId(loanTransaction.getLoanType().getId())
                .instalmentTypeId(loanTransaction.getInstalmentType().getId())
                .customerId(loanTransaction.getCustomer().getId())
                .nominal(loanTransaction.getNominal())
                .approvedAt(loanTransaction.getApprovedAt())
                .approvedBy(loanTransaction.getApprovedBy())
                .approvalStatus(loanTransaction.getApprovalStatus().name())
                .transactionDetailResponses(detailResponses)
                .createdAt(loanTransaction.getCreatedAt())
                .updatedAt(loanTransaction.getUpdatedAt())
                .build();
    }

    private LoanTransaction getLoanTransaction(String id) {
        return loanTransactionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found."));
    }

    private static EInstalmentType getEInstalmentType(String instalmentType) {
        if (instalmentType != null && !instalmentType.isEmpty()) {
            if (instalmentType.equals(EInstalmentType.ONE_MONTH.name())) {
                return EInstalmentType.ONE_MONTH;
            } else if (instalmentType.equals(EInstalmentType.THREE_MONTHS.name())) {
                return EInstalmentType.THREE_MONTHS;
            } else if (instalmentType.equals(EInstalmentType.SIXTH_MONTHS.name())) {
                return EInstalmentType.SIXTH_MONTHS;
            } else if (instalmentType.equals(EInstalmentType.NINE_MONTHS.name())) {
                return EInstalmentType.NINE_MONTHS;
            } else if (instalmentType.equals(EInstalmentType.TWELVE_MONTHS.name())) {
                return EInstalmentType.TWELVE_MONTHS;
            } else {
                throw new RuntimeException("Invalid instalment type.");
            }
        } else {
            throw new RuntimeException("Request is null or empty");
        }
    }
}
