package com.example.foodmaster.domain.review.controller;

import com.example.foodmaster.domain.review.entity.Review;
import com.example.foodmaster.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        List<Review> result = reviewService.searchReview(query, type);
        return result;
    }

    @GetMapping("/reviews/{userid}")
    public List<Review> searchMyReview(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Double star
    ) {
        List<Review> reviewList = reviewService.searchMyReview(memberId, storeId, star);
        return reviewList;
    }
}
