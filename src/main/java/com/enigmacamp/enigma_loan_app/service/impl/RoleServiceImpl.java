package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.entity.Role;
import com.enigmacamp.enigma_loan_app.repository.RoleRepository;
import com.enigmacamp.enigma_loan_app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getById(String id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found."));
    }
}
