package com.supemir.association.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDto {
    private Long id;
    private String title;
    private String description;
    private String filePath;
    private Long memberId;
}
