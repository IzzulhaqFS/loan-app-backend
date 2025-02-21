package com.enigmacamp.enigma_loan_app.service.impl;

import com.enigmacamp.enigma_loan_app.constant.ERole;
import com.enigmacamp.enigma_loan_app.dto.request.NewRoleRequest;
import com.enigmacamp.enigma_loan_app.entity.Role;
import com.enigmacamp.enigma_loan_app.repository.RoleRepository;
import com.enigmacamp.enigma_loan_app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getById(String id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found."));
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.getByName(name).orElseThrow(() -> new RuntimeException("Role not found."));
    }

    @Override
    public Role create(NewRoleRequest request) {
        ERole roleType = null;
        if (request != null) {
            if (request.getName().equals("ROLE_ADMIN")) {
                roleType = ERole.ROLE_ADMIN;
            } else if (request.getName().equals("ROLE_CUSTOMER")) {
                roleType = ERole.ROLE_CUSTOMER;
            } else if (request.getName().equals("ROLE_STAFF")) {
                roleType = ERole.ROLE_STAFF;
            }
        }

        Role role = Role.builder()
                .role(roleType)
                .build();

        return roleRepository.saveAndFlush(role);
    }
}
