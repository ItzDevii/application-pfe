package com.supemir.association.mapper;

import com.supemir.association.dto.DocumentDto;
import com.supemir.association.entity.Document;
import com.supemir.association.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {
    public Document toEntity(DocumentDto dto) {
        if (dto == null) return null;
        Document doc = new Document();
        doc.setId(dto.getId());
        doc.setName(dto.getName());
        doc.setUrl(dto.getUrl());
        Member m = new Member(); m.setId(dto.getMemberId()); doc.setMember(m);
        return doc;
    }

    public DocumentDto toDto(Document document) {
        if (document == null) return null;
        DocumentDto dto = new DocumentDto();
        dto.setId(document.getId());
        dto.setName(document.getName());
        dto.setUrl(document.getUrl());
        dto.setMemberId(document.getMember().getId());
        return dto;
    }
}
