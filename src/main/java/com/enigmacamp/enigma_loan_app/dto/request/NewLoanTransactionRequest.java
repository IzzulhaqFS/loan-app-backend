package com.enigmacamp.enigma_loan_app.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewLoanTransactionRequest {
    private String loanType;
    private String instalmentType;
    private String customer;
    private Double nominal;
}
