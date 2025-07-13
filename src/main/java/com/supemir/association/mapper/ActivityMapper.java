package com.supemir.association.mapper;

import com.supemir.association.dto.ActivityDto;
import com.supemir.association.entity.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {
    public Activity toEntity(ActivityDto dto) {
        if (dto == null) return null;
        Activity activity = new Activity();
        activity.setId(dto.getId());
        activity.setName(dto.getName());
        activity.setEventDate(dto.getEventDate());
        activity.setDescription(dto.getDescription());
        return activity;
    }

    public ActivityDto toDto(Activity activity) {
        if (activity == null) return null;
        ActivityDto dto = new ActivityDto();
        dto.setId(activity.getId());
        dto.setName(activity.getName());
        dto.setEventDate(activity.getEventDate());
        dto.setDescription(activity.getDescription());
        return dto;
    }
}
