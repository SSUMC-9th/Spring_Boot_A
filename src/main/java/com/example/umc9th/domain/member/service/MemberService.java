package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.MyPageResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 마이 페이지 정보 조회 로직
    public MyPageResponseDTO getMyPageInfo(Long currentUserId) {
        // 1. Member 엔티티 조회 (MemberRepository.findById() 사용)
        Member member = memberRepository.findById(currentUserId)
                // 만약 사용자가 존재하지 않으면 예외 발생
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. 엔티티를 DTO로 변환
        return MyPageResponseDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .isPhoneVerified(member.getStatus().equals("ACTIVE")) // status 필드를 활용한 인증 상태 추정
                .build();
    }
}