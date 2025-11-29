package com.example.umc9th.application.mission.dto;

import com.example.umc9th.domain.enums.UserMissionStatus;
import lombok.Builder;

import java.time.LocalDateTime;

public class UserMissionResDTO {

    @Builder
    public record Challenge(
            Long userMissionId,
            Long userId,
            Long missionId,
            UserMissionStatus status,
            LocalDateTime assignedAt
    ) {}
}