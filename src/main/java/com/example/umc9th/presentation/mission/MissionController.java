package com.example.umc9th.presentation.mission;

import com.example.umc9th.application.mission.dto.MissionResDTO;
import com.example.umc9th.application.mission.service.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc9th.global.paging.PageResponse;
import com.example.umc9th.global.resolver.PositivePage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionController {

    private final MissionQueryService missionQueryService;

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회")
    public ApiResponse<Void> getStoreMissions(
            @PathVariable Long storeId,
            @PositivePage @RequestParam(name = "page", required = false) Integer page
    ) {
        BaseSuccessCode response =
                (BaseSuccessCode) missionQueryService.getStoreMissions(storeId, page);
        return ApiResponse.onSuccess(response);
    }
}

