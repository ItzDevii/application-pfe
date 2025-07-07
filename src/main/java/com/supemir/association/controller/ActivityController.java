package com.supemir.association.controller;

import com.supemir.association.dto.ActivityDto;
import com.supemir.association.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<ActivityDto>> getAll() {
        return ResponseEntity.ok(activityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ActivityDto> create(@Valid @RequestBody ActivityDto activityDto) {
        return ResponseEntity.ok(activityService.create(activityDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDto> update(@PathVariable Long id, @Valid @RequestBody ActivityDto activityDto) {
        return ResponseEntity.ok(activityService.update(id, activityDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}