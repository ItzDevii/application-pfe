package com.supemir.association.service;

import com.supemir.association.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    ActivityDto createActivity(ActivityDto dto);
    ActivityDto getActivityById(Long id);
    List<ActivityDto> getAllActivities();
    ActivityDto updateActivity(Long id, ActivityDto dto);
    void deleteActivity(Long id);
}
