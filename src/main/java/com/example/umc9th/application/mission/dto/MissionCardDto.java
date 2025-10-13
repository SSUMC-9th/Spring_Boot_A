package com.example.umc9th.application.mission.dto;
import com.example.umc9th.domain.enums.UserMissionStatus;
public record MissionCardDto(
        Long userMissionId, Long missionId, String storeName, String missionTitle,
        UserMissionStatus status, String statusBadge, Integer dDay, boolean showReviewCta
) {}
