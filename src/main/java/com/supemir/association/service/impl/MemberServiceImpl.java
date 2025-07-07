package com.supemir.association.service.impl;

import com.supemir.association.dto.MemberDto;
import com.supemir.association.entity.Member;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.MemberMapper;
import com.supemir.association.repository.MemberRepository;
import com.supemir.association.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public List<MemberDto> getAll() {
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto getById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + id));
        return memberMapper.toDto(member);
    }

    @Override
    public MemberDto create(MemberDto dto) {
        Member member = memberMapper.toEntity(dto);
        Member saved = memberRepository.save(member);
        return memberMapper.toDto(saved);
    }

    @Override
    public MemberDto update(Long id, MemberDto dto) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + id));

        Member updated = memberMapper.toEntity(dto);
        updated.setId(existing.getId());

        return memberMapper.toDto(memberRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member not found with id " + id);
        }
        memberRepository.deleteById(id);
    }
}
