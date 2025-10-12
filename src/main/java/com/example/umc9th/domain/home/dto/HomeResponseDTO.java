package com.example.umc9th.domain.home.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// 홈 화면 전체 응답 DTO
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeResponseDTO {

    // 상단 (미션 달성 정보)
    private String currentRegion; // 안암동 (지역 정보)
    private Long currentPoint;    // 999,999 (보유 포인트)
    private int completedMissionCount; // 7/10
    private int totalMissionCount;
    private Long rewardPoint; // 1,000 P (달성 시 보상 포인트)

    // MY MISSION 목록
    private List<UserMissionDTO> myMissions;

    // MY MISSION 개별 항목 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionDTO {
        private Long missionId;
        private String missionTitle; // '반이학생마라탕' (가게명과 미션 이름 조합 가능)
        private String storeName;    // '중식당' (가게명)
        private String description;  // 10,000원 이상의 식사 시 (미션 조건)
        private Long point;          // 500 P 적립 (포인트)
        private String dDay;         // D-7 (마감 기한)
    }
}