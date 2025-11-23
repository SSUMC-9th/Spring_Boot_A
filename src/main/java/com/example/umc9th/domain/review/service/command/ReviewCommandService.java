package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;

public interface ReviewCommandService {
    // 요청 DTO를 받아 응답 DTO를 반환하도록 설계 변경
    ReviewResponseDTO addReview(ReviewRequestDTO request);
}