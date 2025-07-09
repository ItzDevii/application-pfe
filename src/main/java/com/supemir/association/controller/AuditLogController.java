package com.supemir.association.controller;

import com.supemir.association.dto.AuditLogDto;
import com.supemir.association.service.AuditLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
@Validated
public class AuditLogController {
    private final AuditLogService auditLogService;

    @PostMapping
    public ResponseEntity<AuditLogDto> create(@Valid @RequestBody AuditLogDto dto) {
        AuditLogDto created = auditLogService.createAuditLog(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLogDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(auditLogService.getAuditLogById(id));
    }

    @GetMapping
    public ResponseEntity<List<AuditLogDto>> getAll() {
        return ResponseEntity.ok(auditLogService.getAllAuditLogs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditLogDto> update(@PathVariable Long id,
                                              @Valid @RequestBody AuditLogDto dto) {
        return ResponseEntity.ok(auditLogService.updateAuditLog(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        auditLogService.deleteAuditLog(id);
    }
}
