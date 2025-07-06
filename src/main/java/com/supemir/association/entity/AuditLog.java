package com.supemir.association.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "entity", nullable = false)
    private String entity;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}
