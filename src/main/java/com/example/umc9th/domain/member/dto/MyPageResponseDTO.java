package com.example.umc9th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponseDTO {

    // 닉네임
    private String nickname;

    // 이메일
    private String email;

    // 전화번호
    private String phoneNumber;

    // 보유 포인트
    private Long point;

    // 추가: 휴대폰 인증 상태 (화면에 '미인증/인증하기' 버튼이 있기 때문에 필요)
    private boolean isPhoneVerified;
}