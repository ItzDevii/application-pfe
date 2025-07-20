package com.supemir.association.controller;

import com.supemir.association.dto.ActivityDto;
import com.supemir.association.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
@Validated
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDto> create(@Valid @RequestBody ActivityDto dto) {
        ActivityDto created = activityService.createActivity(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.getActivityById(id));
    }

    @GetMapping
    public ResponseEntity<List<ActivityDto>> getAll() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDto> update(@PathVariable Long id,
                                              @Valid @RequestBody ActivityDto dto) {
        return ResponseEntity.ok(activityService.updateActivity(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }
}
