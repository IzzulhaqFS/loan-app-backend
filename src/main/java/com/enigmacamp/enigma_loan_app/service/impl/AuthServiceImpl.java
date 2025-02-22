package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.constant.ERole;
import com.enigmacamp.enigma_loan_app.dto.request.AuthRequest;
import com.enigmacamp.enigma_loan_app.dto.request.NewAppUserRequest;
import com.enigmacamp.enigma_loan_app.dto.response.AppUserResponse;
import com.enigmacamp.enigma_loan_app.dto.response.AuthResponse;
import com.enigmacamp.enigma_loan_app.entity.AppUser;
import com.enigmacamp.enigma_loan_app.entity.Role;
import com.enigmacamp.enigma_loan_app.entity.UserRole;
import com.enigmacamp.enigma_loan_app.repository.AppUserRepository;
import com.enigmacamp.enigma_loan_app.security.JwtAuthenticationFilter;
import com.enigmacamp.enigma_loan_app.security.JwtTokenProvider;
import com.enigmacamp.enigma_loan_app.service.AuthService;
import com.enigmacamp.enigma_loan_app.service.RoleService;
import com.enigmacamp.enigma_loan_app.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AppUserRepository appUserRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final JwtTokenProvider tokenProvider;
    private final JwtAuthenticationFilter authenticationFilter;
    private final AuthenticationManager authenticationManager;

    @Override
    public AppUserResponse adminRegister(NewAppUserRequest request) {
        try {
            ERole roleType = ERole.ROLE_ADMIN;
            Role role = roleService.getByName(roleType.name());

            AppUser user = AppUser.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            appUserRepository.saveAndFlush(user);

            UserRole userRole = UserRole.builder()
                    .user(user)
                    .role(role)
                    .build();
            userRoleService.create(userRole);

            user.setRoles(List.of(userRole));

            return AppUserResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRoles().stream().map(userRole1 -> userRole1.getRole().getRole().name()).toList())
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicated user.");
        }
    }

    @Override
    public AppUserResponse customerRegister(NewAppUserRequest request) {
        try {
            ERole roleType = ERole.ROLE_CUSTOMER;
            Role role = roleService.getByName(roleType.name());

            AppUser user = AppUser.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            appUserRepository.saveAndFlush(user);

            UserRole userRole = UserRole.builder()
                    .user(user)
                    .role(role)
                    .build();
            userRoleService.create(userRole);

            user.setRoles(List.of(userRole));

            return AppUserResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRoles().stream().map(userRole1 -> userRole1.getRole().getRole().name()).toList())
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicated user.");
        }
    }

    @Override
    public AppUserResponse staffRegister(NewAppUserRequest request) {
        try {
            ERole roleType = ERole.ROLE_ADMIN;
            Role role = roleService.getByName(roleType.name());

            AppUser user = AppUser.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            appUserRepository.saveAndFlush(user);

            UserRole userRole = UserRole.builder()
                    .user(user)
                    .role(role)
                    .build();
            userRoleService.create(userRole);

            user.setRoles(List.of(userRole));

            return AppUserResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRoles().stream().map(userRole1 -> userRole1.getRole().getRole().name()).toList())
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicated user.");
        }
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = tokenProvider.generateToken(user.getUsername(), user.getAuthorities().toString());

        return AuthResponse.builder()
                .email(user.getUsername())
                .role(user.getAuthorities().toString())
                .token(token)
                .build();
    }
}
