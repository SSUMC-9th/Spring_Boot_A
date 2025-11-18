package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.application.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.enums.UserMissionStatus;
import com.example.umc9th.domain.mission.Mission;
import com.example.umc9th.domain.mission.UserMission;
import com.example.umc9th.domain.user.User;

import java.time.LocalDateTime;

public class UserMissionConverter {

    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(UserMissionStatus.REQUESTED) // 기본 상태
                .assignedAt(LocalDateTime.now())
                .build();
    }

    public static UserMissionResDTO.Challenge toChallengeDTO(UserMission um) {
        return UserMissionResDTO.Challenge.builder()
                .userMissionId(um.getId())
                .userId(um.getUser().getId())
                .missionId(um.getMission().getId())
                .status(um.getStatus())
                .assignedAt(um.getAssignedAt())
                .build();
    }
}
