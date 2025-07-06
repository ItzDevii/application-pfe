package com.supemir.association.service;

import com.supemir.association.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    ActivityDto create(ActivityDto dto);
    ActivityDto update(Long id, ActivityDto dto);
    void delete(Long id);
    ActivityDto getById(Long id);
    List<ActivityDto> getAll();
}
