package com.enigmacamp.enigma_loan_app.entity;

import com.enigmacamp.enigma_loan_app.constant.ERole;
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
@Table(name = "m_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "role", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ERole role;
}
