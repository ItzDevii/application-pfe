package com.supemir.association.service.impl;

import com.supemir.association.dto.ContributionDto;
import com.supemir.association.entity.Contribution;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.ContributionMapper;
import com.supemir.association.repository.ContributionRepository;
import com.supemir.association.service.ContributionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContributionServiceImpl implements ContributionService {
    private final ContributionRepository contributionRepository;
    private final ContributionMapper contributionMapper;

    @Override
    public List<ContributionDto> getAll() {
        return contributionRepository.findAll()
                .stream()
                .map(contributionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContributionDto getById(Long id) {
        Contribution contribution = contributionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contribution not found with id " + id));
        return contributionMapper.toDto(contribution);
    }

    @Override
    public ContributionDto create(ContributionDto dto) {
        Contribution contribution = contributionMapper.toEntity(dto);
        Contribution saved = contributionRepository.save(contribution);
        return contributionMapper.toDto(saved);
    }

    @Override
    public ContributionDto update(Long id, ContributionDto dto) {
        Contribution existing = contributionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contribution not found with id " + id));

        Contribution updated = contributionMapper.toEntity(dto);
        updated.setId(existing.getId());

        return contributionMapper.toDto(contributionRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        if (!contributionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contribution not found with id " + id);
        }
        contributionRepository.deleteById(id);
    }
}
