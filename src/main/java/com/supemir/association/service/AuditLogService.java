package com.supemir.association.service;

import com.supemir.association.dto.AuditLogDto;

import java.util.List;

public interface AuditLogService {
    List<AuditLogDto> getAll();

    AuditLogDto getById(Long id);

    AuditLogDto create(AuditLogDto auditLogDto);

    void delete(Long id);
}
