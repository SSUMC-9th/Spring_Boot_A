package com.example.foodmaster.domain.review.converter;

import com.example.foodmaster.domain.review.dto.ReviewReqDTO;
import com.example.foodmaster.domain.review.dto.ReviewResDTO;
import com.example.foodmaster.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

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

    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ) {
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ) {
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
