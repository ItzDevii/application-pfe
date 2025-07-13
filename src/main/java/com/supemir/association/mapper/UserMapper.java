package com.supemir.association.mapper;

import com.supemir.association.dto.CreateUserDto;
import com.supemir.association.dto.UserDto;
import com.supemir.association.entity.User;
import com.supemir.association.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(CreateUserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(Role.valueOf(dto.getRole()));
        return user;
    }

    public UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().name());
        return dto;
    }
}
