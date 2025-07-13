package com.supemir.association.mapper;

import com.supemir.association.dto.ParticipationDto;
import com.supemir.association.entity.Activity;
import com.supemir.association.entity.Member;
import com.supemir.association.entity.Participation;
import org.springframework.stereotype.Component;

@Component
public class ParticipationMapper {
    public Participation toEntity(ParticipationDto dto) {
        if (dto == null) return null;
        Participation p = new Participation();
        p.setId(dto.getId());
        Member m = new Member(); m.setId(dto.getMemberId()); p.setMember(m);
        Activity a = new Activity(); a.setId(dto.getActivityId()); p.setActivity(a);
        p.setSignupDate(dto.getSignupDate());
        return p;
    }

    public ParticipationDto toDto(Participation participation) {
        if (participation == null) return null;
        ParticipationDto dto = new ParticipationDto();
        dto.setId(participation.getId());
        dto.setMemberId(participation.getMember().getId());
        dto.setActivityId(participation.getActivity().getId());
        dto.setSignupDate(participation.getSignupDate());
        return dto;
    }
}
