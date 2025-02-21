package com.enigmacamp.enigma_loan_app.controller;

import com.enigmacamp.enigma_loan_app.dto.response.AppUserResponse;
import com.enigmacamp.enigma_loan_app.dto.response.CommonResponse;
import com.enigmacamp.enigma_loan_app.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<AppUserResponse>> getById(@PathVariable String id) {
        AppUserResponse appUserResponse = appUserService.getById(id);
        CommonResponse<AppUserResponse> response = CommonResponse.<AppUserResponse>builder()
                .message("Successfully get user with id: " + id)
                .data(appUserResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
