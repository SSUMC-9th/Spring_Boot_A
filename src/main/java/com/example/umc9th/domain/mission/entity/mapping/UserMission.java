package com.example.umc9th.domain.mission.entity.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Builder
@Getter
@Table(name = "member_mission")
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1. Member 엔티티 참조 (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 2. Mission 엔티티 참조 (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    // 3. 미션 수행 상태를 저장하는 필드 (예: "PROGRESS", "COMPLETE")
    @Column(name = "status")
    private String status;

    // ... (기타 필요한 필드)
}
