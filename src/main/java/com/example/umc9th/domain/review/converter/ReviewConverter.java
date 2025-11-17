// com.example.umc9th.domain.review.converter.ReviewConverter.java
package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

public class ReviewConverter {

    // Request DTO -> Entity
    public static Review toReview(
            ReviewRequestDTO request,
            Store store,
            Member member // Service에서 조회된 Member 및 Store 객체 사용
    ) {
        return Review.builder()
                .score(request.getScore())
                .content(request.getContent())
                .member(member)
                .store(store)
                .build();
    }

    // Entity -> Response DTO
    public static ReviewResponseDTO toReviewResponseDTO(
            Review review
    ) {
        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .reviewerNickname(review.getMember().getNickname()) // Member 엔티티에서 닉네임 추출 가정
                .storeName(review.getStore().getStoreName())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}