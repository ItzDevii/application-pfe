package com.supemir.association.service.impl;

import com.supemir.association.dto.CotisationDto;
import com.supemir.association.entity.Cotisation;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.CotisationMapper;
import com.supemir.association.repository.CotisationRepository;
import com.supemir.association.service.CotisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CotisationServiceImpl implements CotisationService {
    private final CotisationRepository cotisationRepository;
    private final CotisationMapper cotisationMapper;

    @Override
    @Transactional
    public CotisationDto createCotisation(CotisationDto dto) {
        Cotisation cot = cotisationMapper.toEntity(dto);
        Cotisation saved = cotisationRepository.save(cot);
        return cotisationMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CotisationDto getCotisationById(Long id) {
        Cotisation cot = cotisationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cotisation not found with id " + id));
        return cotisationMapper.toDto(cot);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CotisationDto> getAllCotisations() {
        return cotisationRepository.findAll().stream()
                .map(cotisationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CotisationDto updateCotisation(Long id, CotisationDto dto) {
        Cotisation existing = cotisationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cotisation not found with id " + id));
        Cotisation updatedEntity = cotisationMapper.toEntity(dto);
        updatedEntity.setId(existing.getId());
        Cotisation updated = cotisationRepository.save(updatedEntity);
        return cotisationMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteCotisation(Long id) {
        if (!cotisationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cotisation not found with id " + id);
        }
        cotisationRepository.deleteById(id);
    }
}
