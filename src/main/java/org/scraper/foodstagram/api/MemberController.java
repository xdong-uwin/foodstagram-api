package org.scraper.foodstagram.api;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.service.MemberService;
import org.scraper.foodstagram.service.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public MemberDto register(MemberDto memberDto) {
        return memberService.register(memberDto);
    }
}
