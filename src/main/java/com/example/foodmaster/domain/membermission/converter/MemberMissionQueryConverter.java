package com.example.foodmaster.domain.membermission.converter;

import com.example.foodmaster.domain.membermission.dto.MemberMissionResDTO;
import com.example.foodmaster.domain.membermission.entity.MemberMission;
import com.example.foodmaster.domain.mission.converter.MissionConverter;
import com.example.foodmaster.domain.mission.dto.MissionResDTO;
import org.springframework.data.domain.Page;

public class MemberMissionQueryConverter {

    public static MemberMissionResDTO.MemberMissionPreviewListDTO toMemberMissionPreviewListDTO(
            Page<MemberMission> result
    ) {
        return MemberMissionResDTO.MemberMissionPreviewListDTO.builder()
                .memberMissionList(result.getContent().stream()
                        .map(MemberMissionQueryConverter::toMemberMissionPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MemberMissionResDTO.MemberMissionPreviewDTO toMemberMissionPreviewDTO(
            MemberMission memberMission
    ){
        return MemberMissionResDTO.MemberMissionPreviewDTO.builder()
                .id(memberMission.getId())
                .isCompleted(memberMission.getIsCompleted())
                .mission(MissionConverter.toMissionPreviewDTO(memberMission.getMission()))
                .build();

    }
}
