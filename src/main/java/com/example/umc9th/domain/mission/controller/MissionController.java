package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class MissionController implements MissionControllerDocs {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService; // 미션 조회 서비스 주입

    // 1. 미션 도전하기 API (기존)
    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        Long hardcodedMemberId = 1L; // 하드 코딩된 Member ID 사용

        MissionResponseDTO.ChallengeMissionResultDTO result =
                missionCommandService.challengeMission(hardcodedMemberId, missionId);

        return ApiResponse.onSuccess(result);
    }

    // 2. 특정 가게의 미션 목록 조회 API (신규)
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getMissionListByStore(
            @PathVariable Long storeId,
            // 커스텀 어노테이션을 사용해 page 유효성 검사 (page는 1부터 시작)
            @RequestParam(name = "page") @CheckPage int page
            // Pageable 객체 전체를 받지 않고, Service에서 PageRequest.of(page - 1, 10)를 사용합니다.
    ) {
        MissionResponseDTO.MissionPreviewListDTO response =
                missionQueryService.getMissionListByStore(storeId, page);

        MissionSuccessCode code = MissionSuccessCode.MISSION_LIST_FOUND;

        return ApiResponse.onSuccess(code, response);
    }

    // 3. 내가 진행중인 미션 목록 조회 API
    @GetMapping("/missions/in-progress")
    public ApiResponse<MissionResponseDTO.MyMissionListDTO> getMyInProgressMissions(
            // @CheckPage 커스텀 어노테이션을 사용하여 page 유효성 검사
            @RequestParam(name = "page") @CheckPage int page
    ) {
        // 하드 코딩된 Member ID (1L) 사용
        Long hardcodedMemberId = 1L;

        MissionResponseDTO.MyMissionListDTO response =
                missionQueryService.getMyInProgressMissions(hardcodedMemberId, page);

        MissionSuccessCode code = MissionSuccessCode.MISSION_LIST_FOUND;

        return ApiResponse.onSuccess(code, response);
    }


    // ====================================================================
    // 4. 미션 진행 완료로 바꾸기 API
    // 최종 경로: PATCH /missions/{memberMissionId}/complete
    // ====================================================================
    @PatchMapping("/missions/{memberMissionId}/complete")
    public ApiResponse<MissionResponseDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable Long memberMissionId
    ) {

        Long hardcodedMemberId = 1L; // 하드 코딩된 Member ID 사용

        // Service 호출 및 상태 변경
        MissionResponseDTO.MissionCompleteResultDTO result =
                missionCommandService.completeMission(hardcodedMemberId, memberMissionId);

        // 성공 코드 사용 (HTTP 200 OK)
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_UPDATED, result);
    }
}