package com.example.umc9th.application.mission.dto;

import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record StoreMission(
            Long missionId,
            Integer rewardPoint,
            String title,
            String description
    ) {}
}

