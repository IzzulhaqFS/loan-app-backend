package com.enigmacamp.enigma_loan_app.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstalmentTypeResponse {
    private String id;
    private String instalmentType;
}
