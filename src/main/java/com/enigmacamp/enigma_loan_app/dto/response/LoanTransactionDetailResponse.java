package com.enigmacamp.enigma_loan_app.dto.response;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionDetailResponse {
    private String id;
    private Date transactionDate;
    private Double nominal;
    private String loanStatus;
    private Date createdAt;
    private Date updatedAt;
}
