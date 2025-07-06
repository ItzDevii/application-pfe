package com.supemir.association.service;

import com.supemir.association.entity.AuditLog;

import java.util.List;

public interface AuditLogService {
    AuditLog createAuditLog(AuditLog auditLog);
    List<AuditLog> getAllAuditLogs();
    AuditLog getAuditLogById(Long id);
    void deleteAuditLog(Long id);
}
