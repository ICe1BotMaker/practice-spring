package com.example.demo.modules.account.service;

import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.response.ApiResponse;
import com.example.demo.common.response.ErrorCode;
import com.example.demo.modules.account.domain.Member;
import com.example.demo.modules.account.dto.MemberResponse;
import com.example.demo.modules.account.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    
    public ApiResponse<MemberResponse> getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return ApiResponse.success(MemberResponse.from(member), "조회에 성공하였습니다.");
    }
}
