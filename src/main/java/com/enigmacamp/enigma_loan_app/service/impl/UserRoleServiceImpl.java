package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.entity.UserRole;
import com.enigmacamp.enigma_loan_app.repository.UserRoleRepository;
import com.enigmacamp.enigma_loan_app.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRole getById(String id) {
        return null;
    }

    @Override
    public UserRole create(UserRole userRole) {
        return null;
    }

    @Override
    public UserRole update(UserRole userRole) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
