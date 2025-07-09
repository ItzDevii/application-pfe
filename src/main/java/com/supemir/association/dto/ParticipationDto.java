package com.supemir.association.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDto {
    private Long id;
    private Long memberId;
    private Long activityId;
    private LocalDate signupDate;
}