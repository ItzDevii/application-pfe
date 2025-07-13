package com.supemir.association.service;

import com.supemir.association.dto.ActivityDto;
import com.supemir.association.entity.Activity;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.ActivityMapper;
import com.supemir.association.repository.ActivityRepository;
import com.supemir.association.service.impl.ActivityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    @Mock
    private ActivityRepository activityRepository;
    @Mock
    private ActivityMapper activityMapper;

    @InjectMocks
    private ActivityServiceImpl activityService;

    private ActivityDto dto;
    private Activity entity;
    private Activity savedEntity;
    private ActivityDto savedDto;

    @BeforeEach
    void setUp() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        dto = new ActivityDto(null, "event", date, "desc");
        entity = new Activity(null, "event", date, "desc");
        savedEntity = new Activity(1L, "event", date, "desc");
        savedDto = new ActivityDto(1L, "event", date, "desc");
    }

    @Test
    void createActivity_shouldSaveAndReturnDto() {
        when(activityMapper.toEntity(dto)).thenReturn(entity);
        when(activityRepository.save(entity)).thenReturn(savedEntity);
        when(activityMapper.toDto(savedEntity)).thenReturn(savedDto);

        ActivityDto result = activityService.createActivity(dto);

        assertEquals(savedDto, result);
        verify(activityRepository).save(entity);
    }

    @Test
    void getActivityById_shouldReturnDto() {
        when(activityRepository.findById(1L)).thenReturn(Optional.of(savedEntity));
        when(activityMapper.toDto(savedEntity)).thenReturn(savedDto);

        ActivityDto result = activityService.getActivityById(1L);

        assertEquals(savedDto, result);
    }

    @Test
    void getActivityById_whenNotFound_shouldThrowException() {
        when(activityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> activityService.getActivityById(1L));
    }

    @Test
    void getAllActivities_shouldReturnDtos() {
        when(activityRepository.findAll()).thenReturn(List.of(savedEntity));
        when(activityMapper.toDto(savedEntity)).thenReturn(savedDto);

        List<ActivityDto> result = activityService.getAllActivities();

        assertEquals(List.of(savedDto), result);
    }

    @Test
    void updateActivity_shouldUpdateAndReturnDto() {
        ActivityDto updateDto = new ActivityDto(null, "new", LocalDate.of(2024,2,2), "newDesc");
        Activity updateEntity = new Activity(null, "new", LocalDate.of(2024,2,2), "newDesc");
        Activity updatedEntity = new Activity(1L, "new", LocalDate.of(2024,2,2), "newDesc");
        ActivityDto updatedDto = new ActivityDto(1L, "new", LocalDate.of(2024,2,2), "newDesc");

        when(activityRepository.findById(1L)).thenReturn(Optional.of(savedEntity));
        when(activityMapper.toEntity(updateDto)).thenReturn(updateEntity);
        when(activityRepository.save(any(Activity.class))).thenReturn(updatedEntity);
        when(activityMapper.toDto(updatedEntity)).thenReturn(updatedDto);

        ActivityDto result = activityService.updateActivity(1L, updateDto);

        assertEquals(updatedDto, result);

        ArgumentCaptor<Activity> captor = ArgumentCaptor.forClass(Activity.class);
        verify(activityRepository).save(captor.capture());
        assertEquals(1L, captor.getValue().getId());
    }

    @Test
    void deleteActivity_shouldDeleteWhenExists() {
        when(activityRepository.existsById(1L)).thenReturn(true);

        activityService.deleteActivity(1L);

        verify(activityRepository).deleteById(1L);
    }

    @Test
    void deleteActivity_whenNotFound_shouldThrowException() {
        when(activityRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> activityService.deleteActivity(1L));
        verify(activityRepository, never()).deleteById(anyLong());
    }
}
