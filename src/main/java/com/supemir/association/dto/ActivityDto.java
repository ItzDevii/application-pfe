package com.supemir.association.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {
    private Long id;
    private String name;
    private LocalDate eventDate;
    private String description;
}
