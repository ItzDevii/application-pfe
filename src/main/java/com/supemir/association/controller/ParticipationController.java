package com.supemir.association.controller;

import com.supemir.association.dto.ParticipationDto;
import com.supemir.association.service.ParticipationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @GetMapping
    public ResponseEntity<List<ParticipationDto>> getAll() {
        return ResponseEntity.ok(participationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(participationService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ParticipationDto> create(@Valid @RequestBody ParticipationDto participationDto) {
        return ResponseEntity.ok(participationService.create(participationDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipationDto> update(@PathVariable Long id, @Valid @RequestBody ParticipationDto participationDto) {
        return ResponseEntity.ok(participationService.update(id, participationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        participationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
