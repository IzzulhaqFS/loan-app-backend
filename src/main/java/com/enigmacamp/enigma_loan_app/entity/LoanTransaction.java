package com.enigmacamp.enigma_loan_app.entity;

import com.enigmacamp.enigma_loan_app.constant.ApprovalStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_loan_transaction")
public class LoanTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "instalment_type_id")
    private InstalmentType instalmentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "nominal", nullable = false)
    private Double nominal;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approved_at", nullable = false)
    private Date approvedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approved_by", nullable = false)
    private String approvedBy;

    @Column(name = "approval_status", nullable = false)
    private ApprovalStatus approvalStatus;

    @OneToMany
    @JsonManagedReference
    private List<LoanTransactionDetail> loanTransactionDetails;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
