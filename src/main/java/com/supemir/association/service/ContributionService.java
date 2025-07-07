package com.supemir.association.service;

import com.supemir.association.dto.ContributionDto;

import java.util.List;

public interface ContributionService {
    List<ContributionDto> getAll();

    ContributionDto getById(Long id);

    ContributionDto create(ContributionDto contributionDto);

    ContributionDto update(Long id, ContributionDto contributionDto);

    void delete(Long id);
}
