package com.supemir.association.mapper;

import com.supemir.association.dto.CotisationDto;
import com.supemir.association.entity.Cotisation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CotisationMapper {

    @Mapping(target = "member.id", source = "memberId")
    Cotisation toEntity(CotisationDto dto);

    @Mapping(target = "memberId", source = "member.id")
    CotisationDto toDto(Cotisation cot);
}
