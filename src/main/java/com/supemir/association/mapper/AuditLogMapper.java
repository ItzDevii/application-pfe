package com.supemir.association.mapper;

import com.supemir.association.dto.AuditLogDto;
import com.supemir.association.entity.AuditLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditLogMapper {

    @Mapping(source = "user.id", target = "userId")
    AuditLogDto toDto(AuditLog auditLog);

    @Mapping(source = "userId", target = "user.id")
    AuditLog toEntity(AuditLogDto dto);
}
