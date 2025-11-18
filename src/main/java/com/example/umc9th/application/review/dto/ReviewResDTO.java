package com.example.umc9th.application.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record Simple(
            Long reviewId,
            Long storeId,
            Long userId,
            Integer rating,
            String content,
            List<String> photoUrls,
            LocalDateTime createdAt
    ) {}
}
