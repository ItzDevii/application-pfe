package com.supemir.association.service.impl;

import com.supemir.association.dto.AuditLogDto;
import com.supemir.association.entity.AuditLog;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.AuditLogMapper;
import com.supemir.association.repository.AuditLogRepository;
import com.supemir.association.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogRepository auditLogRepository;
    private final AuditLogMapper auditLogMapper;

    @Override
    public List<AuditLogDto> getAll() {
        return auditLogRepository.findAll()
                .stream()
                .map(auditLogMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuditLogDto getById(Long id) {
        AuditLog log = auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit log not found with id " + id));
        return auditLogMapper.toDto(log);
    }

    @Override
    public AuditLogDto create(AuditLogDto dto) {
        AuditLog log = auditLogMapper.toEntity(dto);
        AuditLog saved = auditLogRepository.save(log);
        return auditLogMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!auditLogRepository.existsById(id)) {
            throw new ResourceNotFoundException("Audit log not found with id " + id);
        }
        auditLogRepository.deleteById(id);
    }
}
