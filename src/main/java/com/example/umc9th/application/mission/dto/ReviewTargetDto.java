package com.example.umc9th.application.mission.dto;
import java.time.LocalDateTime;
public record ReviewTargetDto(
        Long userMissionId, Long missionId, String storeName, String missionTitle,
        LocalDateTime completedAt
) {}
