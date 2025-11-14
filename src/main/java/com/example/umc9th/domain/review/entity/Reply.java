package com.example.umc9th.domain.review.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review_reply")
public class Reply extends BaseEntity {

    // 답글 식별자 (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK: 리뷰 식별자 (OneToOne 관계 설정)
    // ReviewReply는 항상 특정 Review에 종속됩니다.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id") // DB 외래 키 컬럼 이름 지정
    private Review review;

    // 내용 (TEXT)
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}