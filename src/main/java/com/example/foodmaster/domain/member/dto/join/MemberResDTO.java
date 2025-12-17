package com.example.foodmaster.domain.member.dto.join;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ) {}

    // 로그인 글로벌 auth에도 가능
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}
