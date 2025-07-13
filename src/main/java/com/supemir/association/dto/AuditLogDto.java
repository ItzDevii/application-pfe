package com.supemir.association.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogDto {
    private Long id;

    @NotBlank(message = "Action is required")
    private String action;

    @NotNull(message = "Timestamp is required")
    private LocalDate timestamp;

    @NotNull(message = "User ID is required")
    private Long userId;
}
