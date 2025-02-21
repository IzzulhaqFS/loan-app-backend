package com.enigmacamp.enigma_loan_app.repository;

import com.enigmacamp.enigma_loan_app.entity.Customer;
import com.enigmacamp.enigma_loan_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
