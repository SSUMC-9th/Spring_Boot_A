package com.example.foodmaster.domain.review.controller;

import com.example.foodmaster.domain.review.dto.ReviewReqDTO;
import com.example.foodmaster.domain.review.dto.ReviewResDTO;
import com.example.foodmaster.domain.review.exception.code.ReviewSuccessCode;
import com.example.foodmaster.domain.review.service.command.ReviewCommandService;
import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/reviews/add")
    public ApiResponse<ReviewResDTO.AddDTO> addReview(
            @RequestBody ReviewReqDTO.AddDTO dto
            ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewCommandService.addReview(dto));
    }
}
