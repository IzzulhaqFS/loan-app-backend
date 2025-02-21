package com.enigmacamp.enigma_loan_app.service;

import com.enigmacamp.enigma_loan_app.dto.request.NewRoleRequest;
import com.enigmacamp.enigma_loan_app.entity.Role;

public interface RoleService {
    Role getById(String id);
    Role getByName(String name);
    Role create(NewRoleRequest request);
}
