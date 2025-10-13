package com.example.umc9th.application.mission.dto;
import com.example.umc9th.domain.enums.UserMissionStatus;
public record MyMissionRowDto(
        Long userMissionId, Long missionId, String missionTitle, String storeName,
        UserMissionStatus status, Integer dDay
) {}
