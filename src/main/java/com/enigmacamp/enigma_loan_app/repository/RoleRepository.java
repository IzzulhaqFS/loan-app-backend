package com.enigmacamp.enigma_loan_app.repository;

import com.enigmacamp.enigma_loan_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
