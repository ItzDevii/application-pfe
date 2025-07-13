package com.supemir.association.mapper;

import com.supemir.association.dto.AuditLogDto;
import com.supemir.association.entity.AuditLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditLogMapper {

    @Mapping(target = "user.id", source = "userId")
    AuditLog toEntity(AuditLogDto dto);

    @Mapping(target = "userId", source = "user.id")
    AuditLogDto toDto(AuditLog auditLog);
}