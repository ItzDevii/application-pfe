package com.supemir.association.mapper;

import com.supemir.association.dto.CreateUserDto;
import com.supemir.association.dto.UserDto;
import com.supemir.association.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(CreateUserDto dto);

    UserDto toDto(User user);
}
