package com.supemir.association.service.impl;

import com.supemir.association.dto.ActivityDto;
import com.supemir.association.entity.Activity;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.ActivityMapper;
import com.supemir.association.repository.ActivityRepository;
import com.supemir.association.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    @Override
    @Transactional
    public ActivityDto createActivity(ActivityDto dto) {
        Activity activity = activityMapper.toEntity(dto);
        Activity saved = activityRepository.save(activity);
        return activityMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ActivityDto getActivityById(Long id) {
        Activity act = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id " + id));
        return activityMapper.toDto(act);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityDto> getAllActivities() {
        return activityRepository.findAll().stream()
                .map(activityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ActivityDto updateActivity(Long id, ActivityDto dto) {
        Activity existing = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id " + id));
        Activity updatedEntity = activityMapper.toEntity(dto);
        updatedEntity.setId(existing.getId());
        Activity updated = activityRepository.save(updatedEntity);
        return activityMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Activity not found with id " + id);
        }
        activityRepository.deleteById(id);
    }
}
