package com.example.foodmaster.domain.review.controller;

import com.example.foodmaster.domain.review.dto.ReviewReqDTO;
import com.example.foodmaster.domain.review.dto.ReviewResDTO;
import com.example.foodmaster.domain.review.exception.code.ReviewSuccessCode;
import com.example.foodmaster.domain.review.service.command.ReviewCommandService;
import com.example.foodmaster.domain.review.service.query.ReviewQueryService;
import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewQueryController implements ReviewQueryControllerDocs{

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews")
    @Override
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ) {

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }

    @PostMapping("/reviews/add")
    public ApiResponse<ReviewResDTO.AddDTO> addReview(
            @RequestBody ReviewReqDTO.AddDTO dto
            ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewCommandService.addReview(dto));
    }
}
