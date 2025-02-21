package com.enigmacamp.enigma_loan_app.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse<T> {
    private String message;
    private T data;
}
