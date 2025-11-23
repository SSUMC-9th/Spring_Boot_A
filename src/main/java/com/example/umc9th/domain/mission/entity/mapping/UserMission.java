package com.example.umc9th.domain.mission.entity.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Builder
@Getter
@Table(name = "member_mission")
public class UserMission extends BaseEntity {

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
    @Enumerated(EnumType.STRING) // 이 어노테이션이 있어야 DB에 String으로 저장됩니다.
    private MissionStatus status;

    // ... (기타 필요한 필드)
}
