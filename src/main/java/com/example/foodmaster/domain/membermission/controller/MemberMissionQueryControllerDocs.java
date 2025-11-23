package com.example.foodmaster.domain.membermission.controller;

import com.example.foodmaster.domain.membermission.dto.MemberMissionResDTO;
import com.example.foodmaster.global.annotation.ValidPage;
import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public interface MemberMissionQueryControllerDocs {

    @Operation(
            summary = "멤버의 진행 중 미션 목록 조회 API (개발 중)",
            description = "특정 멤버의 진행 중인 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MemberMissionResDTO.MemberMissionPreviewListDTO> getMemberMission(
            @ValidPage
            @RequestParam(defaultValue = "1") Integer page,
            @PathVariable("memberId") Long memberId
    );
}
