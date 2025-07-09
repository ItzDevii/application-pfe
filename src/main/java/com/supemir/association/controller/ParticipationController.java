package com.supemir.association.controller;

import com.supemir.association.dto.ParticipationDto;
import com.supemir.association.service.ParticipationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
@RequiredArgsConstructor
@Validated
public class ParticipationController {
    private final ParticipationService participationService;

    @PostMapping
    public ResponseEntity<ParticipationDto> create(@Valid @RequestBody ParticipationDto dto) {
        ParticipationDto created = participationService.createParticipation(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipationDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(participationService.getParticipationById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParticipationDto>> getAll() {
        return ResponseEntity.ok(participationService.getAllParticipations());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipationDto> update(@PathVariable Long id,
                                                   @Valid @RequestBody ParticipationDto dto) {
        return ResponseEntity.ok(participationService.updateParticipation(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        participationService.deleteParticipation(id);
    }
}
