package com.supemir.association.entity;

import com.supemir.association.enums.ContributionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contributions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ContributionStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
