// src/main/java/com/example/umc9th/application/review/query/ReviewQueryService.java
package com.example.umc9th.application.review.query;

import com.example.umc9th.domain.review.QReview;
import com.example.umc9th.domain.review.Review;
import com.example.umc9th.domain.store.QStore;
import com.example.umc9th.infrastructure.review.ReviewJpaRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewJpaRepository reviewRepository;

    /**
     * type : "store" | "rating" | "both"
     * query: store일 때 가게명, rating일 때 별점 임계값(예: 4.5), both일 때 "가게명&4.5"
     * userId: "내가 작성한 리뷰" 필터용
     */
    public List<Review> searchReview(Long userId, String query, String type) {
        QReview r = QReview.review;
        QStore  s = QStore.store;

        BooleanBuilder builder = new BooleanBuilder().and(r.user.id.eq(userId));

        if ("store".equalsIgnoreCase(type)) {
            builder.and(s.name.containsIgnoreCase(query));
        } else if ("rating".equalsIgnoreCase(type)) {
            applyStarFilter(builder, r, query);
        } else if ("both".equalsIgnoreCase(type)) {
            String[] parts = query.split("&", 2);
            if (parts.length > 0) builder.and(s.name.containsIgnoreCase(parts[0]));
            if (parts.length > 1)  applyStarFilter(builder, r, parts[1]);
        }
        return reviewRepository.searchReview(builder);
    }

    /** 점대(5/4/3/…) 우선, 소수면 임계값(>=)로 처리 */
    private void applyStarFilter(BooleanBuilder builder, QReview r, String raw) {
        String trimmed = raw.trim();
        // 정수(5,4,3...) = 점대 필터
        if (trimmed.matches("^[0-9]+$")) {
            int group = Integer.parseInt(trimmed); // 5/4/3/2/1
            NumberTemplate<Integer> bucket =
                    Expressions.numberTemplate(Integer.class, "floor({0})", r.rating);
            builder.and(bucket.eq(group));  // 예: group=4 → 4.0 ≤ rating < 5.0
            return;
        }
        // 소수(예: 4.5) = 임계값(>=)
        try {
            double threshold = Double.parseDouble(trimmed);
            builder.and(r.rating.goe(threshold));
        } catch (NumberFormatException ignore) { /* invalid input → 무시 */ }
    }

    // 페이징 버전
    public Page<Review> searchReview(Long userId, String query, String type, Pageable pageable) {
        QReview r = QReview.review;
        QStore  s = QStore.store;

        BooleanBuilder builder = new BooleanBuilder().and(r.user.id.eq(userId));

        if ("store".equalsIgnoreCase(type)) {
            builder.and(s.name.containsIgnoreCase(query));
        } else if ("rating".equalsIgnoreCase(type)) {
            applyStarFilter(builder, r, query);              // 점대/임계값 공통 처리
        } else if ("both".equalsIgnoreCase(type)) {
            String[] parts = query.split("&", 2);
            if (parts.length > 0) builder.and(s.name.containsIgnoreCase(parts[0]));
            if (parts.length > 1)  applyStarFilter(builder, r, parts[1]);
        }
        return reviewRepository.searchReview(builder, pageable);
    }

    private double parseDoubleSafe(String s, double def) {
        try { return Double.parseDouble(s); } catch (Exception e) { return def; }
    }
}
