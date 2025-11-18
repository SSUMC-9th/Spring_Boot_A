package com.example.umc9th.application.review.service;

import com.example.umc9th.application.review.dto.ReviewReqDTO;
import com.example.umc9th.application.review.dto.ReviewResDTO;

public interface ReviewCommandService {

    // 가게에 리뷰 달기
    ReviewResDTO.Simple createStoreReview(Long storeId, ReviewReqDTO.Create dto);
}