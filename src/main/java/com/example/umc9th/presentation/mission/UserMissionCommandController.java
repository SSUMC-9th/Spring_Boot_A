package com.example.umc9th.presentation.mission;

import com.example.umc9th.application.mission.dto.UserMissionResDTO;
import com.example.umc9th.application.mission.service.UserMissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class UserMissionCommandController {

    private final UserMissionCommandService userMissionCommandService;

    // 미션 도전하기
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<UserMissionResDTO.Challenge> challengeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,   // 혹은 전용 SuccessCode
                userMissionCommandService.challengeMission(missionId)
        );
    }
}
