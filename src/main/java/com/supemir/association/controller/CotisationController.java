package com.supemir.association.controller;

import com.supemir.association.dto.CotisationDto;
import com.supemir.association.service.CotisationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotisations")
@RequiredArgsConstructor
@Validated
public class CotisationController {
    private final CotisationService cotisationService;

    @PostMapping
    public ResponseEntity<CotisationDto> create(@Valid @RequestBody CotisationDto dto) {
        CotisationDto created = cotisationService.createCotisation(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CotisationDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(cotisationService.getCotisationById(id));
    }

    @GetMapping
    public ResponseEntity<List<CotisationDto>> getAll() {
        return ResponseEntity.ok(cotisationService.getAllCotisations());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CotisationDto> update(@PathVariable Long id,
                                                @Valid @RequestBody CotisationDto dto) {
        return ResponseEntity.ok(cotisationService.updateCotisation(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cotisationService.deleteCotisation(id);
    }
}
