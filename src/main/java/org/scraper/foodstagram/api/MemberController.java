package org.scraper.foodstagram.api;

import lombok.RequiredArgsConstructor;
import org.scraper.foodstagram.dto.LoginDto;
import org.scraper.foodstagram.dto.LoginResponse;
import org.scraper.foodstagram.dto.MemberDto;
import org.scraper.foodstagram.dto.RecipeDto;
import org.scraper.foodstagram.service.MemberService;
import org.scraper.foodstagram.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final RecipeService recipeService;

    @PostMapping
    public MemberDto register(@RequestBody MemberDto memberDto) {
        return memberService.register(memberDto);
    }

    @GetMapping("/email/verify")
    public ResponseEntity<Boolean> verifyEmail(@RequestParam String token) {
        memberService.verifyEmail(token);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        Long memberId = memberService.login(loginDto.getEmail(), loginDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.builder().memberId(memberId).build());
    }

    @GetMapping("/{memberId}/favourites")
    public ResponseEntity<List<RecipeDto>> getFavouriteRecipes(@PathVariable String memberId) {
        return ResponseEntity.status(HttpStatus.OK).body(recipeService.getFavouriteRecipes(memberId));
    }
}
