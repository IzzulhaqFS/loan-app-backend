package com.enigmacamp.enigma_loan_app.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLoanTypeRequest {
    private String id;
    private String type;
    private Double maxLoan;
}
