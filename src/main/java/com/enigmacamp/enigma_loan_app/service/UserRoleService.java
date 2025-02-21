package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.entity.AppUser;
import com.enigmacamp.enigma_loan_app.entity.Role;
import com.enigmacamp.enigma_loan_app.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole getById(String id);
    List<UserRole> getByUser(AppUser user);
    List<UserRole> getByRole(Role role);
    UserRole create(UserRole userRole);
    UserRole update(UserRole userRole);
    void delete(String id);
}
