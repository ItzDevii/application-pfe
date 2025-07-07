package com.supemir.association.service.impl;

import com.supemir.association.dto.ActivityDto;
import com.supemir.association.entity.Activity;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.ActivityMapper;
import com.supemir.association.repository.ActivityRepository;
import com.supemir.association.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    @Override
    public List<ActivityDto> getAll() {
        return activityRepository.findAll()
                .stream()
                .map(activityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityDto getById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id " + id));
        return activityMapper.toDto(activity);
    }

    @Override
    public ActivityDto create(ActivityDto dto) {
        Activity activity = activityMapper.toEntity(dto);
        Activity saved = activityRepository.save(activity);
        return activityMapper.toDto(saved);
    }

    @Override
    public ActivityDto update(Long id, ActivityDto dto) {
        Activity existing = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id " + id));

        Activity updated = activityMapper.toEntity(dto);
        updated.setId(existing.getId());

        return activityMapper.toDto(activityRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Activity not found with id " + id);
        }
        activityRepository.deleteById(id);
    }
}
