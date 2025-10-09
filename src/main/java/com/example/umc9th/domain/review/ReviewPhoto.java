package com.example.umc9th.domain.review;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review_photos",
        indexes = @Index(name = "idx_review_photos_review", columnList = "review_id"))
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class ReviewPhoto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "review_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_review_photos_review"))
    private Review review;

    @Column(length = 500, nullable = false)
    private String url;
}

