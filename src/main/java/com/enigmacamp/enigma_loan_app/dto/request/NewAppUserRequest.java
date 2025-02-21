package com.enigmacamp.enigma_loan_app.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewAppUserRequest {
    private String email;
    private String password;
}
