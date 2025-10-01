package com.example.umc9th.domain.mission;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.enums.MissionLevel;
import com.example.umc9th.domain.review.Review;
import com.example.umc9th.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missions",
        indexes = {
                @Index(name = "idx_missions_store", columnList = "store_id"),
                @Index(name = "idx_missions_active", columnList = "is_active"),
                @Index(name = "idx_missions_store_active", columnList = "store_id,is_active")
        })
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Mission extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_missions_store"))
    private Store store;

    @Column(length = 150, nullable = false)
    private String title;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "`level`", nullable = false, columnDefinition = "ENUM('EASY','NORMAL','HARD') default 'NORMAL'")
    private MissionLevel level = MissionLevel.NORMAL;

    private LocalDateTime startsAt;
    private LocalDateTime endsAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissions = new ArrayList<>();

    @OneToMany(mappedBy = "mission")
    private List<Review> reviews = new ArrayList<>();
}

