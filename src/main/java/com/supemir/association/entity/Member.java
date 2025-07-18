package com.supemir.association.entity;

import com.supemir.association.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "membre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String firstName;

    @Column(name = "prenom", nullable = false)
    private String lastName;

    @Column(name = "date_adhesion", nullable = false)
    private LocalDate joinDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private MemberStatus status;

}