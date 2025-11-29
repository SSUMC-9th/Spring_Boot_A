package com.example.foodmaster.domain.membermission.controller;

import com.example.foodmaster.domain.membermission.dto.MemberMissionResDTO;
import com.example.foodmaster.domain.membermission.exception.code.MemberMissionSuccessCode;
import com.example.foodmaster.domain.membermission.service.query.MemberMissionQueryService;
import com.example.foodmaster.global.annotation.ValidPage;
import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
public class MemberMissionQueryController implements MemberMissionQueryControllerDocs {

    private final MemberMissionQueryService memberMissionQueryService;

    @GetMapping("/memberMission/{memberId}")
    @Override
    public ApiResponse<MemberMissionResDTO.MemberMissionPreviewListDTO> getMemberMission(
            @ValidPage
            @RequestParam(defaultValue = "1") Integer page,
            @PathVariable("memberId") Long memberId
    ) {
        MemberMissionSuccessCode code = MemberMissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, memberMissionQueryService.searchMissions(memberId, page));
    }
}
