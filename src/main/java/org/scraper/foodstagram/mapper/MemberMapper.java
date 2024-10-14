package org.scraper.foodstagram.mapper;

import org.mapstruct.Mapper;
import org.scraper.foodstagram.repository.entity.Member;
import org.scraper.foodstagram.service.dto.MemberDto;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDto toDto(Member member);

    Member toEntity(MemberDto memberDto);
}
