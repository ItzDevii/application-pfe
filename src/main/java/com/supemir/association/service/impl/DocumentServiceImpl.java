package com.supemir.association.service.impl;

import com.supemir.association.dto.DocumentDto;
import com.supemir.association.entity.Document;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.DocumentMapper;
import com.supemir.association.repository.DocumentRepository;
import com.supemir.association.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Override
    public List<DocumentDto> getAll() {
        return documentRepository.findAll()
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentDto getById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));
        return documentMapper.toDto(document);
    }

    @Override
    public DocumentDto create(DocumentDto dto) {
        Document document = documentMapper.toEntity(dto);
        Document saved = documentRepository.save(document);
        return documentMapper.toDto(saved);
    }

    @Override
    public DocumentDto update(Long id, DocumentDto dto) {
        Document existing = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));

        Document updated = documentMapper.toEntity(dto);
        updated.setId(existing.getId());

        return documentMapper.toDto(documentRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Document not found with id " + id);
        }
        documentRepository.deleteById(id);
    }
}
