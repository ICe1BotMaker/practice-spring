package com.example.demo.modules.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.demo.modules.account.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}