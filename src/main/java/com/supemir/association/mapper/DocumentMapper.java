package com.supemir.association.mapper;

import com.supemir.association.dto.DocumentDto;
import com.supemir.association.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(source = "uploadedBy.id", target = "uploadedBy")
    DocumentDto toDto(Document document);

    @Mapping(source = "uploadedBy", target = "uploadedBy.id")
    Document toEntity(DocumentDto dto);
}
