package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

public interface MissionControllerDocs {

    // ====================================================================
    // 1. 미션 도전하기 API (POST /missions/{missionId}/challenge)
    // ====================================================================
    @Operation(
            summary = "미션 도전하기 API",
            description = "특정 미션에 도전을 시작합니다. (사용자 ID는 하드코딩 또는 인증 정보에서 가져옴)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "도전 성공 (미션 상태: IN_PROGRESS)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션 또는 사용자를 찾을 수 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "이미 도전 중인 미션입니다.")
    })
    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> challengeMission(
            @PathVariable Long missionId
    );


    // ====================================================================
    // 2. 특정 가게의 미션 목록 조회 API (GET /stores/{storeId}/missions)
    // ====================================================================
    @Operation(
            summary = "특정 가게의 미션 목록 조회 API (페이징 포함)",
            description = "특정 가게(storeId)가 제공하는 미션 목록을 10개 단위로 페이징 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "페이지 번호가 1 미만인 경우 오류"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게를 찾을 수 없음")
    })
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getMissionListByStore(
            @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1 이상이어야 함)", example = "1")
            @RequestParam(name = "page") @CheckPage int page
    );

    // ====================================================================
    // 3. 내가 진행중인 미션 목록 조회 API (GET /missions/in-progress)
    // ====================================================================
    @Operation(
            summary = "내가 진행중인 미션 목록 조회 API (페이징 포함)",
            description = "현재 로그인된 사용자(하드코딩 1L)가 **진행 중**인 미션 목록을 10개 단위로 페이징 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "페이지 번호가 1 미만인 경우 오류"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    @GetMapping("/missions/in-progress")
    public ApiResponse<MissionResponseDTO.MyMissionListDTO> getMyInProgressMissions(
            // @RequestParam(name = "page")와 @Parameter는 여기에 남깁니다.
            @Parameter(description = "페이지 번호 (1 이상이어야 함)", example = "1")
            @RequestParam(name = "page") @CheckPage int page // <--- @RequestParam 명시
    );



    // ====================================================================
// 4. 미션 진행 완료 상태로 변경 API (PATCH /missions/{memberMissionId}/complete)
// ====================================================================
    @Operation(
            summary = "미션 진행 완료 상태로 변경 API",
            description = "사용자의 특정 미션(memberMissionId)의 상태를 '진행 완료(COMPLETED)'로 변경하고 결과를 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "상태 변경 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션 기록을 찾을 수 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "이미 완료되었거나, 진행 중 상태가 아닐 때")
    })
    @PatchMapping("/missions/{memberMissionId}/complete")
    public ApiResponse<MissionResponseDTO.MissionCompleteResultDTO> completeMission(
            @Parameter(description = "UserMission ID", example = "1")
            @PathVariable Long memberMissionId
    );




}