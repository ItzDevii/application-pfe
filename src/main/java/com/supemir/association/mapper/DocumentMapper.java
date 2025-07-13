package com.supemir.association.mapper;

import com.supemir.association.dto.DocumentDto;
import com.supemir.association.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(target = "member.id", source = "memberId")
    Document toEntity(DocumentDto dto);

    @Mapping(target = "memberId", source = "member.id")
    DocumentDto toDto(Document document);
}
