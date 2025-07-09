package com.supemir.association.mapper;

import com.supemir.association.dto.MemberDto;
import com.supemir.association.entity.Member;
import com.supemir.association.entity.User;
import com.supemir.association.enums.MemberStatus;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member toEntity(MemberDto dto) {
        if (dto == null) return null;
        Member member = new Member();
        member.setId(dto.getId());
        member.setFirstName(dto.getFirstName());
        member.setLastName(dto.getLastName());
        member.setJoinDate(dto.getJoinDate());
        member.setStatus(MemberStatus.valueOf(dto.getStatus()));
        User user = new User();
        user.setId(dto.getUserId());
        member.setUser(user);
        return member;
    }

    public MemberDto toDto(Member member) {
        if (member == null) return null;
        MemberDto dto = new MemberDto();
        dto.setId(member.getId());
        dto.setFirstName(member.getFirstName());
        dto.setLastName(member.getLastName());
        dto.setJoinDate(member.getJoinDate());
        dto.setStatus(member.getStatus().name());
        dto.setUserId(member.getUser().getId());
        return dto;
    }
}
