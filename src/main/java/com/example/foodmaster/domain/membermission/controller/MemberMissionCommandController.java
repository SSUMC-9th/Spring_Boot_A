package com.example.foodmaster.domain.membermission.controller;

import com.example.foodmaster.domain.membermission.dto.MemberMissionReqDTO;
import com.example.foodmaster.domain.membermission.dto.MemberMissionResDTO;
import com.example.foodmaster.domain.membermission.exception.code.MemberMissionSuccessCode;
import com.example.foodmaster.domain.membermission.service.command.MemberMissionCommandService;
import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberMissionCommandController {

    private final MemberMissionCommandService memberMissionCommandService;

    // 미션 도전
    @PostMapping("/missions/start")
    public ApiResponse<MemberMissionResDTO.MemberMissionDTO> missionStart(
            @RequestBody MemberMissionReqDTO.MemberMissionDTO dto
            ) {
        return ApiResponse.onSuccess(MemberMissionSuccessCode.ADD_COMPLETE, memberMissionCommandService.startMission(dto));
    }
}
