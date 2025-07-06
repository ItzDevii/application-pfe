package com.supemir.association.service;

import com.supemir.association.dto.DocumentDto;

import java.util.List;

public interface DocumentService {
    DocumentDto create(DocumentDto dto);
    void delete(Long id);
    List<DocumentDto> getAll();
}
