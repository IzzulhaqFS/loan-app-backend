package com.enigmacamp.enigma_loan_app.repository;

import com.enigmacamp.enigma_loan_app.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    @Query(value = "SELECT * FROM t_user_role WHERE user_id = :userId", nativeQuery = true)
    List<UserRole> getAllByUser(@Param("userId") String userId);

    @Query(value = "SELECT * FROM t_user_role WHERE role_id = :roleId", nativeQuery = true)
    Optional<UserRole> getAllByRole(@Param("roleId") String roleId);
}
