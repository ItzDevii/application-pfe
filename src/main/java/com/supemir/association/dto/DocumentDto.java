package com.supemir.association.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private Long id;

    @NotBlank(message = "Document name is required")
    private String name;

    @NotBlank(message = "URL is required")
    @Pattern(regexp = "https?://.+", message = "URL must be valid")
    private String url;

    @NotNull(message = "Member ID is required")
    private Long memberId;
}
