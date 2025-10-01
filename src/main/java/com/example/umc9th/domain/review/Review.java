package com.example.umc9th.domain.review;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.mission.Mission;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews",
        uniqueConstraints = @UniqueConstraint(name = "uq_review_user_mission", columnNames = {"user_id","mission_id"}),
        indexes = {
                @Index(name = "idx_review_store", columnList = "store_id"),
                @Index(name = "idx_review_mission", columnList = "mission_id")
        })
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    /** 미션/스토어는 둘 다 NULL 가능: 하나 또는 둘 다 참조 허용(DDL 기준) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id",
            foreignKey = @ForeignKey(name = "fk_reviews_mission"))
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id",
            foreignKey = @ForeignKey(name = "fk_reviews_store"))
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_reviews_user"))
    private User user;

    @Min(1) @Max(5)
    @Column(nullable = false)
    private Integer rating;

    @Lob
    private String content;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPhoto> photos = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();
}
