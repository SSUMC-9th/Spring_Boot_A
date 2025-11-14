package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.member.entity.Member; // Member 엔티티 가정
import com.example.umc9th.domain.store.entity.Store;  // Store 엔티티 가정
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK: 가게 (ManyToOne 관계 설정)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // FK: 유저 (ManyToOne 관계 설정)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    // 평점 (FLOAT)
    @Column(name = "score")
    private Float score;

    // 내용 (TEXT)
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;


    // 관계: 리뷰 사진 (OneToMany) - 해당 리뷰에 여러 장의 사진이 있을 수 있음
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();

    // 관계: 리뷰 답글 (OneToOne) - 해당 리뷰에 하나의 답글이 달릴 수 있음
    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Reply reply;
}