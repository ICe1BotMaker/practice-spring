package com.example.demo.modules.account.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.example.demo.modules.account.dto.SignupRequest;
import com.example.demo.modules.account.service.AccountService;
import com.example.demo.common.response.ApiResponse;

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
}