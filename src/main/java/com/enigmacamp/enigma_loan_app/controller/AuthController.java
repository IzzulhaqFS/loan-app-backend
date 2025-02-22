package com.enigmacamp.enigma_loan_app.controller;

import com.enigmacamp.enigma_loan_app.dto.request.AuthRequest;
import com.enigmacamp.enigma_loan_app.dto.request.NewAppUserRequest;
import com.enigmacamp.enigma_loan_app.dto.response.AppUserResponse;
import com.enigmacamp.enigma_loan_app.dto.response.AuthResponse;
import com.enigmacamp.enigma_loan_app.dto.response.CommonResponse;
import com.enigmacamp.enigma_loan_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup/admin")
    public ResponseEntity<CommonResponse<AppUserResponse>> createAdmin(
            @RequestBody NewAppUserRequest request
    ) {
        AppUserResponse appUserResponse = authService.adminRegister(request);
        CommonResponse<AppUserResponse> response = CommonResponse.<AppUserResponse>builder()
                .message("Admin created.")
                .data(appUserResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/signup/customer")
    public ResponseEntity<CommonResponse<AppUserResponse>> createCustomer(
            @RequestBody NewAppUserRequest request
    ) {
        AppUserResponse appUserResponse = authService.adminRegister(request);
        CommonResponse<AppUserResponse> response = CommonResponse.<AppUserResponse>builder()
                .message("Customer created.")
                .data(appUserResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/signup/staff")
    public ResponseEntity<CommonResponse<AppUserResponse>> createStaff(
            @RequestBody NewAppUserRequest request
    ) {
        AppUserResponse appUserResponse = authService.adminRegister(request);
        CommonResponse<AppUserResponse> response = CommonResponse.<AppUserResponse>builder()
                .message("Staff created.")
                .data(appUserResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<CommonResponse<AuthResponse>> login(@RequestBody AuthRequest request) {
        AuthResponse authResponse = authService.login(request);
        CommonResponse<AuthResponse> response = CommonResponse.<AuthResponse>builder()
                .message("Login success.")
                .data(authResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
