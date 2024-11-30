package org.scraper.foodstagram.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.mapper.MemberMapper;
import org.scraper.foodstagram.repository.MemberRepository;
import org.scraper.foodstagram.dto.MemberDto;
import org.scraper.foodstagram.util.JwtTokenUtil;
import org.scraper.foodstagram.util.MailUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    public MemberDto register(MemberDto memberDto) {
        memberDto.setIsActive(false);
        var savedMember = memberRepository.save(memberMapper.toEntity(memberDto));
        String emailActivationToken = JwtTokenUtil.generateEmailActivationToken(memberDto.getEmail());
        var verificationLink = "http://localhost:8080/v1/members/email/verify?token=" + emailActivationToken;
        try {
            MailUtil.sendVerificationLink(memberDto.getEmail(), verificationLink);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return memberMapper.toDto(savedMember);
    }

    public void verifyEmail(String token) {
        var email = JwtTokenUtil.extractEmailFieldFromEmailActivationToken(token);
        var expireTime = JwtTokenUtil.extractExpirationFieldFromEmailActivationToken(token);

        memberRepository.findByEmail(email).ifPresentOrElse(member -> {
            if (member.getIsActive()) {
                throw new RuntimeException("Email already verified: " + email);
            }
            if (expireTime.before(new Date())) {
                throw new RuntimeException("Verification link expired: " + email);
            }
            member.setIsActive(true);
            memberRepository.save(member);
        }, () -> {
            throw new RuntimeException("Member not found with email: " + email);
        });
    }

    public Long login(String email, String password) {
        memberRepository.findByEmail(email).ifPresentOrElse(member -> {
            if (!member.getIsActive()) {
                throw new RuntimeException("Email not verified: " + email);
            }
            if (!member.getPassword().equals(password)) {
                throw new RuntimeException("Invalid password: " + email);
            }
        }, () -> {
            throw new RuntimeException("Member not found with email: " + email);
        });
        return memberRepository.findByEmail(email).orElseThrow().getId();
    }

}
