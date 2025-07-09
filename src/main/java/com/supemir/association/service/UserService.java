package com.supemir.association.service;

import com.supemir.association.dto.CreateUserDto;
import com.supemir.association.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(CreateUserDto dto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, CreateUserDto dto);
    void deleteUser(Long id);
}
