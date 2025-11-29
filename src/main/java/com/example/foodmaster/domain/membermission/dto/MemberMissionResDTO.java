package com.example.foodmaster.domain.membermission.dto;

import com.example.foodmaster.domain.mission.dto.MissionResDTO;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {

    @Builder
    public record MemberMissionDTO(
            Long memberMissionId
            ) {}

    @Builder
    public record MemberMissionPreviewListDTO(
            List<MemberMissionPreviewDTO> memberMissionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MemberMissionPreviewDTO(
            Long id, // memberMission의 id임
            Boolean isCompleted,

            // 기존 mission dto 재사용
            MissionResDTO.MissionPreviewDTO mission
    ) {}
}
