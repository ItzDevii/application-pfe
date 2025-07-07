package com.supemir.association.mapper;

import com.supemir.association.dto.UserDto;
import com.supemir.association.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto dto);
}
