package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionListResponseDTO {

    private Long missionId;
    private Long point;
    private String storeName;
    private String description;
    private String userMissionStatus; // 진행 중, 진행 완료 등 상태
}