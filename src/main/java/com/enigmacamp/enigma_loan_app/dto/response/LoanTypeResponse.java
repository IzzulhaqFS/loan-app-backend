package com.enigmacamp.enigma_loan_app.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTypeResponse {
    private String id;
    private String type;
    private Double maxLoan;
}
