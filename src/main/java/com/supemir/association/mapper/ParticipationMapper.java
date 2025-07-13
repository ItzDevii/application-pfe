package com.supemir.association.mapper;

import com.supemir.association.dto.ParticipationDto;
import com.supemir.association.entity.Participation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {

    @Mapping(target = "member.id", source = "memberId")
    @Mapping(target = "activity.id", source = "activityId")
    Participation toEntity(ParticipationDto dto);

    @Mapping(target = "memberId", source = "member.id")
    @Mapping(target = "activityId", source = "activity.id")
    ParticipationDto toDto(Participation participation);
}
