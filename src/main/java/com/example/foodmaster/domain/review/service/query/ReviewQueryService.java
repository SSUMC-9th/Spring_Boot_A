package com.example.foodmaster.domain.review.service.query;

import com.example.foodmaster.domain.review.dto.ReviewResDTO;

public interface ReviewQueryService {
//    ReviewResDTO.ReviewPreViewListDTO findReview();

    ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName, Integer page
    );

    ReviewResDTO.ReviewPreViewListDTO findReview(
            Long memberId, Integer page
    );
}
