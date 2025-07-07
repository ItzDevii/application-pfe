package com.supemir.association.mapper;

import com.supemir.association.dto.MemberDto;
import com.supemir.association.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(source = "user.id", target = "userId")
    MemberDto toDto(Member member);

    @Mapping(source = "userId", target = "user.id")
    Member toEntity(MemberDto dto);
}
