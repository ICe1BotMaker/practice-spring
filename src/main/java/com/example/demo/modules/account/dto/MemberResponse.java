package com.example.demo.modules.account.dto;

import java.time.LocalDateTime;

import com.example.demo.modules.account.domain.Member;
import com.example.demo.modules.account.domain.UserRole;

public record MemberResponse(Long id, String username, UserRole role, LocalDateTime createdAt) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getUsername(), member.getRole(), member.getCreatedAt());
    }
}