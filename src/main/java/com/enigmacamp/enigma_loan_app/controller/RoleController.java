package com.enigmacamp.enigma_loan_app.controller;

import com.enigmacamp.enigma_loan_app.dto.request.NewRoleRequest;
import com.enigmacamp.enigma_loan_app.entity.Role;
import com.enigmacamp.enigma_loan_app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody NewRoleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(request));
    }
}
