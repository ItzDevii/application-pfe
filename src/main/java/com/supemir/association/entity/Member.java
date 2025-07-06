package com.supemir.association.entity;

import com.supemir.association.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "members")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
