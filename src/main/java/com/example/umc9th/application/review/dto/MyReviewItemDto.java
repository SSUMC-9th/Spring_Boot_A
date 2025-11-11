package com.example.umc9th.application.review.dto;
import java.time.LocalDateTime;
public record MyReviewItemDto(
        Long reviewId, String storeName, int rating, String content,
        LocalDateTime createdAt, long photoCount
) {}

