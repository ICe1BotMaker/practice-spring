package com.example.demo.modules.account.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.response.ApiResponse;
import com.example.demo.common.response.ErrorCode;
import com.example.demo.modules.account.dto.MemberResponse;
import com.example.demo.modules.account.security.UserDetailsImpl;
import com.example.demo.modules.account.service.MemberService;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public ApiResponse<MemberResponse> mypage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
        return memberService.getMyPage(userDetails.getMember().getId());
    }
}
