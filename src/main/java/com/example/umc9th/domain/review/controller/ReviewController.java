package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews") // 리뷰 기능이 주가 되도록 경로를 /reviews로 설정
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    // 가게에 리뷰 추가 API
    // 요청 본문에서 storeId를 받으므로, 경로에 storeId를 명시할 필요 없음
    @PostMapping("/")
    public ApiResponse addReview(
            //유효성 검사
            @RequestBody @Valid ReviewRequestDTO request
    ) {
        // 1. 서비스 호출 및 응답 DTO 받기
        ReviewResponseDTO response = reviewCommandService.addReview(request);

        // 2. 성공 응답 반환
        return ApiResponse.onSuccess(response);
    }
}