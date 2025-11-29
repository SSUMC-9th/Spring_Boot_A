package com.example.umc9th.presentation.review;

import com.example.umc9th.application.review.query.ReviewQueryService;
import com.example.umc9th.domain.review.Review;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewSearchController {

    private final ReviewQueryService reviewQueryService;

    // 리스트 반환(스샷 그대로)
    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestHeader("X-USER-ID") Long userId, // or SecurityContext
            @RequestParam String query,              // "안암동" or "4.5" or "안암동&4.5"
            @RequestParam String type                // "store" | "star" | "both"
    ) {
        return reviewQueryService.searchReview(userId, query, type);
    }

    // 페이징 버전
    @Operation(summary = "내가 작성한 리뷰 목록 조회")
    @GetMapping("/reviews/search/page")
    public Page<Review> searchReviewPage(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam String query,
            @RequestParam String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewQueryService.searchReview(userId, query, type, pageable);
    }
}

