package com.supemir.association.controller;

import com.supemir.association.dto.ContributionDto;
import com.supemir.association.service.ContributionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contributions")
@RequiredArgsConstructor
public class ContributionController {

    private final ContributionService contributionService;

    @GetMapping
    public ResponseEntity<List<ContributionDto>> getAll() {
        return ResponseEntity.ok(contributionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContributionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contributionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ContributionDto> create(@Valid @RequestBody ContributionDto contributionDto) {
        return ResponseEntity.ok(contributionService.create(contributionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContributionDto> update(@PathVariable Long id, @Valid @RequestBody ContributionDto contributionDto) {
        return ResponseEntity.ok(contributionService.update(id, contributionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contributionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
