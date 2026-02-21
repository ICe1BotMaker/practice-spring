package com.example.demo.controller;

import com.example.demo.dto.SignupRequest;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        return memberService.signup(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody SignupRequest request) {
        return memberService.login(request);
    }
}