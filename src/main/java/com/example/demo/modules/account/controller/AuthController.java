package com.example.demo.modules.account.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.example.demo.modules.account.dto.MemberResponse;
import com.example.demo.modules.account.dto.SignupRequest;
import com.example.demo.modules.account.security.UserDetailsImpl;
import com.example.demo.modules.account.service.MemberService;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.response.ApiResponse;
import com.example.demo.common.response.ErrorCode;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<Object> signup(@RequestBody SignupRequest request) {
        return memberService.signup(request);
    }

    @PostMapping("/login")
    public ApiResponse<Object> login(@RequestBody SignupRequest request) {
        return memberService.login(request);
    }

    @GetMapping("/")
    public ApiResponse<MemberResponse> mypage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
        return memberService.getMyPage(userDetails.getMember().getId());
    }
}