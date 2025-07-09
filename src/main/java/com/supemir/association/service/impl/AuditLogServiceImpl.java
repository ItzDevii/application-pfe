package com.supemir.association.service.impl;

import com.supemir.association.dto.AuditLogDto;
import com.supemir.association.entity.AuditLog;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.AuditLogMapper;
import com.supemir.association.repository.AuditLogRepository;
import com.supemir.association.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogRepository auditLogRepository;
    private final AuditLogMapper auditLogMapper;

    @Override
    @Transactional
    public AuditLogDto createAuditLog(AuditLogDto dto) {
        AuditLog log = auditLogMapper.toEntity(dto);
        AuditLog saved = auditLogRepository.save(log);
        return auditLogMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AuditLogDto getAuditLogById(Long id) {
        AuditLog log = auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AuditLog not found with id " + id));
        return auditLogMapper.toDto(log);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditLogDto> getAllAuditLogs() {
        return auditLogRepository.findAll().stream()
                .map(auditLogMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuditLogDto updateAuditLog(Long id, AuditLogDto dto) {
        AuditLog existing = auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AuditLog not found with id " + id));
        AuditLog updatedEntity = auditLogMapper.toEntity(dto);
        updatedEntity.setId(existing.getId());
        AuditLog updated = auditLogRepository.save(updatedEntity);
        return auditLogMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteAuditLog(Long id) {
        if (!auditLogRepository.existsById(id)) {
            throw new ResourceNotFoundException("AuditLog not found with id " + id);
        }
        auditLogRepository.deleteById(id);
    }
}
