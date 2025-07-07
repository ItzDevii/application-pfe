package com.supemir.association.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CotisationDto {
    private Long id;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private Long memberId;
}
