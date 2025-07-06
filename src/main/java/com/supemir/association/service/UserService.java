package com.supemir.association.service;

import com.supemir.association.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto dto);
    List<UserDto> getAll();
    UserDto getById(Long id);
}
