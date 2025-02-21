package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.dto.request.NewAppUserRequest;
import com.enigmacamp.enigma_loan_app.dto.response.AppUserResponse;

public interface AuthService {
    AppUserResponse adminRegister(NewAppUserRequest request);
    AppUserResponse customerRegister(NewAppUserRequest request);
    AppUserResponse staffRegister(NewAppUserRequest request);
}
