package com.enigmacamp.enigma_loan_app.dto.request;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCustomerRequest {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phone;
}
