package com.supemir.association.service;

import com.supemir.association.dto.AuditLogDto;

import java.util.List;

public interface AuditLogService {
    AuditLogDto createAuditLog(AuditLogDto dto);
    AuditLogDto getAuditLogById(Long id);
    List<AuditLogDto> getAllAuditLogs();
    AuditLogDto updateAuditLog(Long id, AuditLogDto dto);
    void deleteAuditLog(Long id);
}
