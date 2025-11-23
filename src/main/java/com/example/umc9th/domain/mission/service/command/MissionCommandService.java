// com.example.umc9th.domain.mission.service.command.MissionCommandService.java
package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.MissionResponseDTO;

public interface MissionCommandService {
    // Member ID와 Mission ID를 인자로 받습니다.
    MissionResponseDTO.ChallengeMissionResultDTO challengeMission(Long memberId, Long missionId);
}