package com.example.umc9th.presentation.review;

import com.example.umc9th.application.review.dto.ReviewReqDTO;
import com.example.umc9th.application.review.dto.ReviewResDTO;
import com.example.umc9th.application.review.service.ReviewCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewCommandController {

    private final ReviewCommandService reviewCommandService;

    // 가게에 리뷰 추가하기
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.Simple> createStoreReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewReqDTO.Create dto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,   // 프로젝트에 맞게 SuccessCode 선택
                reviewCommandService.createStoreReview(storeId, dto)
        );
    }
}

