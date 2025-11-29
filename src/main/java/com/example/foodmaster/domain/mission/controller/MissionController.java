package com.example.foodmaster.domain.mission.controller;

import com.example.foodmaster.domain.mission.dto.MissionResDTO;
import com.example.foodmaster.domain.mission.exception.code.MissionSuccessCode;
import com.example.foodmaster.domain.mission.service.query.MissionQueryService;
import com.example.foodmaster.global.annotation.ValidPage;
import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs{

    private final MissionQueryService missionQueryService;

    @GetMapping("mission/{storeName}")
    @Override
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMissionsByStore(
            @PathVariable("storeName") String storeName,
            @ValidPage
            @RequestParam Integer page
    ) {
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code,missionQueryService.findMission(storeName, page));
    }
}
