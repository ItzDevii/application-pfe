package com.supemir.association.service;

import com.supemir.association.dto.MemberDto;

import java.util.List;

public interface MemberService {
    List<MemberDto> getAll();

    MemberDto getById(Long id);

    MemberDto create(MemberDto memberDto);

    MemberDto update(Long id, MemberDto memberDto);

    void delete(Long id);
}
