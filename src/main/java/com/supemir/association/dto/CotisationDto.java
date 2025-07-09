package com.supemir.association.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CotisationDto {
    private Long id;
    private Double amount;
    private LocalDate paymentDate;
    private Long memberId;
}
