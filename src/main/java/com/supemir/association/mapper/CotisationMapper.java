package com.supemir.association.mapper;

import com.supemir.association.dto.CotisationDto;
import com.supemir.association.entity.Cotisation;
import com.supemir.association.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class CotisationMapper {
    public Cotisation toEntity(CotisationDto dto) {
        if (dto == null) return null;
        Cotisation cot = new Cotisation();
        cot.setId(dto.getId());
        cot.setAmount(dto.getAmount());
        cot.setPaymentDate(dto.getPaymentDate());
        Member member = new Member(); member.setId(dto.getMemberId());
        cot.setMember(member);
        return cot;
    }

    public CotisationDto toDto(Cotisation cot) {
        if (cot == null) return null;
        CotisationDto dto = new CotisationDto();
        dto.setId(cot.getId());
        dto.setAmount(cot.getAmount());
        dto.setPaymentDate(cot.getPaymentDate());
        dto.setMemberId(cot.getMember().getId());
        return dto;
    }
}
