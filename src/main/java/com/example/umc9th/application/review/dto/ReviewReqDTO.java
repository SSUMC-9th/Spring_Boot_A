package com.example.umc9th.application.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.List;

public class ReviewReqDTO {

    public record Create(
            @Min(1) @Max(5)
            Integer rating,
            String content,
            // 사진 URL 리스트 (없으면 null/빈 배열 허용)
            List<String> photoUrls
    ) {}
}
