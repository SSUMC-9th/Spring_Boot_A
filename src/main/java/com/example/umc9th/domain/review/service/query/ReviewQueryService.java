package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO;

public interface ReviewQueryService{
    ReviewResponseDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);
}
