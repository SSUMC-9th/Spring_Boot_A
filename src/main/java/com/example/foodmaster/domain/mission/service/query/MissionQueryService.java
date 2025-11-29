package com.example.foodmaster.domain.mission.service.query;

import com.example.foodmaster.domain.mission.dto.MissionResDTO;

public interface MissionQueryService {
    MissionResDTO.MissionPreviewListDTO findMission(
            String storeName,
            Integer page
    );
}
