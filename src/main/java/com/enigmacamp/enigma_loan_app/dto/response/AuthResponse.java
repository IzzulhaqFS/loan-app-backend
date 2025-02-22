package com.enigmacamp.enigma_loan_app.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String email;
    private String role;
    private String token;
}
