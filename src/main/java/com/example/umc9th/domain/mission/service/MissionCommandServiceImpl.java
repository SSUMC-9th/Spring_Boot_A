package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.UserMission;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public MissionResponseDTO.ChallengeMissionResultDTO challengeMission(Long memberId, Long missionId) {

        // 1. Member 엔티티 조회 (하드 코딩: memberId 매개변수는 무시하고 1L 사용)
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("하드 코딩된 유저(ID: 1)를 찾을 수 없습니다."));

        // 2. Mission 엔티티 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션을 찾을 수 없습니다."));

        // 3. Converter를 통해 UserMission 엔티티 생성 (상태: PROGRESS)
        UserMission newUserMission = MissionConverter.toUserMission(mission, member);

        // 4. DB 저장
        UserMission savedUserMission = userMissionRepository.save(newUserMission);

        // 5. 응답 DTO로 변환하여 반환
        return MissionConverter.toChallengeMissionResultDTO(savedUserMission);
    }
}