package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeMissionResultDTO {
        Long userMissionId;
        LocalDateTime challengedAt; // 미션 도전 시작 시간 (createdAt)
    }

    // 특정 가게의 미션 목록 조회 API 용
    // 1. 단일 미션 상세 정보 (Preview)
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewDTO {
        private Long missionId;
        private Long point;
        private String storeName;       // 가게명 (Store 엔티티에서 추출)
        private String description;     // 미션 내용
        private LocalDateTime deadline; // 미션 기한
    }

    // 2. 미션 목록 및 페이징 정보 (응답 DTO)
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewListDTO {
        private List<MissionPreviewDTO> missionList;
        private Integer listSize;       // 현재 페이지의 데이터 개수
        private Integer totalPage;      // 전체 페이지 수
        private Long totalElements;     // 전체 데이터 개수
        private Integer currentPage;    // 현재 페이지 번호 (0부터 시작)
        private Boolean isFirst;        // 첫 페이지 여부
        private Boolean isLast;         // 마지막 페이지 여부
    }


    // 내가 진행중인 미션 목록 조회 API 용
    // 3. 내가 진행중인 미션 상세 정보 (MyMissionPreviewDTO)
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionPreviewDTO {
        private Long memberMissionId; // MemberMission 테이블의 ID
        private Long missionId;       // Mission 테이블의 ID
        private String storeName;     // 가게명
        private String description;   // 미션 내용
        private LocalDateTime deadline; // 미션 기한
        private LocalDateTime challengedAt; // 도전 시작일
        private MissionStatus status;        // 미션 상태 (예: IN_PROGRESS)
    }

    // 4. 내가 진행중인 미션 목록 및 페이징 정보 (응답 DTO)
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionListDTO {
        private List<MyMissionPreviewDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Integer currentPage;   // 1부터 시작
        private Boolean isFirst;
        private Boolean isLast;
    }


    // 진행중인 미션 진행 완료로 바꾸기 API 용
    // 5. 미션 완료 결과 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionCompleteResultDTO {
        private Long userMissionId;
        private String storeName;
        private String description;
        private MissionStatus updatedStatus; // COMPLETED
        private LocalDateTime completedAt; // 완료 시점 (updatedAt 사용 가정)
        private Long earnedPoint; // 적립된 포인트
    }
}