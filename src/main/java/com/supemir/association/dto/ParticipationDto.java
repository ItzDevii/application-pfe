package com.supemir.association.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipationDto {
    private Long id;
    private Long memberId;
    private Long activityId;
}
