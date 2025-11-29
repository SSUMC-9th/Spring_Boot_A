package com.example.foodmaster.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionPreviewListDTO(
            List<MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElement,
            Boolean isFirst,
            Boolean isLast
    )
    {}

    @Builder
    public record MissionPreviewDTO(
            String storeName,
            String goal,
            Double reward,
            LocalDate deadLine
    ) {}
}
