package com.example.foodmaster.domain.membermission.repository;

import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.membermission.entity.MemberMission;
import com.example.foodmaster.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 완료한 미션 모음
    @Query("select mm.mission from MemberMission mm where mm.member = :member and mm.isCompleted=true")
    Page<Mission> findCompletedMissionsByMember(@Param("member") Member member, Pageable pageable);

    // 진행중인 미션 모음
    @Query("select mm.mission from MemberMission mm where mm.member = :member and mm.isCompleted=false ")
    Page<Mission> findNotCompletedMissionsByMember(@Param("member") Member member, Pageable pageable);

    Boolean existsByMemberAndMission(Member member, Mission mission);
}
