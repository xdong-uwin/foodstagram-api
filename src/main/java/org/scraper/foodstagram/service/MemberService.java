package org.scraper.foodstagram.service;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.mapper.MemberMapper;
import org.scraper.foodstagram.repository.MemberRepository;
import org.scraper.foodstagram.repository.entity.Member;
import org.scraper.foodstagram.service.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    public MemberDto register(MemberDto memberDto) {
        Member savedMember = memberRepository.save(memberMapper.toEntity(memberDto));
        return memberMapper.toDto(savedMember);
    }

}
