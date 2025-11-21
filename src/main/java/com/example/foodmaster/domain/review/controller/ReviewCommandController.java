package com.example.foodmaster.domain.review.controller;

import com.example.foodmaster.domain.review.entity.Review;
import com.example.foodmaster.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewCommandController {

    private final ReviewService reviewService;

    @GetMapping("/reviews/search")
    public String searchReview(
            @RequestParam String query,
            @RequestParam String type,
            Model model
    ) {
        List<Review> result = reviewService.searchReview(query, type);
        model.addAttribute("result", result);
        return null;
    }

    @GetMapping("/reviews/members/{memberId}")
    public List<Review> searchMyReview(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Double star,
            Model model
    ) {
        List<Review> reviewList = reviewService.searchMyReview(memberId, storeId, star);
        model.addAttribute("reviewList", reviewList);
        return null;
    }
}
