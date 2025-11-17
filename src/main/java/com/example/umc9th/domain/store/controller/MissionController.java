package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.service.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    // 미션 도전하기 API
    // {missionId}를 경로 변수로 받습니다.
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        // 하드 코딩된 Member ID (1L)와 Mission ID를 Service에 전달
        Long hardcodedMemberId = 1L;

        MissionResponseDTO.ChallengeMissionResultDTO result =
                missionCommandService.challengeMission(hardcodedMemberId, missionId);

        return ApiResponse.onSuccess(result);
    }
}