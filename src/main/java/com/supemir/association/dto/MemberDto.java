package com.supemir.association.dto;

import com.supemir.association.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate joinDate;
    private Status status;
}
