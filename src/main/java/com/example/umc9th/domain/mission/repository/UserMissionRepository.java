package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.mapping.UserMission; // UserMission import 확인

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository의 제네릭 타입을 Mission에서 UserMission으로 변경합니다.
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    /**
     * 특정 사용자(Member)의 미션 목록을 주어진 상태(Status)로 필터링하여 페이징 조회
     */
    Page<UserMission> findAllByMemberAndStatus(Member member, String status, Pageable pageable);

    List<UserMission> findAllByMemberAndStatusOrderByCreatedAtDesc(Member member, String progress);
}