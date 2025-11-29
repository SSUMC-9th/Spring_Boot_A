package com.example.foodmaster.domain.member.dto.join;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ) {}
}
