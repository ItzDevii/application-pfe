package com.supemir.association.service.impl;

import com.supemir.association.dto.MemberDto;
import com.supemir.association.entity.Member;
import com.supemir.association.exception.ResourceNotFoundException;
import com.supemir.association.mapper.MemberMapper;
import com.supemir.association.repository.MemberRepository;
import com.supemir.association.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public MemberDto createMember(MemberDto dto) {
        Member member = memberMapper.toEntity(dto);
        Member saved = memberRepository.save(member);
        return memberMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + id));
        return memberMapper.toDto(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MemberDto updateMember(Long id, MemberDto dto) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + id));
        Member updatedEntity = memberMapper.toEntity(dto);
        updatedEntity.setId(existing.getId());
        Member updated = memberRepository.save(updatedEntity);
        return memberMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member not found with id " + id);
        }
        memberRepository.deleteById(id);
    }
}
