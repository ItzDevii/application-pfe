package com.supemir.association.service.impl;

import com.supemir.association.dto.DocumentDto;
import com.supemir.association.entity.Document;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.DocumentMapper;
import com.supemir.association.repository.DocumentRepository;
import com.supemir.association.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Override
    @Transactional
    public DocumentDto createDocument(DocumentDto dto) {
        Document doc = documentMapper.toEntity(dto);
        Document saved = documentRepository.save(doc);
        return documentMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentDto getDocumentById(Long id) {
        Document doc = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));
        return documentMapper.toDto(doc);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentDto> getAllDocuments() {
        return documentRepository.findAll().stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DocumentDto updateDocument(Long id, DocumentDto dto) {
        Document existing = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));
        Document updatedEntity = documentMapper.toEntity(dto);
        updatedEntity.setId(existing.getId());
        Document updated = documentRepository.save(updatedEntity);
        return documentMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Document not found with id " + id);
        }
        documentRepository.deleteById(id);
    }
}
