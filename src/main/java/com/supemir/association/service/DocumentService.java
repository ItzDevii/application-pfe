package com.supemir.association.service;

import com.supemir.association.dto.DocumentDto;

import java.util.List;

public interface DocumentService {
    DocumentDto createDocument(DocumentDto dto);
    DocumentDto getDocumentById(Long id);
    List<DocumentDto> getAllDocuments();
    DocumentDto updateDocument(Long id, DocumentDto dto);
    void deleteDocument(Long id);
}
