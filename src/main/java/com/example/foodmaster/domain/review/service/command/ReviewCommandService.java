package com.example.foodmaster.domain.review.service.command;

import com.example.foodmaster.domain.review.dto.ReviewReqDTO;
import com.example.foodmaster.domain.review.dto.ReviewResDTO;

public interface ReviewCommandService {

    ReviewResDTO.AddDTO addReview(ReviewReqDTO.AddDTO dto);
}
