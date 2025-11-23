package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.application.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.Mission;

public class MissionConverter {

    public static MissionResDTO.StoreMission toStoreMission(Mission mission) {
        return MissionResDTO.StoreMission.builder()
                .missionId(mission.getId())
                // Mission 엔티티에 아직 rewardPoint가 없으므로 임시로 null
                // TODO: rewardPoint 필드 생기면 mission.getRewardPoint()로 변경
                .rewardPoint(null)
                .title(mission.getTitle())
                .description(mission.getDescription())
                .build();
    }
}
