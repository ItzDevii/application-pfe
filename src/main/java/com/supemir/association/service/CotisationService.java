package com.supemir.association.service;

import com.supemir.association.dto.CotisationDto;

import java.util.List;

public interface CotisationService {
    CotisationDto create(CotisationDto dto);
    CotisationDto update(Long id, CotisationDto dto);
    void delete(Long id);
    CotisationDto getById(Long id);
    List<CotisationDto> getAll();
}
