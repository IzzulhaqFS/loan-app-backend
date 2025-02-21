package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.dto.response.AppUserResponse;
import com.enigmacamp.enigma_loan_app.entity.AppUser;
import com.enigmacamp.enigma_loan_app.entity.UserRole;
import com.enigmacamp.enigma_loan_app.repository.AppUserRepository;
import com.enigmacamp.enigma_loan_app.service.AppUserService;
import com.enigmacamp.enigma_loan_app.service.RoleService;
import com.enigmacamp.enigma_loan_app.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Override
    public AppUserResponse getById(String id) {
        AppUser user = appUserRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        List<UserRole> userRoles = userRoleService.getByUser(user);
        List<String> roles = userRoles.stream().map(userRole -> userRole.getRole().getRole().name()).toList();

        return AppUserResponse.builder()
                .email(user.getEmail())
                .role(roles)
                .build();
    }
}
