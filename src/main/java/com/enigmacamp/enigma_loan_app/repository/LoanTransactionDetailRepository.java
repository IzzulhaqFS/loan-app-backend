package com.enigmacamp.enigma_loan_app.repository;

import com.enigmacamp.enigma_loan_app.entity.LoanTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanTransactionDetailRepository extends JpaRepository<LoanTransactionDetail, String> {

    @Query(value = "SELECT * FROM t_loan_transaction_detail WHERE trx_id = :id", nativeQuery = true)
    List<LoanTransactionDetail> getByTransaction(@Param("id") String id);
}
