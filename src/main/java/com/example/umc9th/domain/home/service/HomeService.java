package com.example.umc9th.domain.home.service;

import com.example.umc9th.domain.home.dto.HomeResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HomeService {

    private final MemberRepository memberRepository;
    private final UserMissionRepository userMissionRepository;

    public HomeService(MemberRepository memberRepository, UserMissionRepository userMissionRepository) {
        this.memberRepository = memberRepository;
        this.userMissionRepository = userMissionRepository;
    }

    public HomeResponseDTO getHomeInfo(Long currentUserId) {
        // 1. 사용자 엔티티 조회 (Member 테이블에서 지역, 포인트 등 획득)
        Member member = memberRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 2. 진행 중인 미션 목록 조회 (Repository 메서드 사용)
        List<UserMission> ongoingMissions = userMissionRepository
                .findAllByMemberAndStatusOrderByCreatedAtDesc(member, "PROGRESS"); // 'PROGRESS'는 예시 상태 값

        // 3. DTO 변환: 미션 목록
        List<HomeResponseDTO.UserMissionDTO> missionDTOs = ongoingMissions.stream()
                .map(um -> {
                    // D-Day 계산 (deadline 필드 가정)
                    long daysLeft = ChronoUnit.DAYS.between(LocalDateTime.now(), um.getMission().getDeadline());

                    return HomeResponseDTO.UserMissionDTO.builder()
                            .missionId(um.getMission().getId())
                            // missionTitle은 StoreName + MissionCondition 등으로 조합 가능
                            .missionTitle(um.getMission().getStore().getStoreName() + " 미션")
                            .storeName(um.getMission().getStore().getStoreName())
                            .description(um.getMission().getDescription())
                            .point(um.getMission().getPoint())
                            .dDay("D-" + daysLeft)
                            .build();
                })
                .collect(Collectors.toList());

        // 4. DTO 변환: 최종 응답
        return HomeResponseDTO.builder()
                .currentRegion(member.getAddress()) // Member 엔티티의 주소 필드 사용 가정
                .currentPoint(member.getPoint())
                .completedMissionCount(7) // 실제 로직에서는 쿼리로 계산
                .totalMissionCount(10)     // 실제 로직에서는 쿼리로 계산
                .rewardPoint(1000L)        // 고정값 또는 다른 테이블에서 조회
                .myMissions(missionDTOs)
                .build();
    }


}