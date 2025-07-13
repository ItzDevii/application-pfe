package com.supemir.association.service;

import com.supemir.association.dto.CreateUserDto;
import com.supemir.association.dto.UserDto;
import com.supemir.association.entity.User;
import com.supemir.association.enums.Role;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.UserMapper;
import com.supemir.association.repository.UserRepository;
import com.supemir.association.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private CreateUserDto createDto;
    private User user;
    private User savedUser;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        createDto = new CreateUserDto("john", "secret", Role.USER.name());
        user = new User(null, "john", "secret", Role.USER);
        savedUser = new User(1L, "john", "encoded", Role.USER);
        userDto = new UserDto(1L, "john", Role.USER.name());
    }

    @Test
    void createUser_shouldEncodePasswordAndReturnDto() {
        when(userMapper.toEntity(createDto)).thenReturn(user);
        when(passwordEncoder.encode("secret")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(userMapper.toDto(savedUser)).thenReturn(userDto);

        UserDto result = userService.createUser(createDto);

        assertEquals(userDto, result);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertEquals("encoded", captor.getValue().getPassword());
    }

    @Test
    void getUserById_shouldReturnDto() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(savedUser));
        when(userMapper.toDto(savedUser)).thenReturn(userDto);

        UserDto result = userService.getUserById(1L);

        assertEquals(userDto, result);
    }

    @Test
    void getUserById_whenNotFound_shouldThrowException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
    }
}
