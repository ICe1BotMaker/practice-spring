package com.example.demo.modules.account.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.modules.account.domain.Member;
import com.example.demo.modules.account.domain.UserRole;
import com.example.demo.modules.account.dto.MemberResponse;
import com.example.demo.modules.account.dto.SignupRequest;
import com.example.demo.modules.account.repository.MemberRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.response.ApiResponse;
import com.example.demo.common.response.ErrorCode;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ApiResponse<Object> signup(SignupRequest request) {
        memberRepository.findByUsername(request.username())
                .ifPresent(member -> {
                    throw new BusinessException(ErrorCode.DUPLICATE_NAME);
                });

        String encodedPassword = passwordEncoder.encode(request.password());
        
        Member member = Member.builder()
                .username(request.username())
                .password(encodedPassword)
                .role(UserRole.USER)
                .build();
                
        memberRepository.save(member);

        return ApiResponse.success(null, "회원가입에 성공하였습니다.");
    }

    public ApiResponse<Object> login(SignupRequest request) {
        Member member = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }

        return ApiResponse.success(jwtUtil.createToken(member.getId()), "로그인에 성공하였습니다.");
    }
    
    public ApiResponse<MemberResponse> getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return ApiResponse.success(MemberResponse.from(member), "조회에 성공하였습니다.");
    }
}