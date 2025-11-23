// src/main/java/.../domain/review/controller/ReviewController.java

package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController implements ReviewControllerDocs {
    //                                         ^^^^^^^^^^^^^^^^^^^^^^^^^ 인터페이스 구현

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    // 1. 가게에 리뷰 추가 API (인터페이스 구현)
    @Override // 메서드를 인터페이스로부터 가져왔음을 명시
    @PostMapping("")
    public ApiResponse<ReviewResponseDTO> addReview(
            @RequestBody @Valid ReviewRequestDTO request
    ) {
        // 1. 서비스 호출 및 응답 DTO 받기
        ReviewResponseDTO response = reviewCommandService.addReview(request);

        // 2. 성공 응답 반환 (CREATED 상태 코드 사용 권장)
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, response);
    }

    // 2. 가게의 리뷰 목록 조회 API (인터페이스 구현)
    @Override
    @GetMapping("")
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ) {

        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }
}