package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.dto.MissionListResponseDTO;
import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MissionService {

    private final UserMissionRepository userMissionRepository;
    private final MemberRepository memberRepository;

    public MissionService(UserMissionRepository userMissionRepository, MemberRepository memberRepository) {
        this.userMissionRepository = userMissionRepository;
        this.memberRepository = memberRepository;
    }

    // 미션 목록 조회 (페이징 포함) 로직
    public Page<MissionListResponseDTO> getMissionsByStatus(Long memberId, String status, int page) {

        // 1. 페이지 요청 객체 생성 (페이지 번호 0부터 시작, 페이지 당 10개)
        Pageable pageable = PageRequest.of(page, 10);

        // 2. Member 엔티티 조회 (쿼리 조건에 사용)
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 3. Repository 메서드 호출 (findAllByMemberAndStatus 가정)
        Page<UserMission> userMissions = userMissionRepository.findAllByMemberAndStatus(member, status, pageable);

        // 4. 조회된 UserMission 엔티티 Page를 DTO Page로 변환
        return userMissions.map(um -> MissionListResponseDTO.builder()
                .missionId(um.getMission().getId())
                .point(um.getMission().getPoint())
                .storeName(um.getMission().getStore().getStoreName()) // 조인된 Store 엔티티에서 이름 획득
                .description(um.getMission().getDescription())
                .userMissionStatus(um.getStatus().name())
                .build());
    }
}