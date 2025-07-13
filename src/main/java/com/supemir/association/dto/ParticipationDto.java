package com.supemir.association.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDto {
    private Long id;

    @NotNull(message = "Member ID is required")
    private Long memberId;

    @NotNull(message = "Activity ID is required")
    private Long activityId;

    @NotNull(message = "Signup date is required")
    @PastOrPresent(message = "Signup date cannot be in the future")
    private LocalDate signupDate;
}