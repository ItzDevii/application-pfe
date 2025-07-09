package com.supemir.association.service.impl;

import com.supemir.association.dto.ParticipationDto;
import com.supemir.association.entity.Participation;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.ParticipationMapper;
import com.supemir.association.repository.ParticipationRepository;
import com.supemir.association.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRepository participationRepository;
    private final ParticipationMapper participationMapper;

    @Override
    @Transactional
    public ParticipationDto createParticipation(ParticipationDto dto) {
        Participation p = participationMapper.toEntity(dto);
        Participation saved = participationRepository.save(p);
        return participationMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ParticipationDto getParticipationById(Long id) {
        Participation p = participationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participation not found with id " + id));
        return participationMapper.toDto(p);
    }

    @Override
    @Transactional
    public List<ParticipationDto> getAllParticipations() {
        return participationRepository.findAll().stream()
                .map(participationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParticipationDto updateParticipation(Long id, ParticipationDto dto) {
        Participation existing = participationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participation not found with id " + id));
        Participation updatedEntity = participationMapper.toEntity(dto);
        updatedEntity.setId(existing.getId());
        Participation updated = participationRepository.save(updatedEntity);
        return participationMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteParticipation(Long id) {
        if (!participationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Participation not found with id " + id);
        }
        participationRepository.deleteById(id);
    }
}
