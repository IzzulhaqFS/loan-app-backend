package com.enigmacamp.enigma_loan_app.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserResponse {
    private String email;
    private List<String> role;
}
