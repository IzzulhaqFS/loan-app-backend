package com.enigmacamp.enigma_loan_app.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionResponse {
    private String id;
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerId;
    private Double nominal;
    private Date approvedAt;
    private String approvedBy;
    private String approvalStatus;
    private List<LoanTransactionDetailResponse> transactionDetailResponses;
    private Date createdAt;
    private Date updatedAt;
}
