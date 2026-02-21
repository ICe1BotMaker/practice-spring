package com.example.demo.service;

import com.example.demo.dto.SignupRequest;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String signup(SignupRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        
        Member member = Member.builder()
                .username(request.username())
                .password(encodedPassword)
                .role("ROLE_USER")
                .build();
                
        memberRepository.save(member);
        return "회원가입 성공!";
    }

    public String login(SignupRequest request) {
        Member member = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.createToken(member.getUsername());
    }
}