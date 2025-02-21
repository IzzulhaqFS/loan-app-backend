package com.enigmacamp.enigma_loan_app.entity;

import com.enigmacamp.enigma_loan_app.constant.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_loan_transaction_detail")
public class LoanTransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "trx_date", nullable = false)
    private Date transactionDate;

    @Column(name = "nominal", nullable = false)
    private Double nominal;

    @ManyToOne
    @JoinColumn(name = "trx_id")
    private LoanTransaction loanTransaction;

    @ManyToOne
    @JoinColumn(name = "loan_status_id")
    private LoanStatus loanStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
