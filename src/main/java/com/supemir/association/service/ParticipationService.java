package com.supemir.association.service;

import com.supemir.association.dto.ParticipationDto;

import java.util.List;

public interface ParticipationService {
    ParticipationDto createParticipation(ParticipationDto dto);
    ParticipationDto getParticipationById(Long id);
    List<ParticipationDto> getAllParticipations();
    ParticipationDto updateParticipation(Long id, ParticipationDto dto);
    void deleteParticipation(Long id);
}
