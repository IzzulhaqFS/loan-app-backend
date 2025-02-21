package com.enigmacamp.enigma_loan_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_loan_type")
public class LoanType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "max_loan", nullable = false)
    private Double maxLoan;
}
