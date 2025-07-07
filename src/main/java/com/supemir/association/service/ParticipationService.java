package com.supemir.association.service;

import com.supemir.association.dto.ParticipationDto;

import java.util.List;

public interface ParticipationService {
    List<ParticipationDto> getAll();

    ParticipationDto getById(Long id);

    ParticipationDto create(ParticipationDto participationDto);

    ParticipationDto update(Long id, ParticipationDto participationDto);

    void delete(Long id);
}
