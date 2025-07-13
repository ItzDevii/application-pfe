package com.supemir.association.service;

import com.supemir.association.dto.CreateUserDto;
import com.supemir.association.dto.UserDto;
import com.supemir.association.entity.User;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.UserMapper;
import com.supemir.association.repository.UserRepository;
import com.supemir.association.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    void createUser_savesUserAndReturnsDto() {
        CreateUserDto dto = new CreateUserDto("john", "secret", "ADMIN");
        User entity = new User();
        User saved = new User();
        saved.setId(1L);
        UserDto expectedDto = new UserDto(1L, "john", "ADMIN");

        when(userMapper.toEntity(dto)).thenReturn(entity);
        when(passwordEncoder.encode("secret")).thenReturn("enc");
        when(userRepository.save(entity)).thenReturn(saved);
        when(userMapper.toDto(saved)).thenReturn(expectedDto);

        UserDto result = userService.createUser(dto);

        assertThat(result).isEqualTo(expectedDto);
        assertThat(entity.getPassword()).isEqualTo("enc");

        verify(userMapper).toEntity(dto);
        verify(passwordEncoder).encode("secret");
        verify(userRepository).save(entity);
        verify(userMapper).toDto(saved);
    }

    @Test
    void getUserById_returnsUserDto() {
        User user = new User();
        user.setId(2L);
        UserDto expectedDto = new UserDto(2L, "bob", "USER");

        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(expectedDto);

        UserDto result = userService.getUserById(2L);

        assertThat(result).isEqualTo(expectedDto);
        verify(userRepository).findById(2L);
        verify(userMapper).toDto(user);
    }

    @Test
    void getUserById_notFound_throwsException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(99L))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(userRepository).findById(99L);
    }
}
