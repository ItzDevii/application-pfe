package com.supemir.association.mapper;

import com.supemir.association.dto.ActivityDto;
import com.supemir.association.entity.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    Activity toEntity(ActivityDto dto);

    ActivityDto toDto(Activity activity);
}
