package com.supemir.association.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Document name is required")
    private String name;

    @NotBlank(message = "Document type is required")
    private String type;

    @NotBlank(message = "File path is required")
    private String path;

    @NotNull(message = "Uploader ID is required")
    private Long uploadedBy;
}
