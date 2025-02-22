package com.enigmacamp.enigma_loan_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;
}
