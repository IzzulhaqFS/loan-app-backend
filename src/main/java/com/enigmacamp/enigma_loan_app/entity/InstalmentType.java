package com.enigmacamp.enigma_loan_app.entity;

import com.enigmacamp.enigma_loan_app.constant.EInstalmentType;
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
@Table(name = "m_instalment_type")
public class InstalmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "instalment_type", nullable = false)
    private EInstalmentType instalmentType;
}
