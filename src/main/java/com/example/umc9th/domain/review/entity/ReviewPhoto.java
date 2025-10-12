package com.example.umc9th.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review_photo")
public class ReviewPhoto {

    // 리뷰 사진 식별자 (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK: 리뷰 식별자 (ManyToOne 관계 설정)
    // 여러 사진이 하나의 리뷰에 속함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id") // DB 외래 키 컬럼 이름 지정
    private Review review;

    // 리뷰 사진 URL (VARCHAR)
    @Column(name = "photo_url")
    private String photoUrl;
}