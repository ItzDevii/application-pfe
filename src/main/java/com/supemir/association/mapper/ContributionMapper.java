package com.supemir.association.mapper;

import com.supemir.association.dto.ContributionDto;
import com.supemir.association.entity.Contribution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContributionMapper {

    @Mapping(source = "member.id", target = "memberId")
    ContributionDto toDto(Contribution contribution);

    @Mapping(source = "memberId", target = "member.id")
    Contribution toEntity(ContributionDto dto);
}
