package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 1. PK인 id를 사용하여 Member 엔티티를 조회하는 표준 메서드
    // Optional을 사용하여 값이 없을 경우를 안전하게 처리합니다.
    Optional<Member> findById(Long id);

    // 2. 이메일을 사용하여 Member 엔티티를 조회 (로그인 등에서 활용 가능)
    Optional<Member> findByEmail(String email);

    // 3. 닉네임으로 조회하는 메서드 (화면의 닉네임 필터링에 필요할 경우)
    Optional<Member> findByNickname(String nickname);
}