package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.UserMission;

public class MissionConverter {

    // Mission 및 Member 엔티티를 UserMission 엔티티로 변환
    public static UserMission toUserMission(Mission mission, Member member) {
        return UserMission.builder()
                .mission(mission)
                .member(member)
                .status("PROGRESS") // 미션 도전 시 '도전 중' 상태로 고정
                // createdAt은 BaseEntity 상속을 통해 자동 생성된다고 가정
                .build();
    }

    // UserMission 엔티티를 응답 DTO로 변환
    public static MissionResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(
            UserMission userMission
    ) {
        return MissionResponseDTO.ChallengeMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .challengedAt(userMission.getCreatedAt())
                .build();
    }
}