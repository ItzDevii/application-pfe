package com.supemir.association.service;

import com.supemir.association.dto.ContributionDto;

import java.util.List;

public interface CotisationService {
    ContributionDto create(ContributionDto dto);
    ContributionDto update(Long id, ContributionDto dto);
    void delete(Long id);
    ContributionDto getById(Long id);
    List<ContributionDto> getAll();
}
