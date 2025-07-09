package com.supemir.association.service;

import com.supemir.association.dto.CotisationDto;

import java.util.List;

public interface CotisationService {
    CotisationDto createCotisation(CotisationDto dto);
    CotisationDto getCotisationById(Long id);
    List<CotisationDto> getAllCotisations();
    CotisationDto updateCotisation(Long id, CotisationDto dto);
    void deleteCotisation(Long id);
}
