package com.example.foodmaster.domain.membermission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberMissionResDTO {

    @Builder
    public record MemberMissionDTO(
            Long memberMissionId
            ) {}
}
