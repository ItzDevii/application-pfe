package com.supemir.association.mapper;

import com.supemir.association.dto.AuditLogDto;
import com.supemir.association.entity.AuditLog;
import com.supemir.association.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuditLogMapper {
    public AuditLog toEntity(AuditLogDto dto) {
        if (dto == null) return null;
        AuditLog log = new AuditLog();
        log.setId(dto.getId());
        log.setAction(dto.getAction());
        log.setTimestamp(dto.getTimestamp());
        User u = new User(); u.setId(dto.getUserId()); log.setUser(u);
        return log;
    }

    public AuditLogDto toDto(AuditLog auditLog) {
        if (auditLog == null) return null;
        AuditLogDto dto = new AuditLogDto();
        dto.setId(auditLog.getId());
        dto.setAction(auditLog.getAction());
        dto.setTimestamp(auditLog.getTimestamp());
        dto.setUserId(auditLog.getUser().getId());
        return dto;
    }
}