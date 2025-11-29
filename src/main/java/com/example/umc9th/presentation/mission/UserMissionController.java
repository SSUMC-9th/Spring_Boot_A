package com.example.umc9th.presentation.mission;

import com.example.umc9th.application.mission.dto.UserMissionResDTO;
import com.example.umc9th.application.mission.service.UserMissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc9th.global.paging.PageResponse;
import com.example.umc9th.global.resolver.PositivePage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions/me")
@Tag(name = "UserMission", description = "사용자 미션 API")
public class UserMissionController {

    private final UserMissionQueryService userMissionQueryService;

    @GetMapping("/progress")
    @Operation(summary = "내가 진행중인 미션 목록 조회")
    public ApiResponse<Void> getMyProgressMissions(
            @PositivePage @RequestParam(name = "page", required = false) Integer page
    ) {
        // TODO: 나중에 Spring Security 붙이면 로그인 사용자 ID로 변경
        Long userId = 1L;

        BaseSuccessCode result =
                (BaseSuccessCode) userMissionQueryService.getMyProgressMissions(userId, page);

        return ApiResponse.onSuccess(result);
    }
}
