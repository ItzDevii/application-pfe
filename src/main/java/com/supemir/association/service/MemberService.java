package com.supemir.association.service;

import com.supemir.association.dto.MemberDto;

import java.util.List;

public interface MemberService {
    MemberDto create(MemberDto dto);
    MemberDto update(Long id, MemberDto dto);
    void delete(Long id);
    MemberDto getById(Long id);
    List<MemberDto> getAll();
}
