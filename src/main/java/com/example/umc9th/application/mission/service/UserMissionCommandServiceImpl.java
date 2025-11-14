package com.example.umc9th.application.mission.service;

import com.example.umc9th.application.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.mission.Mission;
import com.example.umc9th.domain.mission.UserMission;
import com.example.umc9th.domain.mission.converter.UserMissionConverter;
import com.example.umc9th.domain.user.User;
import com.example.umc9th.infrastructure.mission.MissionJpaRepository;
import com.example.umc9th.infrastructure.mission.UserMissionJpaRepository;
import com.example.umc9th.infrastructure.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserMissionCommandServiceImpl implements UserMissionCommandService {

    private final UserJpaRepository userRepository;
    private final MissionJpaRepository missionRepository;
    private final UserMissionJpaRepository userMissionRepository;

    private static final Long MOCK_USER_ID = 1L; // 하드코딩 유저

    @Override
    public UserMissionResDTO.Challenge challengeMission(Long missionId) {

        User user = userRepository.findById(MOCK_USER_ID)
                .orElseThrow(() -> new IllegalStateException("MOCK_USER_ID 유저가 없습니다."));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("미션을 찾을 수 없습니다. id=" + missionId));

        // 이미 도전 중인지(Unique 제약 위반 방지)
        if (userMissionRepository.existsByUserAndMission(user, mission)) {
            throw new IllegalStateException("이미 도전 중인 미션입니다.");
        }

        // TODO: mission.isActive, 기간(startsAt/endsAt) 체크 로직 필요하면 여기서 추가

        UserMission userMission = UserMissionConverter.toUserMission(user, mission);
        userMissionRepository.save(userMission);

        return UserMissionConverter.toChallengeDTO(userMission);
    }
}

