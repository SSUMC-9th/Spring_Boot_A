package com.example.foodmaster.domain.review.converter;

import com.example.foodmaster.domain.review.dto.ReviewReqDTO;
import com.example.foodmaster.domain.review.dto.ReviewResDTO;
import com.example.foodmaster.domain.review.entity.Review;

public class ReviewConverter {

    // Entity -> DTO
    public static ReviewResDTO.AddDTO toReviewDTO(
            Review review
    ) {
        return ReviewResDTO.AddDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .store(review.getStore())
                .member(review.getMember())
                .creatAt(review.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Review toReviewEntity(
            ReviewReqDTO.AddDTO dto
    ) {
        return Review.builder()
                .content(dto.content())
                .star(dto.star())
                .member(dto.member())
                .store(dto.store())
                .build();
    }
}
