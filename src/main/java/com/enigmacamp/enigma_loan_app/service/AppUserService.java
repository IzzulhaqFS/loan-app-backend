package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.dto.response.AppUserResponse;

public interface AppUserService {
    AppUserResponse getById(String id);
}
