package com.supemir.association.service;

import com.supemir.association.dto.MemberDto;
import com.supemir.association.entity.Member;
import com.supemir.association.entity.User;
import com.supemir.association.enums.MemberStatus;
import com.supemir.association.enums.Role;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.MemberMapper;
import com.supemir.association.repository.MemberRepository;
import com.supemir.association.service.impl.MemberServiceImpl;
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
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberServiceImpl memberService;

    private MemberDto dto;
    private Member entity;
    private Member savedEntity;
    private MemberDto savedDto;

    @BeforeEach
    void setUp() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        User user = new User(1L, "u", "p", Role.USER);
        dto = new MemberDto(null, "fn", "ln", date, "ACTIVE", 1L);
        entity = new Member(null, "fn", "ln", date, MemberStatus.ACTIVE, user);
        savedEntity = new Member(1L, "fn", "ln", date, MemberStatus.ACTIVE, user);
        savedDto = new MemberDto(1L, "fn", "ln", date, "ACTIVE", 1L);
    }

    @Test
    void createMember_shouldSaveAndReturnDto() {
        when(memberMapper.toEntity(dto)).thenReturn(entity);
        when(memberRepository.save(entity)).thenReturn(savedEntity);
        when(memberMapper.toDto(savedEntity)).thenReturn(savedDto);

        MemberDto result = memberService.createMember(dto);

        assertEquals(savedDto, result);
        verify(memberRepository).save(entity);
    }

    @Test
    void getMemberById_shouldReturnDto() {
        when(memberRepository.findById(1L)).thenReturn(Optional.of(savedEntity));
        when(memberMapper.toDto(savedEntity)).thenReturn(savedDto);

        MemberDto result = memberService.getMemberById(1L);

        assertEquals(savedDto, result);
    }

    @Test
    void getMemberById_whenNotFound_shouldThrowException() {
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> memberService.getMemberById(1L));
    }

    @Test
    void getAllMembers_shouldReturnDtos() {
        when(memberRepository.findAll()).thenReturn(List.of(savedEntity));
        when(memberMapper.toDto(savedEntity)).thenReturn(savedDto);

        List<MemberDto> result = memberService.getAllMembers();

        assertEquals(List.of(savedDto), result);
    }

    @Test
    void updateMember_shouldUpdateAndReturnDto() {
        MemberDto updateDto = new MemberDto(null, "new", "name", LocalDate.of(2024,2,2), "INACTIVE", 1L);
        User user = new User(1L, "u", "p", Role.USER);
        Member updateEntity = new Member(null, "new", "name", LocalDate.of(2024,2,2), MemberStatus.INACTIVE, user);
        Member updatedEntity = new Member(1L, "new", "name", LocalDate.of(2024,2,2), MemberStatus.INACTIVE, user);
        MemberDto updatedDto = new MemberDto(1L, "new", "name", LocalDate.of(2024,2,2), "INACTIVE", 1L);

        when(memberRepository.findById(1L)).thenReturn(Optional.of(savedEntity));
        when(memberMapper.toEntity(updateDto)).thenReturn(updateEntity);
        when(memberRepository.save(any(Member.class))).thenReturn(updatedEntity);
        when(memberMapper.toDto(updatedEntity)).thenReturn(updatedDto);

        MemberDto result = memberService.updateMember(1L, updateDto);

        assertEquals(updatedDto, result);

        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
        verify(memberRepository).save(captor.capture());
        assertEquals(1L, captor.getValue().getId());
    }

    @Test
    void deleteMember_shouldDeleteWhenExists() {
        when(memberRepository.existsById(1L)).thenReturn(true);

        memberService.deleteMember(1L);

        verify(memberRepository).deleteById(1L);
    }

    @Test
    void deleteMember_whenNotFound_shouldThrowException() {
        when(memberRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> memberService.deleteMember(1L));
        verify(memberRepository, never()).deleteById(anyLong());
    }
}
