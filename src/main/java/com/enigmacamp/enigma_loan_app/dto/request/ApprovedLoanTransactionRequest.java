package com.enigmacamp.enigma_loan_app.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovedLoanTransactionRequest {
    private String loanTransactionId;
    private Double interestRate;
}
