package com.enigmacamp.enigma_loan_app.repository;

import com.enigmacamp.enigma_loan_app.entity.Customer;
import com.enigmacamp.enigma_loan_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE m_customer SET status = :status WHERE id = :id", nativeQuery = true)
    void softDelete(@Param("id") String id, @Param("status") String status);
}
