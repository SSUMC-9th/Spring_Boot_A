package com.example.foodmaster.domain.review.repository;

import com.example.foodmaster.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    // 검색 API
    List<Review> searchReview(
            Predicate predicate
    );

    List<Review> searchMyReview(
            Predicate predicate
    );
}
