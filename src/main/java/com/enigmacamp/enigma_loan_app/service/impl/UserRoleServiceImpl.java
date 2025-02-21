package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.entity.AppUser;
import com.enigmacamp.enigma_loan_app.entity.Role;
import com.enigmacamp.enigma_loan_app.entity.UserRole;
import com.enigmacamp.enigma_loan_app.repository.UserRoleRepository;
import com.enigmacamp.enigma_loan_app.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRole getById(String id) {
        return null;
    }

    @Override
    public List<UserRole> getByUser(AppUser user) {
        return userRoleRepository.getAllByUser(user.getId());
    }

    @Override
    public List<UserRole> getByRole(Role role) {
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
