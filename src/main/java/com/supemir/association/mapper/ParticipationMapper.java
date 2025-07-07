package com.supemir.association.mapper;

import com.supemir.association.dto.ParticipationDto;
import com.supemir.association.entity.Participation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {

    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "activity.id", target = "activityId")
    ParticipationDto toDto(Participation participation);

    @Mapping(source = "memberId", target = "member.id")
    @Mapping(source = "activityId", target = "activity.id")
    Participation toEntity(ParticipationDto dto);
}
