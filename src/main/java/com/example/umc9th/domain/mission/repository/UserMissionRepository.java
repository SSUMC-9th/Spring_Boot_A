package com.example.umc9th.domain.mission.repository;


import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.member.entity.Member;           // Member 엔티티 가정
import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<Mission, Long> {

    /**
     * 특정 사용자(Member)의 미션 목록을 주어진 상태(Status)로 필터링하여 페이징 조회
     *
     * @param member   사용자 엔티티
     * @param status   미션 상태 (예: PROGRESS, COMPLETE, REVIEWED)
     * @param pageable 페이징 정보 (페이지 번호, 크기, 정렬)
     * @return UserMission 엔티티의 페이징된 목록
     */
    Page<UserMission> findAllByMemberAndStatus(Member member, String status, Pageable pageable);

    List<UserMission> findAllByMemberAndStatusOrderByCreatedAtDesc(Member member, String progress);
}
