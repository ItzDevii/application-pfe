package com.supemir.association.mapper;

import com.supemir.association.dto.MemberDto;
import com.supemir.association.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "user.id", source = "userId")
    Member toEntity(MemberDto dto);

    @Mapping(target = "userId", source = "user.id")
    MemberDto toDto(Member member);
}
