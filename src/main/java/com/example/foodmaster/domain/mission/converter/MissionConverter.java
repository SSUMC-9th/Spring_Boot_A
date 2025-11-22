package com.example.foodmaster.domain.mission.converter;

import com.example.foodmaster.domain.mission.dto.MissionResDTO;
import com.example.foodmaster.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public class MissionConverter {

    // Entity -> DTO
    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(
            Page<Mission> result
    ) {
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElement(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(
            Mission mission
    ) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .storeName(mission.getStore().getName())
                .goal(mission.getGoal())
                .reward(mission.getAward())
                .deadLine(mission.getDeadline())
                .build();
    }
}
