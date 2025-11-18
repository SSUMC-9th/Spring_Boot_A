package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {

    private Long reviewId;

    private String reviewerNickname; // 작성자 닉네임 (Member 엔티티에서 추출)

    private String storeName;        // 가게 이름 (Store 엔티티에서 추출)

    private Float score;

    private String content;

    private LocalDateTime createdAt;
}