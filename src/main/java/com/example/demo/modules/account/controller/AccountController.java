package com.example.demo.modules.account.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.example.demo.modules.account.dto.MemberResponse;
import com.example.demo.modules.account.dto.SignupRequest;
import com.example.demo.modules.account.security.UserDetailsImpl;
import com.example.demo.modules.account.service.AccountService;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.response.ApiResponse;
import com.example.demo.common.response.ErrorCode;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/signup")
    public ApiResponse<Object> signup(@RequestBody SignupRequest request) {
        return accountService.signup(request);
    }

    @PostMapping("/login")
    public ApiResponse<Object> login(@RequestBody SignupRequest request) {
        return accountService.login(request);
    }

    @GetMapping("/")
    public ApiResponse<MemberResponse> mypage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
        return accountService.getMyPage(userDetails.getMember().getId());
    }
}