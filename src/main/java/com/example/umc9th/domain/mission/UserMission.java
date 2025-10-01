package com.example.umc9th.domain.mission;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.enums.UserMissionStatus;
import com.example.umc9th.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_missions",
        uniqueConstraints = @UniqueConstraint(name = "uq_um_user_mission", columnNames = {"user_id","mission_id"}),
        indexes = {
                @Index(name = "idx_um_user_status", columnList = "user_id,status"),
                @Index(name = "idx_um_mission", columnList = "mission_id"),
                @Index(name = "idx_um_user_status_assigned", columnList = "user_id,status,assigned_at")
        })
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class UserMission extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_um_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_um_mission"))
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('REQUESTED','IN_PROGRESS','COMPLETED','REVIEWED') default 'REQUESTED'")
    private UserMissionStatus status = UserMissionStatus.REQUESTED;

    @Column(nullable = false)
    private LocalDateTime assignedAt;

    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
}

