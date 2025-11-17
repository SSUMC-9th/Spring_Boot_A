package com.example.foodmaster.domain.membermission.converter;

import com.example.foodmaster.domain.member.dto.MemberResDTO;
import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.membermission.dto.MemberMissionReqDTO;
import com.example.foodmaster.domain.membermission.dto.MemberMissionResDTO;
import com.example.foodmaster.domain.membermission.entity.MemberMission;
import com.example.foodmaster.domain.mission.entity.Mission;
import lombok.Builder;

@Builder
public class MemberMissionConverter {

    // Entity -> DTO
    public static MemberMissionResDTO.MemberMissionDTO addDTO(
            MemberMission memberMission
    ) {
        return MemberMissionResDTO.MemberMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .build();
    }


    // DTO -> Entity
    public static MemberMission toMemberMission (
            Member member,
            Mission mission
    ) {
        return MemberMission.builder()
                .isCompleted(false)
                .member(member)
                .mission(mission)
                .build();
    }
}
