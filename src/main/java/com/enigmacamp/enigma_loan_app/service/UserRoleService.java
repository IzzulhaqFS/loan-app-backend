package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.entity.UserRole;

public interface UserRoleService {
    UserRole getById(String id);
    UserRole create(UserRole userRole);
    UserRole update(UserRole userRole);
    void delete(String id);
}
