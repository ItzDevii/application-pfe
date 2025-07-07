package com.supemir.association.service.impl;

import com.supemir.association.dto.ParticipationDto;
import com.supemir.association.entity.Participation;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.ParticipationMapper;
import com.supemir.association.repository.ParticipationRepository;
import com.supemir.association.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRepository participationRepository;
    private final ParticipationMapper participationMapper;

    @Override
    public List<ParticipationDto> getAll() {
        return participationRepository.findAll()
                .stream()
                .map(participationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationDto getById(Long id) {
        Participation participation = participationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participation not found with id " + id));
        return participationMapper.toDto(participation);
    }

    @Override
    public ParticipationDto create(ParticipationDto dto) {
        Participation participation = participationMapper.toEntity(dto);
        Participation saved = participationRepository.save(participation);
        return participationMapper.toDto(saved);
    }

    @Override
    public ParticipationDto update(Long id, ParticipationDto dto) {
        Participation existing = participationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participation not found with id " + id));

        Participation updated = participationMapper.toEntity(dto);
        updated.setId(existing.getId());

        return participationMapper.toDto(participationRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        if (!participationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Participation not found with id " + id);
        }
        participationRepository.deleteById(id);
    }
}
