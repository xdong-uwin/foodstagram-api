package org.scraper.foodstagram.api;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.service.MemberService;
import org.scraper.foodstagram.dto.LoginDto;
import org.scraper.foodstagram.dto.MemberDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/email/verify")
    public void verifyEmail(@RequestParam String token) {
        memberService.verifyEmail(token);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto) {
        memberService.login(loginDto.getEmail(), loginDto.getPassword());
    }
}
