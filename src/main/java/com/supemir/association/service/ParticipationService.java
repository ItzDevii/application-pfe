package com.supemir.association.service;

import com.supemir.association.dto.ParticipationDto;

import java.util.List;

public interface ParticipationService {
    ParticipationDto create(ParticipationDto dto);
    void delete(Long id);
    List<ParticipationDto> getAll();
}
