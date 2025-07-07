package com.supemir.association.controller;

import com.supemir.association.dto.AuditLogDto;
import com.supemir.association.service.AuditLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<List<AuditLogDto>> getAll() {
        return ResponseEntity.ok(auditLogService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLogDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(auditLogService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AuditLogDto> create(@Valid @RequestBody AuditLogDto auditLogDto) {
        return ResponseEntity.ok(auditLogService.create(auditLogDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        auditLogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
