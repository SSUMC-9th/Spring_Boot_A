package com.example.foodmaster.domain.mission.controller;

import com.example.foodmaster.domain.mission.dto.MissionResDTO;
import com.example.foodmaster.global.annotation.ValidPage;
import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public interface MissionControllerDocs {

    @Operation(
            summary = "가게의 미션 목록 조회 API (개발 중)",
            description = "특정 가게의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("mission/{storeName}")
    ApiResponse<MissionResDTO.MissionPreviewListDTO> getMissionsByStore(
            @PathVariable("storeName") String storeName,
            @ValidPage
            @RequestParam Integer page
    );
}
