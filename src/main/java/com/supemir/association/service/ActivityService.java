package com.supemir.association.service;

import com.supemir.association.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    List<ActivityDto> getAll();

    ActivityDto getById(Long id);

    ActivityDto create(ActivityDto activityDto);

    ActivityDto update(Long id, ActivityDto activityDto);

    void delete(Long id);
}
